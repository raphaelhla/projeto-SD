package com.raphaelagra.notificacao.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.raphaelagra.notificacao.constante.MensagemConstante;
import com.raphaelagra.notificacao.domain.Proposta;
import com.raphaelagra.notificacao.service.NotificacaoSnsService;

@Component
public class PropostaPendenteListener {

	@Autowired
	private NotificacaoSnsService notificacaoSnsService;
	
	@RabbitListener(queues = "${rabbitmq.queue.propostapendente}")
	public void propostaPendente(Proposta proposta) {
		String mensagem = String.format(MensagemConstante.PROPOSTA_EM_ANALISE, proposta.getUsuario().getNome());
		notificacaoSnsService.notificar(proposta.getUsuario().getTelefone(), mensagem);
	}
}
