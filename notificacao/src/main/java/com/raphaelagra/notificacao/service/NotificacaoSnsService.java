package com.raphaelagra.notificacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;

@Service
public class NotificacaoSnsService {

	@Autowired
	private AmazonSNS amazonSNS;
	
	public void notificar(String telefone, String mensagem) {
		PublishRequest publishRequest = new PublishRequest().withMessage(mensagem).withPhoneNumber(telefone);
		amazonSNS.publish(publishRequest);
	}
}
