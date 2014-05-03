package br.com.allanlarangeiras.geradorcontrole.gui;

import static br.com.allanlarangeiras.geradorcontrole.util.ValidadorUtil.isInteiroValido;

import java.awt.GridLayout;
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
import br.com.allanlarangeiras.geradorcontrole.util.Config;
import br.com.allanlarangeiras.geradorcontrole.util.MessagesUtil;

public class JanelaPrincipal extends JFrame {

	private JLabel lblCpfCnpj = new JLabel();
	private JTextField txtCpfCnpj = new JTextField();
	
	private JLabel lblCtrl = new JLabel();
	private JTextField txtCtrl = new JTextField();

	private JButton btnGerarCodigo = new JButton();
	
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
			txtCtrl.setText(geradorControle.gerarCodigoControle(txtCpfCnpj.getText()));
			
		} else {
			JOptionPane.showMessageDialog(null, mensagens.toString());
			
		}
	}

	private boolean componentesEstaoValidos() {
		if (!isInteiroValido(txtCpfCnpj.getText()) && txtCpfCnpj.getText().isEmpty()) {
			mensagens.append(resources.getString(MessagesUtil.CPF_CNPJ_VALIDATION_ERROR_INVALID_KEY));
			return false;
		}
		return true;
	}

	private void inicializarLayout() {
		this.setResizable(false);
		this.setTitle(Config.APP_TITLE);
		this.setLayout(new GridLayout(3, 1));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 100);
		this.setLocationRelativeTo(null);
		
		JPanel primeiraLinha = new JPanel(new GridLayout(1,2));
		primeiraLinha.add(lblCpfCnpj);
		primeiraLinha.add(txtCpfCnpj);
		
		JPanel segundaLinha = new JPanel(new GridLayout(1,2));
		segundaLinha.add(lblCtrl);
		segundaLinha.add(txtCtrl);
		
		this.add(primeiraLinha);
		this.add(segundaLinha);
		this.add(btnGerarCodigo);
		
	}

	private void inicializarValores() {
		lblCpfCnpj.setText(resources.getString(MessagesUtil.CPF_CNPJ_LABEL_KEY));
		txtCpfCnpj.setText(resources.getString(MessagesUtil.CPF_CNPJ_DEFAULT_VALUE_KEY));
		
		lblCtrl.setText(resources.getString(MessagesUtil.CTRL_LABEL_KEY));
		txtCtrl.setEditable(false);
		
		btnGerarCodigo.setText(resources.getString(MessagesUtil.GERAR_CODIGO_LABEL_KEY));
		
		final JanelaPrincipal janelaPrincipal = this;
		
		btnGerarCodigo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				janelaPrincipal.gerarControle();
				
			}
		});
	}	
	
}
