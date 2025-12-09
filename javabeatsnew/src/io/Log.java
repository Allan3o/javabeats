package io;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Log {

    public void registrar(String mensagem) {
        try (FileWriter fw = new FileWriter("app.log", true)) {
            fw.write(LocalDateTime.now() + " - " + mensagem + "\n");
        } catch (IOException e) {
            System.err.println("Erro ao escrever log.");
        }
    }
}
