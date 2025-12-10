package src.main.teste;

import java.io.File;

import src.main.play.PlayerMusica;

public class TesteAudio {
    public static void main(String[] args) {

        try {
            File arquivo = new File("/home/allan/Área de trabalho/javabets/Musicas/Queen – Bohemian Rhapsody.mp3");

            String caminhoCorrigido = arquivo.toURI().toString();

            System.out.println("URI gerada: " + caminhoCorrigido);

            PlayerMusica player = new PlayerMusica();
            player.tocar(caminhoCorrigido);

        } catch (Exception e) {
            System.out.println("Erro ao tocar música: " + e.getMessage());
        }
    }
}
