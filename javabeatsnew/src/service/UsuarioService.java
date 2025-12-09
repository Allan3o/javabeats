package service;

import Models.Usuario;
import io.UsuarioFileHandler;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    private final List<Usuario> usuarios = new ArrayList<>();
    private final UsuarioFileHandler fileHandler = new UsuarioFileHandler();

    public UsuarioService() {
        usuarios.addAll(fileHandler.carregarUsuarios());
    }

    public void cadastrarUsuario(String nome, String email) {
        Usuario u = new Usuario(nome, email, "");
        usuarios.add(u);
        salvar();
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    public Usuario buscarPorEmail(String email) {
        return usuarios.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public void removerUsuario(String email) {
        usuarios.removeIf(u -> u.getEmail().equalsIgnoreCase(email));
        salvar();
    }

    private void salvar() {
        fileHandler.salvarUsuarios(usuarios);
    }
}
