import java.util.ArrayList;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Lista que guarda todos os usuários cadastrados
        ArrayList<Usuario> usuarios = new ArrayList<>();

        while (true) {

            System.out.println("\n===== JAVABEATS =====");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Login");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma das opções: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            // Guardar usuário na memória

            if (opcao == 1) {
                System.out.println("\n=== Cadastro de Usuário ===");

                System.out.print("Digite seu nome: ");
                String nome = scanner.nextLine();

                System.out.print("Digite seu e-mail: ");
                String email = scanner.nextLine();

                System.out.print("Digite sua senha: ");
                String senha = scanner.nextLine();

                int id = usuarios.size() + 1;

                Usuario novoUsuario = new Usuario(id, nome, email, senha);

                usuarios.add(novoUsuario);
                System.out.println("\nUsuário cadastrado com sucesso!");
                System.out.println("ID: " + id);
                System.out.println("Nome: " + nome);
                System.out.println("E-mail: " + email);
            }

            // Login do usuário

            else if (opcao == 2) {
                System.out.println("\n=== Tela de Login ===");

                System.out.print("Email: ");
                String emailLogin = scanner.nextLine();

                System.out.print("Senha: ");
                String senhaLogin = scanner.nextLine();

                Usuario usuarioEncontrado = null;

                // Procura usuário na lista
                for (Usuario u : usuarios) {
                    if (u.getEmail().equals(emailLogin) && u.getSenha().equals(senhaLogin)) {
                        usuarioEncontrado = u;
                        break;
                    }
                }

                if (usuarioEncontrado == null) {
                    System.out.println("\n  Usuário não encontrado ou senha incorreta.");
                } else {
                    System.out.println("\nLogin realizado com sucesso! Bem-vindo, " + usuarioEncontrado.getNome());
                    TelaInicial.mostrarMenu(scanner, usuarioEncontrado);

                }
            }

            // Sair
            else if (opcao == 0) {
                System.out.println("\nSistema encerrado.");
                break;
            }

            else {
                System.out.println("\nOpção inválida. Tente novamente.");
            }
        }

        scanner.close();

    }

}
