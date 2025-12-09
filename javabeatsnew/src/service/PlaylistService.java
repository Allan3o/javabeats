package service;

import Models.Musica;
import Models.Playlist;
import java.util.ArrayList;
import java.util.List;

public class PlaylistService {

    private final List<Playlist> playlists = new ArrayList<>();

    public Playlist criarPlaylist(String nome) {
        Playlist playlist = new Playlist(nome);
        playlists.add(playlist);
        return playlist;
    }

  
    public List<Playlist> listarPlaylists() {
        return new ArrayList<>(playlists); 
    }

    
    public boolean adicionarMusica(String nomePlaylist, Musica musica) {
        Playlist pl = buscarPlaylist(nomePlaylist);
        if (pl == null) return false;
        pl.getMusicas().add(musica);
        return true;
    }

   
    public boolean removerMusica(String nomePlaylist, String nomeMusica) {
        Playlist pl = buscarPlaylist(nomePlaylist);
        if (pl == null) return false;

        return pl.getMusicas().removeIf(m -> m.getTitulo().equalsIgnoreCase(nomeMusica));
    }

   
    private Playlist buscarPlaylist(String nome) {
        return playlists.stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }
}
