package br.com.allanlarangeiras.geradorcontrole.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.allanlarangeiras.geradorcontrole.service.GeradorControleService;
import br.com.allanlarangeiras.geradorcontrole.util.Compact;

@Service
public class GeradorControleServiceImpl implements GeradorControleService {

	private static final GeradorControleService INSTANCE = new GeradorControleServiceImpl();

	public static int TIMECTRL = 10800;
	public static int TAM_TIME = 10;
	public static int TAM_IP = 10;
	public static int TAM_DV = 4;
	public static String MAX_IP = "4294967295";

	public static int CTRL_SUCESSO = 0;
	public static int CTRL_ERRO_PARAM = -1;
	public static int CTRL_AUSENTE = -2;
	public static int CTRL_INVALIDO = -3;
	public static int CTRL_DIGIVER = -4;
	public static int CTRL_EXPIRED = -5;
	public static int CTRL_IP_INVALIDO = -6;
	public static int CTRL_IP_DIFERENTE = -7;
	public static int CTRL_FRASE_DIF = -8;
	private static int[] PESO = { 7, 11, 3, 9, 2, 5, 19, 23, 17, 31, 29 };

	private GeradorControleServiceImpl() {
	}

	public static GeradorControleService getInstance() {
		return INSTANCE;
	}

	public String gerarCodigoControle(String cpfCnpj) {
		int tamanhoControle = TAM_TIME + TAM_IP + cpfCnpj.length() + TAM_DV + 1;
		char[] Controle = new char[tamanhoControle];
		char[] EndIp = "127.0.0.1".toCharArray();
		char[] Frase = cpfCnpj.toCharArray();
		int TamFrase = 16;
		int TamCtrl = tamanhoControle;

		char[] strBuf = new char[' '];
		char[] strBuf2 = new char[' '];

		long dwEndIP = 0L;
		long Posicao = 0L;
		EndIp = new char[] { '', '\000', '\000', '\001' };
		StringBuffer sb = new StringBuffer();

		if ((EndIp == null) || (lstrlen(EndIp) == 0))
			return "";
		if ((Frase == null) || (lstrlen(Frase) == 0))
			return "";
		if (TamFrase == 0)
			return "";
		if (Controle == null) {
			return "";
		}

		long ltime = getSerialDate();
		ltime += TIMECTRL;
		sb.append(converte(ltime, TAM_TIME));

		dwEndIP = getLongAddress(EndIp);
		if (dwEndIP == -1L) {
			return "";
		}
		sb.append(converte(dwEndIP, TAM_IP));

		sb.append(converte(Frase, TamFrase));

		char[] tmp = sb.toString().toCharArray();

		for (int iaux = 0; iaux < TAM_DV; iaux++) {
			int iret = DigiVeri(tmp, iaux, iaux + 4);
			if (iret > 9)
				iret = 0;
			iret += 48;
			sb.append((char) iret);
			tmp = sb.toString().toCharArray();
		}

		tmp = sb.toString().toCharArray();
		Compact.Compacta(tmp, strBuf, strBuf.length);
		//System.arraycopy(strBuf, 0, Controle, 0, TamCtrl);
		return new String(strBuf);
	}

	public static long getSerialDate() {
		return new Date().getTime() / 1000L;
	}

	private static long getLongAddress(char[] ipByte) {
		long address = ipByte[0] & 0xFF;
		address |= ipByte[1] << '\b' & 0xFF00;
		address |= ipByte[2] << '\020' & 0xFF0000;
		address |= ipByte[3] << '\030' & 0xFF000000;
		return address;
	}

	public static int lstrlen(char[] bytes) {
		return bytes.length;
	}

	public static int sizeof(char[] buf) {
		return buf.length;
	}

	private static String converte(long parm, int tamanho) {
		return converte(String.valueOf(parm), tamanho);
	}

	private static String converte(char[] parm, int tamanho) {
		return converte(new String(parm), tamanho);
	}

	private static String converte(String parm, int tamanho) {
		String tmp = "";
		for (int i = 0; i < tamanho; i++) {
			tmp = tmp + "0";
		}
		tmp = tmp + parm;
		return tmp.substring(tmp.length() - tamanho);
	}

	public static int DigiVeri(char[] lpbyData, long ipesoini, long ipesofim) {
		int isoma = 0;
		int ipeso = (int) ipesoini;

		int ipos = lpbyData.length;
		while (ipos > 0) {
			isoma += (lpbyData[(ipos - 1)] - '0') * PESO[ipeso];
			ipeso++;
			if (ipeso > ipesofim)
				ipeso = (int) ipesoini;
			ipos--;
		}
		int iresto = isoma % 11;
		iresto = 11 - iresto;
		return iresto;
	}
	
	

}
