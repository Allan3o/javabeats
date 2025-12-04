package modelos;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private final int id;
    private final String nome;
    private final String descricao;
    private final List<Musica> musicas;

    public Playlist(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = (descricao == null ? "" : descricao);
        this.musicas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void adicionarMusica(Musica musica) {
        if (musica != null) {
            musicas.add(musica);
        }
    }

    @Override
    public String toString() {
        return id + " - " + nome + (descricao.isEmpty() ? "" : " | " + descricao);
    }
}
