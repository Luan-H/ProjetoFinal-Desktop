package br.com.Agenda.view;

import br.com.Agenda.controller.UsuarioController;
import javax.swing.*;
import java.awt.*;

public class TelaPerfil extends JFrame {
	private JPasswordField fieldSenhaNova = new JPasswordField();
	private JPasswordField fieldConfirma = new JPasswordField();
	private UsuarioController controller;
	private String usuarioLogado;
	JLabel labelSenhaNova = new JLabel();
	JLabel labelConfirma = new JLabel();
	JLabel labelInfo = new JLabel();
	JButton btnSalvar = new JButton();
	JButton btnFechar = new JButton();
	public TelaPerfil(String usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
		this.controller = new UsuarioController();

		// Configuração da janela
		setTitle("Meu perfil");
		setSize(400, 300);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Label
		labelInfo.setBounds(145, 30, 150, 40);
		labelInfo.setText("Usuário: " + usuarioLogado);
		labelInfo.setFont(new Font("Arial", Font.BOLD, 16));
		add(labelInfo);
		
		// Senhas
		labelSenhaNova.setText("Nova Senha:");
		labelSenhaNova.setBounds(80, 90, 80, 30);
		fieldSenhaNova.setBounds(160, 90, 130, 30);
		add(fieldSenhaNova);
		add(labelSenhaNova);
		
		labelConfirma.setText("Confime sua senha:");
		labelConfirma.setBounds(40, 140, 120, 30);
		fieldConfirma.setBounds(160, 140, 130, 30);
		add(fieldConfirma);
		add(labelConfirma);
		
		// Botões
		btnSalvar.setBounds(70, 190, 130, 25);
		btnSalvar.setText("Alterar Senha");
		btnFechar.setBounds(210, 190, 100, 25);
		btnFechar.setText("Fechar");
		add(btnFechar);
		add(btnSalvar);
		
		// Eventos
		btnSalvar.addActionListener(e -> {
			String nova = new String(fieldSenhaNova.getPassword());
			String confirma = new String(fieldConfirma.getPassword());

			if (nova.isEmpty()) {
				JOptionPane.showMessageDialog(this, "A senha não pode ser vazia.");
				return;
			}

			if (!nova.equals(confirma)) {
				JOptionPane.showMessageDialog(this, "As senhas não conferem!");
				return;
			}

			if (controller.alterarSenha(usuarioLogado, nova)) {
				JOptionPane.showMessageDialog(this, "Senha alterada com sucesso!");
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Erro ao alterar senha.");
			}
		});

		btnFechar.addActionListener(e -> dispose());
	}
}