package br.com.allanlarangeiras.geradorcontrole.model;

public class Controle {

	public String cpfCnpj;
	public String controle;
	
	public Controle(String cpfCnpj) {
		if (cpfCnpj == null) {
			cpfCnpj = "43338235000109";
		}
		
		this.cpfCnpj = cpfCnpj;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public String getControle() {
		return controle;
	}
	public void setControle(String controle) {
		this.controle = controle;
	}
	
	
	
}
