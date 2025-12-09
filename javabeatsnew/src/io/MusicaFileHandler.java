package io;

import Models.Musica;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MusicaFileHandler {

    private final String caminhoArquivo = "musicas.json";

    public void salvarMusicas(List<Musica> musicas) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(caminhoArquivo))) {

            writer.println("[");
            for (int i = 0; i < musicas.size(); i++) {
                Musica m = musicas.get(i);
                writer.println("  {");
                writer.println("    \"titulo\": \"" + m.getTitulo() + "\",");
                writer.println("    \"artista\": \"" + m.getArtista() + "\",");
                writer.println("    \"caminho\": \"" + m.getCaminhoArquivo() + "\"");
                writer.println("  }" + (i < musicas.size() - 1 ? "," : ""));
            }
            writer.println("]");

        } catch (IOException e) {
            System.out.println("Erro ao salvar músicas: " + e.getMessage());
        }
    }

    public List<Musica> carregarMusicas() {
        List<Musica> musicas = new ArrayList<>();
        File file = new File(caminhoArquivo);

        if (!file.exists()) return musicas;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String linha;
            String titulo = "", artista = "", caminho = "";

            while ((linha = reader.readLine()) != null) {

                linha = linha.trim();

                if (linha.startsWith("\"titulo\"")) {
                    titulo = extrairValor(linha);
                } else if (linha.startsWith("\"artista\"")) {
                    artista = extrairValor(linha);
                } else if (linha.startsWith("\"caminho\"")) {
                    caminho = extrairValor(linha);

                    // objeto completo — adiciona
                    musicas.add(new Musica(titulo, artista, caminho));
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao carregar músicas: " + e.getMessage());
        }

        return musicas;
    }

    private String extrairValor(String linha) {
        return linha.split(":")[1]
                .replace("\"", "")
                .replace(",", "")
                .trim();
    }
}
