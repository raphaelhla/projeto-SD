package com.raphaelagra.analisecredito.service.strategy.impl;

import org.springframework.stereotype.Component;

import com.raphaelagra.analisecredito.domain.Proposta;
import com.raphaelagra.analisecredito.service.strategy.CalculoPonto;

@Component
public class RendaMaiorValorSolicitadoImpl implements CalculoPonto{

	@Override
	public int calcular(Proposta proposta) {
		return rendaMaiorValorSolicitado(proposta) ? 100 : 0;
	}
	
	private boolean rendaMaiorValorSolicitado(Proposta proposta) {
		return proposta.getUsuario().getRenda() > proposta.getValorSolicitado();
	}

}
