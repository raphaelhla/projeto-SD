package com.raphaelagra.propostaapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.raphaelagra.propostaapp.dto.PropostaRequestDto;
import com.raphaelagra.propostaapp.dto.PropostaResponseDto;
import com.raphaelagra.propostaapp.entity.Proposta;
import com.raphaelagra.propostaapp.mapper.PropostaMapper;
import com.raphaelagra.propostaapp.repository.PropostaRepository;

@Service
public class PropostaService {

	private PropostaRepository propostaRepository;
	
	private NotificacaoRabbitService notificacaoRabbitService;
	
	private String exchange;
	
	public PropostaService(	PropostaRepository propostaRepository,
			NotificacaoRabbitService notificacaoRabbitService,
			@Value("${rabbitmq.propostapendente.exchange}")String exchange) {
		
		this.propostaRepository = propostaRepository;
		this.notificacaoRabbitService = notificacaoRabbitService;
		this.exchange = exchange;
	}
	
	public PropostaResponseDto criar(PropostaRequestDto requestDto) {
		Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(requestDto);
		propostaRepository.save(proposta);
		
		notificarRabbitMQ(proposta);
		
		return PropostaMapper.INSTANCE.convertEntityToDto(proposta);
	}
	
	private void notificarRabbitMQ(Proposta proposta) {
		try {
			notificacaoRabbitService.notificar(proposta, exchange);
		} catch (RuntimeException ex) {
			proposta.setIntegrada(false);
			propostaRepository.save(proposta);
		}
	}

	public List<PropostaResponseDto> obterPropostas() {
		return PropostaMapper.INSTANCE.convertListEntityToListDto(propostaRepository.findAll());
	}
}
