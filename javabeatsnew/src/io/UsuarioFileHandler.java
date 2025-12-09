package io;

import Models.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioFileHandler {

    private final String caminhoArquivo = "usuarios.json";

    // Salva a lista de usuários
    public void salvarUsuarios(List<Usuario> usuarios) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(caminhoArquivo))) {

            writer.println("[");
            for (int i = 0; i < usuarios.size(); i++) {
                Usuario u = usuarios.get(i);
                writer.println("  {");
                writer.println("    \"nome\": \"" + u.getNome() + "\",");
                writer.println("    \"email\": \"" + u.getEmail() + "\"");
                writer.println("  }" + (i < usuarios.size() - 1 ? "," : ""));
            }
            writer.println("]");

        } catch (IOException e) {
            System.out.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }

    // Carrega usuários do arquivo
    public List<Usuario> carregarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        File file = new File(caminhoArquivo);

        if (!file.exists()) {
            return usuarios; // nenhum usuário salvo ainda
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String linha;
            String nome = "";
            String email = "";

            while ((linha = reader.readLine()) != null) {

                linha = linha.trim();

                if (linha.startsWith("\"nome\"")) {
                    nome = extrairValor(linha);
                } else if (linha.startsWith("\"email\"")) {
                    email = extrairValor(linha);

                    // Quando encontramos o email, concluímos o objeto
                    usuarios.add(new Usuario(nome, email, ""));
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao carregar usuários: " + e.getMessage());
        }

        return usuarios;
    }

    private String extrairValor(String linha) {
        return linha.split(":")[1]
                    .replace("\"", "")
                    .replace(",", "")
                    .trim();
    }
}
