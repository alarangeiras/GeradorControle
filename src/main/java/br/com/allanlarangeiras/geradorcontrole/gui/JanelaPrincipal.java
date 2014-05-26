package br.com.allanlarangeiras.geradorcontrole.gui;

import static br.com.allanlarangeiras.geradorcontrole.util.ValidadorUtil.isInteiroValido;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.allanlarangeiras.geradorcontrole.service.GeradorControleService;
import br.com.allanlarangeiras.geradorcontrole.service.UrlService;
import br.com.allanlarangeiras.geradorcontrole.service.impl.GeradorControleServiceImpl;
import br.com.allanlarangeiras.geradorcontrole.service.impl.UrlServiceImpl;
import br.com.allanlarangeiras.geradorcontrole.tipos.URL100Corretor;
import br.com.allanlarangeiras.geradorcontrole.util.Config;
import br.com.allanlarangeiras.geradorcontrole.util.MessagesUtil;
import br.com.allanlarangeiras.geradorcontrole.util.URLUtil;

public class JanelaPrincipal extends JFrame {

	private JLabel lblCpfCnpj = new JLabel();
	private JTextField txtCpfCnpj = new JTextField();

	private JLabel lblNome = new JLabel();
	private JTextField txtNome = new JTextField();

	private JLabel lblId_Cia = new JLabel();
	private JTextField txtId_Cia = new JTextField();
	
	private JLabel lblUrl = new JLabel();
	private JComboBox<String> txtUrl = new JComboBox<String>();

	private JButton btnGerarUrl = new JButton();
	private JButton btnBradescor = new JButton();

	
	private StringBuilder mensagens = new StringBuilder();
	
	private GeradorControleService geradorControle = GeradorControleServiceImpl.getInstance();
	private UrlService urlService = UrlServiceImpl.getInstance();
	
	private ResourceBundle resources = ResourceBundle.getBundle(Config.MESSAGES_FILE);
	
	private static final long serialVersionUID = 1L;
	
	public JanelaPrincipal() {
		inicializarComponentes();
	}

	private void inicializarComponentes() {
		inicializarLayout();
		inicializarValores();
	}

	public void gerarControle() {
		if (componentesEstaoValidos()) {
			String ctrl = geradorControle.gerarCodigoControle(txtCpfCnpj.getText());
			persisteUrls();
			try {
				URL100Corretor url100Corretor = new URL100Corretor(txtUrl.getSelectedItem().toString(), txtCpfCnpj.getText(), txtId_Cia.getText(), txtNome.getText(), ctrl);
				URLUtil.abrirUrl(url100Corretor.getUrlCompleta());
				removerTodasUrls();
				popularUrls();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao abrir a URL");
			}
			
		} else {
			JOptionPane.showMessageDialog(null, mensagens.toString());
			mensagens = new StringBuilder();
			
		}
	}

	

	private void persisteUrls() {
		List<String> urls = new ArrayList<String>();
		int itens = txtUrl.getItemCount();
		for (int i = 0; i < itens; i++) {
			urls.add(txtUrl.getItemAt(i).toString());
		}
		if (txtUrl.getSelectedIndex() == -1) {
			urls.add(txtUrl.getSelectedItem().toString());
		}
		
		urlService.persistirUrls(urls);
	}

	private boolean componentesEstaoValidos() {
		if (!isInteiroValido(txtCpfCnpj.getText()) && txtCpfCnpj.getText().isEmpty()) {
			mensagens.append(resources.getString(MessagesUtil.CPF_CNPJ_VALIDATION_ERROR_INVALID_KEY) + "\n");
		}
		if (txtNome.getText().isEmpty()) {
			mensagens.append(resources.getString(MessagesUtil.NOME_VALIDATION_ERROR_INVALID_KEY) + "\n");
		}
		if (!isInteiroValido(txtId_Cia.getText()) && txtId_Cia.getText().isEmpty()) {
			mensagens.append(resources.getString(MessagesUtil.CIA_VALIDATION_ERROR_INVALID_KEY) + "\n");
		}
		if (mensagens.length() > 0 ) {
			return false;
		}
		return true;
	}

	private void inicializarLayout() {
		this.setResizable(false);
		this.setTitle(Config.APP_TITLE);
		this.setLayout(new GridLayout(5, 1));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900, 150);
		this.setLocationRelativeTo(null);
		
		JPanel primeiraLinha = new JPanel(new GridLayout(1,2));
		primeiraLinha.add(lblCpfCnpj);
		primeiraLinha.add(txtCpfCnpj);
		
		JPanel segundaLinha = new JPanel(new GridLayout(1,2));
		segundaLinha.add(lblNome);
		segundaLinha.add(txtNome);

		JPanel teceiraLinha = new JPanel(new GridLayout(1,2));
		teceiraLinha.add(lblId_Cia);
		teceiraLinha.add(txtId_Cia);

		JPanel quartaLinha = new JPanel(new GridLayout(1,2));
		quartaLinha.add(lblUrl);
		quartaLinha.add(txtUrl);
		
		JPanel quintaLinha = new JPanel(new GridLayout(1,2));
		quintaLinha.add(btnGerarUrl);
		quintaLinha.add(btnBradescor);
		
		this.add(primeiraLinha);
		this.add(segundaLinha);
		this.add(teceiraLinha);
		this.add(quartaLinha);
		this.add(quintaLinha);
		
		final JanelaPrincipal janelaPrincipal = this;

		btnGerarUrl.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				janelaPrincipal.gerarControle();
				
			}
		});
		btnBradescor.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				janelaPrincipal.inicializarValores();
				
			}
		});		
		
	}

	private void inicializarValores() {
		lblCpfCnpj.setText(resources.getString(MessagesUtil.CPF_CNPJ_LABEL_KEY));
		txtCpfCnpj.setText(resources.getString(MessagesUtil.CPF_CNPJ_DEFAULT_VALUE_KEY));

		lblNome.setText(resources.getString(MessagesUtil.NOME_LABEL_KEY));
		txtNome.setText(resources.getString(MessagesUtil.NOME_DEFAULT_VALUE_KEY));

		lblId_Cia.setText(resources.getString(MessagesUtil.CIA_LABEL_KEY));
		txtId_Cia.setText(resources.getString(MessagesUtil.CIA_DEFAULT_VALUE_KEY));

		
		lblUrl.setText(resources.getString(MessagesUtil.URL_LABEL_KEY));
		txtUrl.setEditable(true);
		popularUrls();
		
		btnGerarUrl.setText(resources.getString(MessagesUtil.GERAR_CODIGO_LABEL_KEY));
		btnBradescor.setText(resources.getString(MessagesUtil.BRADESCOR_LABEL_KEY));

		
		

	}

	private void removerTodasUrls() {
		int itemCount = txtUrl.getItemCount();

	    for(int i=0;i<itemCount;i++){
	    	txtUrl.removeItemAt(0);
	     }
		
	}
	
	private void popularUrls() {
		List<String> urls = urlService.obterUrls();
		
		if (urls != null && urls.size() > 0) {
			for (String url : urls) {
				txtUrl.addItem(url);
			}
		}
		
	}	
	
}
