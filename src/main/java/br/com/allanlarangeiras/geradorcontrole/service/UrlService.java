package br.com.allanlarangeiras.geradorcontrole.service;

import java.util.List;

public interface UrlService {

	List<String> obterUrls();

	void persistirUrls(List<String> urls);
	
}
