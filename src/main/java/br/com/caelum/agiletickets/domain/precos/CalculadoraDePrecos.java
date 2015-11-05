package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco = calcularAjustesPreco(sessao);
		return preco.multiply(BigDecimal.valueOf(quantidade));
	}
	

	public static BigDecimal calcularAjustesPreco(Sessao sessao) {
		BigDecimal preco = calcularAjustePrecoOcupacao(sessao);
		if (sessao.getEspetaculo().getTipo().taxaDuracao) {
			preco = somarAjustePrecoDuracao(sessao, preco);
		}
		return preco;
	}
	
	private static BigDecimal calcularAjustePrecoOcupacao(Sessao sessao) {
		TipoDeEspetaculo tipo = sessao.getEspetaculo().getTipo();
		BigDecimal preco = sessao.getPreco();
		if ((sessao.getTotalIngressos() - sessao.getIngressosReservados()) / sessao.getTotalIngressos().doubleValue() <= tipo.taxaOcupacao) { 
			preco = preco.add(preco.multiply(BigDecimal.valueOf(tipo.percentualAumentoOcupacao)));
		} 
		return preco;
	}
	
	private static BigDecimal somarAjustePrecoDuracao(Sessao sessao, BigDecimal preco) {
		if (sessao.getDuracaoEmMinutos() > 60) {
			preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
		}
		return preco;
	}

	
	

}