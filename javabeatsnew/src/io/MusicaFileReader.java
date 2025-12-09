package io;

import Models.Musica;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MusicaFileReader {

    public List<Musica> carregarMusicasDaPasta(String caminhoPasta) {
        List<Musica> musicas = new ArrayList<>();
        File pasta = new File(caminhoPasta);

        if (!pasta.exists() || !pasta.isDirectory()) {
            return musicas; // pasta inválida, lista vazia
        }

        for (File arquivo : pasta.listFiles()) {
            if (arquivo.getName().toLowerCase().endsWith(".mp3")) {

                Musica musica = new Musica(
                    arquivo.getName(),   // título
                    "Artista desconhecido",
                    arquivo.toURI().toString() // caminho em formato de URL
                );

                musicas.add(musica);
            }
        }

        return musicas;
    }
}
