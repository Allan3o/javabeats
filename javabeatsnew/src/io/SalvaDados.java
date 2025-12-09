package io;

import java.nio.file.Files;
import java.nio.file.Paths;

public class SalvaDados {

    public boolean salvar(String caminho, String conteudo) {
        try {
            Files.write(Paths.get(caminho), conteudo.getBytes());
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao salvar arquivo: " + caminho);
            return false;
        }
    }
}
