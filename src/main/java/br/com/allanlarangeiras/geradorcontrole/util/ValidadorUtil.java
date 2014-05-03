package br.com.allanlarangeiras.geradorcontrole.util;

public final class ValidadorUtil {

	private ValidadorUtil() { }
	
	public static boolean isInteiroValido(String valor) {
		try {
			Integer.parseInt(valor);
			return true;
			
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
}
