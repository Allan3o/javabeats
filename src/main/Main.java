package src.main;

import java.util.ArrayList;
import java.util.Scanner;

import src.main.modelos.Usuario;
import src.main.telas.TelaInicial;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Usuario> usuarios = new ArrayList<>();

        while (true) {
            // Limpa a tela (funciona na maioria dos terminais)
            System.out.print("\033[H\033[2J");
            System.out.flush();

            // Cabeçalho estilizado
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║            J A V A B E A T S           ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║                                        ║");
            System.out.println("║     [1]  Cadastrar usuário             ║");
            System.out.println("║     [2]  Login                         ║");
            System.out.println("║     [0]  Sair                          ║");
            System.out.println("║                                        ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.print("║  Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            System.out.println("╚════════════════════════════════════════╝");

            if (opcao == 1) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                
                System.out.println("╔════════════════════════════════════════╗");
                System.out.println("║        CADASTRO DE USUÁRIO             ║");
                System.out.println("╠════════════════════════════════════════╣");
                System.out.println("║                                        ║");
                
                System.out.print("║  Nome: ");
                String nome = scanner.nextLine();
                
                System.out.print("║  E-mail: ");
                String email = scanner.nextLine();
                
                System.out.print("║  Senha: ");
                String senha = scanner.nextLine();
                
                int id = usuarios.size() + 1;
                Usuario novoUsuario = new Usuario(id, nome, email, senha);
                usuarios.add(novoUsuario);
                
                System.out.println("║                                        ║");
                System.out.println("╠════════════════════════════════════════╣");
                System.out.println("║        CADASTRO CONCLUÍDO!             ║");
                System.out.println("╠════════════════════════════════════════╣");
                System.out.println("║                                        ║");
                System.out.println("║  ID: " + id);
                System.out.println("║  Nome: " + nome);
                System.out.println("║  E-mail: " + email);
                System.out.println("║                                        ║");
                System.out.println("╚════════════════════════════════════════╝");
                
                System.out.print("\nPressione ENTER para continuar...");
                scanner.nextLine();
            }

            else if (opcao == 2) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                
                System.out.println("╔════════════════════════════════════════╗");
                System.out.println("║            TELA DE LOGIN               ║");
                System.out.println("╠════════════════════════════════════════╣");
                System.out.println("║                                        ║");
                
                System.out.print("║  Email: ");
                String emailLogin = scanner.nextLine();
                
                System.out.print("║  Senha: ");
                String senhaLogin = scanner.nextLine();
                
                System.out.println("║                                        ║");
                System.out.println("╠════════════════════════════════════════╣");

                Usuario usuarioEncontrado = null;

                for (Usuario u : usuarios) {
                    if (u.getEmail().equals(emailLogin) && u.getSenha().equals(senhaLogin)) {
                        usuarioEncontrado = u;
                        break;
                    }
                }

                if (usuarioEncontrado == null) {
                    System.out.println("║                                        ║");
                    System.out.println("║     USUÁRIO NÃO ENCONTRADO            ║");
                    System.out.println("║     Ou senha incorreta                ║");
                    System.out.println("║                                        ║");
                    System.out.println("╚════════════════════════════════════════╝");
                    
                    System.out.print("\nPressione ENTER para tentar novamente...");
                    scanner.nextLine();
                } else {
                    System.out.println("║                                        ║");
                    System.out.println("║     LOGIN REALIZADO COM SUCESSO!      ║");
                    System.out.println("║     Bem-vindo, " + usuarioEncontrado.getNome() + "!");
                    System.out.println("║                                        ║");
                    System.out.println("╚════════════════════════════════════════╝");
                    
                    System.out.print("\nCarregando sua biblioteca musical...");
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                    TelaInicial.mostrarMenu(scanner, usuarioEncontrado);
                }
            }

            else if (opcao == 0) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                
                System.out.println("╔════════════════════════════════════════╗");
                System.out.println("║                                        ║");
                System.out.println("║         ATÉ A PRÓXIMA!                 ║");
                System.out.println("║                                        ║");
                System.out.println("║     Obrigado por usar JavaBeats!       ║");
                System.out.println("║                                        ║");
                System.out.println("╚════════════════════════════════════════╝");
                break;
            }

            else {
                System.out.println("╔════════════════════════════════════════╗");
                System.out.println("║                                        ║");
                System.out.println("║         OPÇÃO INVÁLIDA!                ║");
                System.out.println("║     Tente novamente                    ║");
                System.out.println("║                                        ║");
                System.out.println("╚════════════════════════════════════════╝");
                
                System.out.print("\nPressione ENTER para continuar...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }
}