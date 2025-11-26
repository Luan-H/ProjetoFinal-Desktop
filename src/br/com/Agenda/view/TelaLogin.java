package br.com.Agenda.view;

import br.com.Agenda.controller.UsuarioController;

import javax.swing.*;
import java.awt.*;

public class TelaLogin extends JFrame {
	JPasswordField txtSenha;
	UsuarioController controller;
	JPanel panelBotoes = new JPanel();
	JButton btnEntrar = new JButton();
	JButton btnRegistrar = new JButton();
	JLabel welcomeLabel = new JLabel();
	JLabel loginLabel = new JLabel();
	JLabel passwordLabel = new JLabel();
	JTextField loginField = new JTextField();
	JPasswordField passwordField = new JPasswordField();

	public TelaLogin() {
		controller = new UsuarioController();
		// Configuração da janela
		setTitle("Login");
		setSize(400, 300);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Bem vindo
		welcomeLabel.setBounds(145, 30, 110, 40);
		welcomeLabel.setText("Bem-Vindo!");
		welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		add(welcomeLabel);

		// Login
		loginLabel.setText("Login: ");
		loginLabel.setBounds(110, 90, 50, 30);
		loginLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		loginField.setBounds(160, 90, 130, 30);
		add(loginField);
		add(loginLabel);

		// Senha
		passwordLabel.setText("Senha: ");
		passwordLabel.setBounds(110, 140, 50, 30);
		passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		passwordField.setBounds(160, 140, 130, 30);
		add(passwordLabel);
		add(passwordField);

		// Botão
		btnEntrar.setBounds(100, 190, 100, 25);
		btnEntrar.setText("Login");
		btnEntrar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnRegistrar.setBounds(210, 190, 100, 25);
		btnRegistrar.setText("Registrar");
		btnRegistrar.setFont(new Font("Arial", Font.PLAIN, 14));
		add(btnEntrar);
		add(btnRegistrar);

		// Eventos
		btnEntrar.addActionListener(e -> logar());
		btnRegistrar.addActionListener(e -> {
			new TelaRegistro().setVisible(true);
		});
		setVisible(true);
	}

	private void logar() {
		String login = loginField.getText();
		String senha = new String(passwordField.getPassword());

		if (controller.autenticar(login, senha)) {
			new TelaPrincipal(login).setVisible(true);
			this.dispose(); // Fecha a tela de login
		} else {
			JOptionPane.showMessageDialog(this, "Login ou senha inválidos!");
		}
	}
}