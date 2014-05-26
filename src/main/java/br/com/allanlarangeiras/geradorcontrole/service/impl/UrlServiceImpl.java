package br.com.allanlarangeiras.geradorcontrole.service.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import br.com.allanlarangeiras.geradorcontrole.service.UrlService;

public class UrlServiceImpl implements UrlService {

	private static final String GERADOR_CONTROLE_FILE = "GeradorControleUrls.ser";
	private static final UrlService INSTANCE = new UrlServiceImpl();

	public static UrlService getInstance() {
		return INSTANCE;
	}

	@SuppressWarnings("unchecked")
	public List<String> obterUrls() {
		List<String> urls = null;
		try {
			FileInputStream fis = new FileInputStream(GERADOR_CONTROLE_FILE);
			ObjectInputStream ois = new ObjectInputStream(fis);
			urls = (List<String>) ois.readObject();
			ois.close();
		} catch (Exception e) { }

		return urls;
	}

	public void persistirUrls(List<String> urls) {
		try {
			FileOutputStream fo = new FileOutputStream(GERADOR_CONTROLE_FILE);
			ObjectOutputStream oo = new ObjectOutputStream(fo);
			oo.writeObject(urls);
			oo.flush();
			oo.close();
		} catch (Exception e) { }

	}

}
