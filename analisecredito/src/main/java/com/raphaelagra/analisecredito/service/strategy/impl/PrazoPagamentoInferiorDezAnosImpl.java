package com.raphaelagra.analisecredito.service.strategy.impl;

import org.springframework.stereotype.Component;

import com.raphaelagra.analisecredito.domain.Proposta;
import com.raphaelagra.analisecredito.service.strategy.CalculoPonto;

@Component
public class PrazoPagamentoInferiorDezAnosImpl implements CalculoPonto{

	@Override
	public int calcular(Proposta proposta) {
		return proposta.getPrazoPagamento() <= 120 ? 80 : 0;
	}

}
