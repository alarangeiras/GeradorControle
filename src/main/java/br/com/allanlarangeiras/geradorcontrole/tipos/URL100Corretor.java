package br.com.allanlarangeiras.geradorcontrole.tipos;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import br.com.allanlarangeiras.geradorcontrole.util.URLUtil;

public class URL100Corretor {

	private String urlRelativa;
	
	public URL100Corretor(String cpfCnpjCorretor, String id_cia, String nome, String ctrl) throws MalformedURLException {
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put(ParametrosURL.CTRL.toString(), ctrl);
		parametros.put(ParametrosURL.NOME.toString(), nome);
		parametros.put(ParametrosURL.CORRETOR.toString(), cpfCnpjCorretor);
		parametros.put(ParametrosURL.ID_CIA.toString(), id_cia);
		
		this.urlRelativa =  URLUtil.obterURL(parametros);
		
	}
	
	public String getUrlRelativa() {
		return urlRelativa;
	}
}
