package modelos;

import java.util.ArrayList;

public class Usuario {

    private int id;
    private String nome;
    private String email;
    private String senha;

    private ArrayList<Playlist> playlists = new ArrayList<>();

    public Usuario(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void adicionarPlaylist(Playlist playlist) {
        if (playlist != null) {
            playlists.add(playlist);
        }
    }
}
