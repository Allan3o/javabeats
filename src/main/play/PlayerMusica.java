package src.main.play;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class PlayerMusica {

    private MediaPlayer player;

    public PlayerMusica() {
        PlayerInitializer.init();
    }

    public void tocar(String caminho) {
        try {
            File file = new File(caminho);
            String uri = file.toURI().toString();

    

            Media media = new Media(uri);
            player = new MediaPlayer(media);
            player.play();

        } catch (Exception e) {
            System.out.println("Erro ao tocar música: " + e.getMessage());
        }
    }

    public void pausar() {
        if (player != null) player.pause();
    }

    public void continuar() {
        if (player != null) player.play();
    }

    public void parar() {
        if (player != null) player.stop();
    }
}
