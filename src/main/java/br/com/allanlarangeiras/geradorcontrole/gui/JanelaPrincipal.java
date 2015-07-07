package br.com.allanlarangeiras.geradorcontrole.gui;

import static br.com.allanlarangeiras.geradorcontrole.util.ValidadorUtil.isInteiroValido;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.allanlarangeiras.geradorcontrole.service.GeradorControleService;
import br.com.allanlarangeiras.geradorcontrole.service.impl.GeradorControleServiceImpl;
import br.com.allanlarangeiras.geradorcontrole.tipos.URL100Corretor;
import br.com.allanlarangeiras.geradorcontrole.util.Config;
import br.com.allanlarangeiras.geradorcontrole.util.MessagesUtil;

public class JanelaPrincipal extends JFrame implements ClipboardOwner {

	private JLabel lblCpfCnpj = new JLabel();
	private JTextField txtCpfCnpj = new JTextField(20);

	private JLabel lblNome = new JLabel();
	private JTextField txtNome = new JTextField(20);

	private JLabel lblId_Cia = new JLabel();
	private JTextField txtId_Cia = new JTextField(20);
	
	private JLabel lblParametros = new JLabel();
	private JTextField txtParametros = new JTextField(20);

	private JButton btnGerarControle = new JButton();
	private JButton btnBradescor = new JButton();
	
	private JButton btnCopy = new JButton();

	
	private StringBuilder mensagens = new StringBuilder();
	
	private GeradorControleService geradorControle = GeradorControleServiceImpl.getInstance();
	
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
			try {
				URL100Corretor url100Corretor = new URL100Corretor(txtCpfCnpj.getText(), txtId_Cia.getText(), txtNome.getText(), ctrl);
				String urlRelativa = url100Corretor.getUrlRelativa();
				if (urlRelativa != null && !urlRelativa.isEmpty()) {
					txtParametros.setText(urlRelativa);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao abrir a URL");
			}
			
		} else {
			JOptionPane.showMessageDialog(null, mensagens.toString());
			mensagens = new StringBuilder();
			
		}
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
		this.setSize(700, 150);
		this.setLocationRelativeTo(null);
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints col1 = new GridBagConstraints();
		col1.anchor = GridBagConstraints.LINE_START;
		col1.gridx = 0;
		col1.gridy = 0;
		col1.weightx = 0;
		col1.weighty = 0.5;
		col1.insets = new Insets(5, 5, 5, 5);
		
		GridBagConstraints col2 = new GridBagConstraints();
		col2.fill = GridBagConstraints.HORIZONTAL;
		col2.gridx = 1;
		col1.gridy = 0;
		col2.weightx = 0.5;
		col2.weighty = 0.5;
		col2.insets = new Insets(3, 10, 3, 10);

		JPanel primeiraLinha = new JPanel(layout);
		primeiraLinha.add(lblCpfCnpj, col1);
		primeiraLinha.add(txtCpfCnpj, col2);
		
		JPanel segundaLinha = new JPanel(layout);
		segundaLinha.add(lblNome, col1);
		segundaLinha.add(txtNome, col2);

		JPanel teceiraLinha = new JPanel(layout);
		teceiraLinha.add(lblId_Cia, col1);
		teceiraLinha.add(txtId_Cia, col2);

		JPanel quartaLinha = new JPanel(layout);
		quartaLinha.add(lblParametros, col1);
		quartaLinha.add(txtParametros, col2);
		
		JPanel quintaLinha = new JPanel(new GridLayout(1, 2));
		quintaLinha.add(btnGerarControle);
		quintaLinha.add(btnBradescor);
		
		this.add(primeiraLinha);
		this.add(segundaLinha);
		this.add(teceiraLinha);
		this.add(quartaLinha);
		this.add(quintaLinha);
		
		final JanelaPrincipal janelaPrincipal = this;

		btnGerarControle.addActionListener(new ActionListener() {
			
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

		
		lblParametros.setText(resources.getString(MessagesUtil.PARAMETROS_LABEL_KEY));
		txtParametros.setEditable(false);
		
		btnGerarControle.setText(resources.getString(MessagesUtil.GERAR_CODIGO_LABEL_KEY));
		btnBradescor.setText(resources.getString(MessagesUtil.BRADESCOR_LABEL_KEY));
		
		

	}

	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {
		// TODO Auto-generated method stub
		
	}
	
}
