package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;

public class PrecoCinemaShow {
	public static BigDecimal calcularPreco(Sessao sessao,
			double porcetagemIngressosRestantes) {
		BigDecimal preco;
		if(porcetagemIngressosRestantes <= 0.05) { 
			preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
		} else {
			preco = sessao.getPreco();
		}
		return preco;
	}
}
