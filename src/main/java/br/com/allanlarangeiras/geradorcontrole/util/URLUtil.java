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
	
	public static String obterURL(Map<String, String> parametros) {
		StringBuilder urlRelativa = new StringBuilder();
		urlRelativa.append(INTERROGACAO);
		for (Map.Entry<String, String> parametro : parametros.entrySet()) {
			if (urlRelativa.toString().charAt(urlRelativa.length()-1) != '?') {
				urlRelativa.append(AND );

			}
			urlRelativa.append(parametro.getKey());
			urlRelativa.append(IGUAL);
			urlRelativa.append(parametro.getValue());
		}
		return urlRelativa.toString();
	}
	
}
