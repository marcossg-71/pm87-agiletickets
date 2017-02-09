package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;

public class PrecoTeatro {
	public static BigDecimal calcularPreco(Sessao sessao) {
		BigDecimal preco;
		//nao aplica aumento para teatro (quem vai é pobretão)
		preco = sessao.getPreco();
		return preco;
	}
}
