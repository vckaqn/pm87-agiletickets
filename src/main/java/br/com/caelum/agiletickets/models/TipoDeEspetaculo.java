package br.com.caelum.agiletickets.models;

public enum TipoDeEspetaculo {
	
	
	CINEMA(0.05, 0.10, false), SHOW(0.05, 0.10, false), TEATRO(0.0, 0.0, false), BALLET(0.50, 0.20, true), ORQUESTRA(0.50, 0.20, true);
	
	public double taxaOcupacao;
	public double percentualAumentoOcupacao;
	public boolean taxaDuracao;
	
	TipoDeEspetaculo(double taxaOcupacao, double percentualAumentoOcupacao, boolean taxaDuracao) {
		this.taxaOcupacao = taxaOcupacao;
		this.percentualAumentoOcupacao = percentualAumentoOcupacao;
		this.taxaDuracao = taxaDuracao;
	}

	
}
