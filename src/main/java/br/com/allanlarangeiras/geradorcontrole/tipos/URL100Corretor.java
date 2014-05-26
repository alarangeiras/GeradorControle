package br.com.allanlarangeiras.geradorcontrole.tipos;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import br.com.allanlarangeiras.geradorcontrole.util.URLUtil;

public class URL100Corretor {

	private URL urlCompleta;
	
	public URL100Corretor(String urlBase, String cpfCnpjCorretor, String id_cia, String nome, String ctrl) throws MalformedURLException {
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put(ParametrosURL.CTRL.toString(), ctrl);
		parametros.put(ParametrosURL.NOME.toString(), nome);
		parametros.put(ParametrosURL.CORRETOR.toString(), cpfCnpjCorretor);
		parametros.put(ParametrosURL.ID_CIA.toString(), id_cia);
		
		urlCompleta =  new URL(URLUtil.obterURL(urlBase, parametros));
		
	}
	
	public URL getUrlCompleta() {
		return urlCompleta;
	}
}
