package domain;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private final List<Musica> musicas = new ArrayList<>();

    public void adicionar(Musica m) {
        musicas.add(m);
    }

    public List<Musica> getMusicas() {
        return musicas;
    }
}
