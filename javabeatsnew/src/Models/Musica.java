package Models;


public class Musica {

    private String titulo;
    private String artista;
    private String caminhoArquivo;

    public Musica(String titulo, String artista, String caminhoArquivo) {
        this.titulo = titulo;
        this.artista = artista;
        this.caminhoArquivo = caminhoArquivo;
    }

    public String getTitulo() { return titulo; }
    public String getArtista() { return artista; }
    public String getCaminhoArquivo() { return caminhoArquivo; }

    @Override
    public String toString() {
        return artista + " - " + titulo;
    }
}
