package com.raphaelagra.notificacao.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.raphaelagra.notificacao.constante.MensagemConstante;
import com.raphaelagra.notificacao.domain.Proposta;
import com.raphaelagra.notificacao.service.NotificacaoSnsService;

@Component
public class PropostaConcluidaListener {

	@Autowired
	private NotificacaoSnsService notificacaoSnsService;
	
	@RabbitListener(queues = "${rabbitmq.queue.propostaconcluida}")
	public void propostaConcluida(Proposta proposta) {
		String status = proposta.getAprovada() ? "aprovado" : "recusado";
		String mensagem = String.format(MensagemConstante.PROPOSTA_CONCLUIDA, proposta.getUsuario().getNome(), status);
		notificacaoSnsService.notificar(proposta.getUsuario().getTelefone(), mensagem);
	}
}
