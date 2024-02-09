package br.com.joaovictor.rinhadebackend.enums;

public enum TipoTransacao {

	c("c"),
	d("d");
	
	private String tipo;
	
	private TipoTransacao(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
}
