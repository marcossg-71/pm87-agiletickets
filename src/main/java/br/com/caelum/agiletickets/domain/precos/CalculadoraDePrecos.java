package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		
		int quantidadeIngressosRestantes = sessao.getTotalIngressos() - sessao.getIngressosReservados();
		double porcetagemIngressosRestantes = quantidadeIngressosRestantes / sessao.getTotalIngressos().doubleValue();
		if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.CINEMA) || sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.SHOW)) {
			//quando estiver acabando os ingressos... 
			preco = PrecoCinemaShow.calcularPreco(sessao, porcetagemIngressosRestantes);
		} else if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.BALLET)) {
			preco = PrecoBalletOrquestra.calcularPreco(sessao, porcetagemIngressosRestantes);
		} else if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.ORQUESTRA)) {
			preco = PrecoBalletOrquestra.calcularPreco(sessao, porcetagemIngressosRestantes);
		}  else {
			preco = PrecoTeatro.calcularPreco(sessao);
		} 

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	

	
	

}