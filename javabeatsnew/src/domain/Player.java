package domain;

public class Player {

    private Musica atual;
    private boolean tocando;

    public void tocar(Musica musica) {
        this.atual = musica;
        this.tocando = true;
    }

    public void pausar() {
        this.tocando = false;
    }

    public Musica getAtual() { return atual; }
    public boolean isTocando() { return tocando; }
}
