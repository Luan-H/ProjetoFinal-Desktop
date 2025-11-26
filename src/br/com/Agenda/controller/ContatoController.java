package br.com.Agenda.controller;

import com.google.gson.reflect.TypeToken;
import br.com.Agenda.model.Contato;
import br.com.Agenda.util.JsonUtil;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContatoController {
    private static final String ARQUIVO = "data/contatos.json";
    private static final Type TIPO = new TypeToken<ArrayList<Contato>>(){}.getType();

    // Filtra para mostrar apenas os do usuário logado
    public List<Contato> listarPorUsuario(String loginUsuario) {
        List<Contato> todos = JsonUtil.carregar(ARQUIVO, TIPO);
        return todos.stream()
                .filter(c -> c.getLoginDono().equals(loginUsuario))
                .collect(Collectors.toList());
    }

    public void salvar(Contato contato) {
        List<Contato> todos = JsonUtil.carregar(ARQUIVO, TIPO);

        if (contato.getId() == 0) {
            // --- LÓGICA DE ID AUTOMÁTICO ---
            // Pega o maior ID existente em TODA a lista (de todos usuários)
            int novoId = todos.stream()
                    .mapToInt(Contato::getId)
                    .max()
                    .orElse(0) + 1;
            
            contato.setId(novoId);
            todos.add(contato);
        } else {
            // --- EDIÇÃO ---
            for (int i = 0; i < todos.size(); i++) {
                if (todos.get(i).getId() == contato.getId()) {
                    todos.set(i, contato); // Substitui o antigo pelo novo
                    break;
                }
            }
        }
        JsonUtil.salvar(todos, ARQUIVO);
    }

    public void excluir(int idContato) {
        List<Contato> todos = JsonUtil.carregar(ARQUIVO, TIPO);
        todos.removeIf(c -> c.getId() == idContato);
        JsonUtil.salvar(todos, ARQUIVO);
    }
}