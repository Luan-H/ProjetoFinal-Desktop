package br.com.Agenda.view;

import br.com.Agenda.controller.UsuarioController;
import javax.swing.*;
import java.awt.*;

public class TelaRegistro extends JFrame {
	private UsuarioController controller;
	JButton btnSalvar = new JButton();
	JButton btnCancelar = new JButton();
	JLabel registerLabel = new JLabel();
	JLabel loginLabel = new JLabel();
	JLabel passwordLabel = new JLabel();
	JTextField loginField = new JTextField();
	JPasswordField passwordField = new JPasswordField();

	public TelaRegistro() {
		controller = new UsuarioController();
		setTitle("Registro");
		setSize(400, 300);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Registro
		registerLabel.setBounds(145, 30, 110, 40);
		registerLabel.setText("Registro");
		registerLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		add(registerLabel);

		// Login
		loginLabel.setBounds(100, 90, 70, 30);
		loginLabel.setText("Usuario:");
		loginLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		loginField.setBounds(160, 90, 130, 30);
		add(loginField);
		add(loginLabel);

		// Senha
		passwordLabel.setBounds(110, 140, 50, 30);
		passwordLabel.setText("Senha:");
		passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		passwordField.setBounds(160, 140, 130, 30);
		add(passwordField);
		add(passwordLabel);
	

		// Botão
		btnSalvar.setBounds(100, 190, 100, 25);
		btnSalvar.setText("Registrar");
		btnSalvar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCancelar.setBounds(210, 190, 100, 25);
		btnCancelar.setText("Cancelar");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
		add(btnSalvar);
		add(btnCancelar);
		setVisible(true);

		// Eventos
		btnSalvar.addActionListener(e -> {
			String login = loginField.getText();
			String senha = new String(passwordField.getPassword());

			if (login.isEmpty() || senha.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
				return;
			}

			if (controller.cadastrar(login, senha)) {
				JOptionPane.showMessageDialog(this, "Usuário criado com sucesso!");
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Usuário já existe!");
			}
		});

		btnCancelar.addActionListener(e -> dispose());
	}
}