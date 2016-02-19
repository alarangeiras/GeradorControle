package br.com.allanlarangeiras.geradorcontrole;

import br.com.allanlarangeiras.geradorcontrole.service.GeradorControleService;
import br.com.allanlarangeiras.geradorcontrole.service.impl.GeradorControleServiceImpl;

public class Main {

	public static void main(String[] args) {
		String cpfCnpj = "43338235000109";
		if (args.length > 0) {
			cpfCnpj = args[0];
		}
		GeradorControleService geradorControleService = GeradorControleServiceImpl.getInstance();
		String controle = geradorControleService.gerarCodigoControle(cpfCnpj);
		System.out.println("CPF/CNPJ: " + cpfCnpj);
		System.out.println("Controle: " + controle);
		System.out.println("URL Exemplo: " + "https://wwws.bradescoseguros.com.br/INET-HistoricoApolice/filtro-historico-apolice.do?ctrl=RFfQJhGARhXtEqQDDyCvAABJUjhk&cd_cgccpf=43338235000109&id_cia=1&nome=RODOBENS+ADM+E+COR+DE+SEGS+LTDA");
	}
	
}
