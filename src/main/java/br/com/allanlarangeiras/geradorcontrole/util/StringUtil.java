package br.com.allanlarangeiras.geradorcontrole.util;

public class StringUtil {

	public static String onlyNumbers(String number) {
		return number.replaceAll("\\D", "");
	}
	
}
