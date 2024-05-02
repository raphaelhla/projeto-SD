package com.raphaelagra.analisecredito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.raphaelagra.analisecredito.domain.Proposta;
import com.raphaelagra.analisecredito.exceptions.StrategyException;
import com.raphaelagra.analisecredito.service.strategy.CalculoPonto;

@Service
public class AnaliseCreditoService {

	@Autowired
	private List<CalculoPonto> calculoPontoList;
	
	@Autowired
	private NotificacaoRabbitService notificacaoRabbitService;
	
	@Value("${rabbitmq.propostaconcluida.exchange}")
	private String exchangePropostaConcluida;
	
	public void analisar(Proposta proposta) {
		try {
			int pontuacao = calculoPontoList.stream().mapToInt(impl -> impl.calcular(proposta)).sum();
			boolean aprovada = pontuacao > 350;
			proposta.setAprovada(aprovada);
		} catch (StrategyException ex) {
			proposta.setAprovada(false);
			proposta.setObservacao(ex.getMessage());
		}
		
		notificacaoRabbitService.notificar(exchangePropostaConcluida, proposta);
	}
}
