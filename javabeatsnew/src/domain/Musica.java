package domain;

public class Musica {
    private final String nome;
    private final String caminho;

    public Musica(String nome, String caminho) {
        this.nome = nome;
        this.caminho = caminho;
    }

    public String getNome() { return nome; }
    public String getCaminho() { return caminho; }

    @Override
    public String toString() {
        return nome;
    }
}


