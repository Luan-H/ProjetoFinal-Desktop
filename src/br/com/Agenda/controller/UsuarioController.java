package br.com.Agenda.controller;

import com.google.gson.reflect.TypeToken;
import br.com.Agenda.model.Usuario;
import br.com.Agenda.util.JsonUtil;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UsuarioController {
    private static final String ARQUIVO = "data/usuarios.json";
    private static final Type TIPO = new TypeToken<ArrayList<Usuario>>(){}.getType();

    public boolean cadastrar(String login, String senha) {
        List<Usuario> usuarios = JsonUtil.carregar(ARQUIVO, TIPO);

        for (Usuario u : usuarios) {
            if (u.getLogin().equalsIgnoreCase(login)) {
                return false;
            }
        }

        usuarios.add(new Usuario(login, senha));
        JsonUtil.salvar(usuarios, ARQUIVO);
        return true;
    }

    public boolean autenticar(String login, String senha) {
        List<Usuario> usuarios = JsonUtil.carregar(ARQUIVO, TIPO);
        for (Usuario u : usuarios) {
            if (u.getLogin().equals(login) && u.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean alterarSenha(String login, String novaSenha) {
        List<Usuario> usuarios = JsonUtil.carregar(ARQUIVO, TIPO);
        boolean achou = false;

        for (Usuario u : usuarios) {
            if (u.getLogin().equals(login)) {
                u.setSenha(novaSenha);
                achou = true;
                break;
            }
        }

        if (achou) {
            JsonUtil.salvar(usuarios, ARQUIVO);
        }
        return achou;
    }
}