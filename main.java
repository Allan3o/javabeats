import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=====JAVABAETS=2====");
        System.out.println("1 - Cadastrar usuario");
        System.out.println("2 - Login");
        System.out.println("0 - Sair");
        System.out.println("Escollha uma da opçoes acima: ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao == 1) {
            System.out.println("\n=====Cadastro de Usuário");

            System.out.println("Digite o seu nome: ");
            String nome = scanner.nextLine();

            System.out.println("Digite o seu E-mail: ");
            String email = scanner.nextLine();

            System.out.println("Digite sua Senha: ");
            String senha = scanner.nextLine();

            Usuario usuario = new Usuario(1, nome, email, senha);

            System.out.println("\nUsuário cadastrado com sucesso!");
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Email: " + usuario.getEmail());
        }

        if (opcao == 2) {
            System.out.println("Tela de login em construção...");
        }

        if (opcao == 0) {
            System.out.println("Sistema encerrado.");
        }

    }

}