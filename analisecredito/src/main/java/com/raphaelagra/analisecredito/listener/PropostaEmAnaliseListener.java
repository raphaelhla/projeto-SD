package com.raphaelagra.analisecredito.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.raphaelagra.analisecredito.domain.Proposta;
import com.raphaelagra.analisecredito.service.AnaliseCreditoService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class PropostaEmAnaliseListener {

	private final AnaliseCreditoService analiseCreditoService;
	
	@RabbitListener(queues = "${rabbitmq.queue.propostapendente}")
	public void propostaPendente(Proposta proposta) {
		analiseCreditoService.analisar(proposta);
	}
}
