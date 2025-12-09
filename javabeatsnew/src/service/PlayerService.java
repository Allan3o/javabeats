package service;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import Models.Musica;
import java.io.File;

public class PlayerService {

    private MediaPlayer player;

    public void tocar(Musica musica) {
        parar();

        // Converte caminho para URI válida
        File arquivo = new File(musica.getCaminhoArquivo());
        Media media = new Media(arquivo.toURI().toString());

        player = new MediaPlayer(media);
        player.play();
    }

    public void pausar() {
        if (player != null) {
            player.pause();
        }
    }

    public void parar() {
        if (player != null) {
            player.stop();
            player.dispose();
            player = null;
        }
    }

    public void setVolume(double volume) {
        if (player != null) {
            player.setVolume(volume);
        }
    }
}


