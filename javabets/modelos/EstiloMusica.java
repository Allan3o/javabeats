package modelos;

import java.util.ArrayList;

public class EstiloMusica {

    private int id;
    private String nome;
    private ArrayList<Musica> musicas = new ArrayList<>();

    public EstiloMusica(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    public void adicionarMusica(Musica m) {
        if (m != null) musicas.add(m);
    }
}
