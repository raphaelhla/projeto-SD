package com.raphaelagra.propostaapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.raphaelagra.propostaapp.dto.PropostaRequestDto;
import com.raphaelagra.propostaapp.dto.PropostaResponseDto;
import com.raphaelagra.propostaapp.service.PropostaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/proposta")
public class PropostaController {

	private PropostaService propostaService;
	
	@PostMapping
	public ResponseEntity<PropostaResponseDto> criar(@RequestBody PropostaRequestDto requestDto) {
		PropostaResponseDto response = propostaService.criar(requestDto);
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(response.getId())
				.toUri())
				.body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<PropostaResponseDto>> obterPropostas() {
		return ResponseEntity.ok(propostaService.obterPropostas());
	}
}
