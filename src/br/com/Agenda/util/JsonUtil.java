package br.com.Agenda.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    // Gson configurado para deixar o JSON bonitinho (indentado)
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Salva qualquer lista em um arquivo
    public static <T> void salvar(List<T> dados, String caminho) {
        try {
            // Garante que a pasta 'data' exista
            Files.createDirectories(Paths.get("data"));
            try (Writer writer = new FileWriter(caminho)) {
                gson.toJson(dados, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao salvar arquivo: " + caminho);
        }
    }

    // Carrega qualquer lista de um arquivo
    public static <T> List<T> carregar(String caminho, Type tipoLista) {
        if (!Files.exists(Paths.get(caminho))) {
            return new ArrayList<>();
        }
        try (Reader reader = new FileReader(caminho)) {
            List<T> lista = gson.fromJson(reader, tipoLista);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}