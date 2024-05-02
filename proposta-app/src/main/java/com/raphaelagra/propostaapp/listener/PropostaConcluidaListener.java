package com.raphaelagra.propostaapp.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.raphaelagra.propostaapp.dto.PropostaResponseDto;
import com.raphaelagra.propostaapp.entity.Proposta;
import com.raphaelagra.propostaapp.mapper.PropostaMapper;
import com.raphaelagra.propostaapp.repository.PropostaRepository;
import com.raphaelagra.propostaapp.service.WebSocketService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class PropostaConcluidaListener {

	private PropostaRepository propostaRepository;
	
	private WebSocketService webSocketService;
	
	@RabbitListener(queues = "${rabbitmq.queue.propostaconcluida.ms.proposta}")
	public void propostaConcluida(Proposta proposta) {
		atualizarProposta(proposta);
		PropostaResponseDto responseDto = PropostaMapper.INSTANCE.convertEntityToDto(proposta);
		webSocketService.notificar(responseDto);
	}

	private void atualizarProposta(Proposta proposta) {
		propostaRepository.atualizarProposta(proposta.getId(), proposta.getAprovada(), proposta.getObservacao());
	}
}
