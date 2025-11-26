package br.com.Agenda.model;

public class Contato {
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String loginDono; // Vincula o contato ao usuário

    // Construtor completo
    public Contato(int id, String nome, String email, String telefone, String loginDono) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.loginDono = loginDono;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; } // Necessário para o auto-incremento
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public String getLoginDono() { return loginDono; }
}