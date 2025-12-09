package io;

import java.nio.file.Files;
import java.nio.file.Paths;

public class CarregaDados {

    public String carregar(String caminho) {
        try {
            return new String(Files.readAllBytes(Paths.get(caminho)));
        } catch (Exception e) {
            System.err.println("Erro ao carregar arquivo: " + caminho);
            return null;
        }
    }
}
