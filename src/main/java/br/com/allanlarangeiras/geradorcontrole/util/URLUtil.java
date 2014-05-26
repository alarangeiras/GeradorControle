package br.com.allanlarangeiras.geradorcontrole.util;

import java.awt.Desktop;
import java.net.URI;
import java.net.URL;
import java.util.Map;

public class URLUtil {

	private static String IGUAL = "=";
	private static String INTERROGACAO = "?";
	private static String AND = "&";
	private static String HTTP = "http";

	private URLUtil() { }
	
	public static void abrirUrl(URL url) {
		try {
			Desktop.getDesktop().browse(new URI(url.toString()));
			
		} catch (Exception e) {
			throw new IllegalArgumentException("Erro ao abrir a URL: " + url.getPath());
			
		}
	}
	
	public static String obterURL(String urlBase, Map<String, String> parametros) {
		StringBuilder urlCompleta = new StringBuilder();
		if (urlBase.length() > 0 && !urlBase.startsWith(HTTP)) {
			urlCompleta.append(HTTP + "://");
		}
		urlCompleta.append(urlBase);
		urlCompleta.append(INTERROGACAO );
		for (Map.Entry<String, String> parametro : parametros.entrySet()) {
			if (urlCompleta.toString().charAt(urlCompleta.length()-1) != '?') {
				urlCompleta.append(AND );

			}
			urlCompleta.append(parametro.getKey());
			urlCompleta.append(IGUAL);
			urlCompleta.append(parametro.getValue());
		}
		return urlCompleta.toString();
	}
	
}
