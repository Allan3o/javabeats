package src.main.modelos;

public class Musica {

    private static int contadorGlobal = 1;

    private int id;
    private String nome;
    private String caminho;

    public Musica(String nome, String caminho) {
        this.id = contadorGlobal++;   
        this.nome = nome;
        this.caminho = caminho;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCaminho() {
        return caminho;
    }

    @Override
    public String toString() {
        return id + " - " + nome;
    }
}
