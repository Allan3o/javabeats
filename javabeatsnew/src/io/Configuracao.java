package io;

import java.util.Properties;
import java.io.FileInputStream;

public class Configuracao {

    private Properties props = new Properties();

    public void carregar(String caminho) {
        try {
            props.load(new FileInputStream(caminho));
        } catch (Exception e) {
            System.err.println("Não foi possível carregar configurações.");
        }
    }

    public String get(String chave) {
        return props.getProperty(chave);
    }
}
