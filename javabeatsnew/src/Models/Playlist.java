package Models;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private String nome;
    private List<Musica> musicas = new ArrayList<>();

    public Playlist(String nome) {
        this.nome = nome;
    }

    public void adicionarMusica(Musica musica) {
        musicas.add(musica);
    }

    public void removerMusica(Musica musica) {
        musicas.remove(musica);
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public String getNome() {
        return nome;
    }
}
