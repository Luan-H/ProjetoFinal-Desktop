package br.com.Agenda.view;

import br.com.Agenda.controller.ContatoController;
import br.com.Agenda.model.Contato;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaPrincipal extends JFrame {
	private String usuarioLogado;
	private JTable tabela;
	private DefaultTableModel modeloTabela;
	private ContatoController controller;
	JPanel panelBotoes = new JPanel();
	JButton btnAdicionar = new JButton("Adicionar");
	JButton btnEditar = new JButton("Editar");
	JButton btnExcluir = new JButton("Excluir");
	JButton btnSair = new JButton("Sair");
	JButton btnPerfil = new JButton("Meu Perfil");

	public TelaPrincipal(String usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
		this.controller = new ContatoController();

		setTitle("Agenda de Contatos - Usuário: " + usuarioLogado);
		setSize(600, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		// --- Configuração da Tabela ---
		// Colunas: ID, Nome, Email, Telefone
		modeloTabela = new DefaultTableModel(new Object[] { "ID", "Nome", "Email", "Telefone" }, 0) {
			@Override 
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tabela = new JTable(modeloTabela);
		add(new JScrollPane(tabela), BorderLayout.CENTER);

		panelBotoes.add(btnAdicionar);
		panelBotoes.add(btnEditar);
		panelBotoes.add(btnExcluir);
		panelBotoes.add(btnSair);
		panelBotoes.add(btnPerfil);
		add(panelBotoes, BorderLayout.SOUTH);

		// Eventos
		btnAdicionar.addActionListener(e -> {
			// Passa 'null' para indicar que é um novo contato
			new TelaCadastroContato(this, usuarioLogado, null).setVisible(true);
		});

		btnEditar.addActionListener(e -> {
			int linhaSelecionada = tabela.getSelectedRow();
			if (linhaSelecionada >= 0) {
				// Recupera dados da tabela
				int id = (int) tabela.getValueAt(linhaSelecionada, 0);
				String nome = (String) tabela.getValueAt(linhaSelecionada, 1);
				String email = (String) tabela.getValueAt(linhaSelecionada, 2);
				String fone = (String) tabela.getValueAt(linhaSelecionada, 3);

				// Cria objeto temporário e abre tela de edição
				Contato contato = new Contato(id, nome, email, fone, usuarioLogado);
				new TelaCadastroContato(this, usuarioLogado, contato).setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, "Selecione um contato para editar.");
			}
		});

		btnExcluir.addActionListener(e -> {
			int linha = tabela.getSelectedRow();
			if (linha >= 0) {
				int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza?", "Excluir", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					int id = (int) tabela.getValueAt(linha, 0);
					controller.excluir(id);
					carregarTabela();
				}
			} else {
				JOptionPane.showMessageDialog(this, "Selecione um contato para excluir.");
			}
		});

		btnSair.addActionListener(e -> {
			new TelaLogin().setVisible(true);
			dispose();
		});
		
		btnPerfil.addActionListener(e -> {
		    new TelaPerfil(usuarioLogado).setVisible(true);
		});

		carregarTabela();
	}

	public void carregarTabela() {
		modeloTabela.setRowCount(0);
		List<Contato> lista = controller.listarPorUsuario(usuarioLogado);

		for (Contato c : lista) {
			modeloTabela.addRow(new Object[] { c.getId(), c.getNome(), c.getEmail(), c.getTelefone() });
		}
	}
}