package service;

import Models.Musica;
import io.MusicaFileHandler;

import java.util.ArrayList;
import java.util.List;

public class MusicaService {

    private final List<Musica> musicas = new ArrayList<>();
    private final MusicaFileHandler fileHandler = new MusicaFileHandler();

    public MusicaService() {
        musicas.addAll(fileHandler.carregarMusicas());
    }

    public void adicionarMusica(String titulo, String artista, String caminho) {
        Musica m = new Musica(titulo, artista, caminho);
        musicas.add(m);
        salvar();
    }

    public List<Musica> listarMusicas() {
        return musicas;
    }

    public void removerMusica(String titulo) {
        musicas.removeIf(m -> m.getTitulo().equalsIgnoreCase(titulo));
        salvar();
    }

    public Musica buscarPorTitulo(String titulo) {
        return musicas.stream()
                .filter(m -> m.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);
    }

    private void salvar() {
        fileHandler.salvarMusicas(musicas);
    }
}
