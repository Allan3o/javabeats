import java.util.ArrayList;
import java.util.Scanner;

public class TelaInicial {

    public static void mostrarMenu(Scanner scanner, Usuario usuarioLogado) {

        while (true) {
            System.out.println("\n===== TELA INICIAL =====");
            System.out.println("Bem-vindo, " + usuarioLogado.getNome());
            System.out.println("1 - Minhas Playlists");
            System.out.println("2 - Músicas Recentes");
            System.out.println("3 - Criar Playlist");
            System.out.println("4 - Remover Playlists");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                System.out.println("\n===== Minhas Playlist =====");

                if (usuarioLogado.getPlaylists().isEmpty()) {
                    System.out.println(" Você ainda não criou nenhuma playlist");
                } else {
                    for (Playlist p : usuarioLogado.getPlaylists()) {
                        System.out.println("ID: " + p.getId() + " | " + p.getNome() + " | " + p.getDescricao());
                    }
                }
            } else if (opcao == 2) {
                System.out.println("\nFunção de músicas recentes em construção...");
            } else if (opcao == 3) {
                System.out.println("\n===== Criar Playlist =====");

                System.out.println("Nome da playlist: ");
                String nomePlaylist = scanner.nextLine();

                System.out.println("Descrição: ");
                String descricaoPlaylist = scanner.nextLine();

                int id = usuarioLogado.getPlaylists().size() + 1;

                Playlist nova = new Playlist(id, nomePlaylist, descricaoPlaylist);
                usuarioLogado.adicionarPlaylist(nova);

                System.out.println("\nPlaylist criada com sucesso!");
            }

            else if (opcao == 4) {
                System.out.println("\n===== Remover Playlist =====");

                if (usuarioLogado.getPlaylists().isEmpty()) {
                    System.out.println("Você não possui nenhuma playlist.");
                } else {

                    System.out.println("\n===== Minhas Playlists =====");
                    ArrayList<Playlist> listas = usuarioLogado.getPlaylists();

                    for (int i = 0; i < listas.size(); i++) {
                        Playlist p = listas.get(i);
                        System.out.println((i + 1) + " - " + p.getNome() + " | " + p.getDescricao());
                    }

                    System.out.print("\nDigite o número da playlist que deseja remover: ");
                    int numero = scanner.nextInt();
                    scanner.nextLine();

                    if (numero < 1 || numero > listas.size()) {
                        System.out.println("Número inválido.");
                    } else {
                        listas.remove(numero - 1);
                        System.out.println("Playlist removida com sucesso!");
                    }
                }
            }

            else if (opcao == 0) {
                System.out.println("\nVocê saiu da conta.");
                break;
            } else {
                System.out.println("\nOpção inválida. Tente novamente.");
            }
        }
    }
}