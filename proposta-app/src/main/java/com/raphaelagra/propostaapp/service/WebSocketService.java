package com.raphaelagra.propostaapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.raphaelagra.propostaapp.dto.PropostaResponseDto;

@Service
public class WebSocketService {

	@Autowired
	private SimpMessagingTemplate template;
	
	public void notificar(PropostaResponseDto proposta) {
		template.convertAndSend("/propostas", proposta);
	}
}
