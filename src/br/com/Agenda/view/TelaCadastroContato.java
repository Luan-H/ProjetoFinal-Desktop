package br.com.Agenda.view;

import br.com.Agenda.controller.ContatoController;
import br.com.Agenda.model.Contato;
import javax.swing.*;
import java.awt.*;

public class TelaCadastroContato extends JFrame {
	private JTextField fieldNome, fieldEmail, fieldTelefone;
	private JLabel labelNome = new JLabel();
	private JLabel labelEmail = new JLabel();
	private JLabel labelTelefone = new JLabel();
	private ContatoController controller;
	private TelaPrincipal telaPrincipal;
	private String usuarioLogado;
	private Contato contatoEmEdicao;
	JButton btnSalvar = new JButton("Salvar");
	JButton btnCancelar = new JButton("Cancelar");
	
	public TelaCadastroContato(TelaPrincipal telaPrincipal, String usuarioLogado, Contato contato) {
		this.telaPrincipal = telaPrincipal;
		this.usuarioLogado = usuarioLogado;
		this.contatoEmEdicao = contato;
		this.controller = new ContatoController();

		setTitle(contato == null ? "Novo Contato" : "Editar Contato");
		setSize(300, 400);
		setLocationRelativeTo(telaPrincipal);
		setLayout(null);
		
		// Nome
		labelNome.setText("Nome:");
		labelNome.setBounds(30, 60, 60, 30);
		labelNome.setFont(new Font("Arial", Font.PLAIN, 14));
		fieldNome = new JTextField(contato != null ? contato.getNome() : "");
		fieldNome.setBounds(90, 60, 160, 30);
		add(fieldNome);
		add(labelNome);

		// Email
		labelEmail.setText("Email:");
		labelEmail.setBounds(30, 100, 60, 30);
		labelEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		fieldEmail = new JTextField(contato != null ? contato.getEmail() : "");
		fieldEmail.setBounds(90, 100, 160, 30);
		add(fieldEmail);
		add(labelEmail);
		
		// Telefone
		labelTelefone.setText("Telefone:");
		labelTelefone.setBounds(20, 140, 70, 30);
		labelTelefone.setFont(new Font("Arial", Font.PLAIN, 14));
		fieldTelefone = new JTextField(contato != null ? contato.getTelefone() : "");
		fieldTelefone.setBounds(90, 140, 160, 30);
		add(fieldTelefone);
		add(labelTelefone);
		
		
		// Botões
		btnSalvar.setBounds(50, 190, 90, 30);
		btnSalvar.setText("Salvar");
		btnSalvar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCancelar.setBounds(160, 190, 100, 30);
		btnCancelar.setText("Cancelar");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
		add(btnSalvar);
		add(btnCancelar);

		// Ação Salvar
		btnSalvar.addActionListener(e -> {
			String nome = fieldNome.getText();
			String email = fieldEmail.getText();
			String fone = fieldTelefone.getText();

			if (nome.isEmpty()) {
				JOptionPane.showMessageDialog(this, "O nome é obrigatório.");
				return;
			}

			Contato novoContato;
			if (contatoEmEdicao == null) {
				// ID 0 indica para o controller gerar um novo ID
				novoContato = new Contato(0, nome, email, fone, usuarioLogado);
			} else {
				// Mantém o ID original para atualização
				novoContato = new Contato(contatoEmEdicao.getId(), nome, email, fone, usuarioLogado);
			}

			controller.salvar(novoContato);

			// Atualiza a tabela da tela pai e fecha esta janela
			telaPrincipal.carregarTabela();
			dispose();
		});

		btnCancelar.addActionListener(e -> dispose());
	}
}