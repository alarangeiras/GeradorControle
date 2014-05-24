package br.com.allanlarangeiras.geradorcontrole.tipos;


public enum ParametrosURL {

	CTRL("ctrl"),
	CORRETOR("cd_cgccpf"),
	NOME("nome"),
	ID_CIA("id_cia");
	
	private String valor;

	private ParametrosURL(String valor) {
		this.valor = valor;

	}

	public String toString() {
		return valor;
	}
	
}
