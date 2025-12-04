package telas;

import java.util.ArrayList;
import java.util.Scanner;

import modelos.Usuario;
import modelos.Playlist;
import modelos.Musica;
import modelos.EstiloMusica;
import play.PlayerMusica;

public class TelaInicial {

    private static ArrayList<EstiloMusica> estilosMusicais = new ArrayList<>();

    public static void pausar(Scanner scanner) {
        System.out.print("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }

    private static void carregarEstilos() {
        if (!estilosMusicais.isEmpty()) return;

        String base = "/home/allan/Área de trabalho/javabets/Musicas/";

        EstiloMusica rock = new EstiloMusica(1, "Rock");
        rock.adicionarMusica(new Musica("Bohemian Rhapsody", base + "Queen Bohemian Rhapsody.mp3"));
        rock.adicionarMusica(new Musica("Smells Like Teen Spirit", base + "Nirvana Smells Like Teen Spirit.mp3"));

        EstiloMusica pop = new EstiloMusica(2, "Pop");
        pop.adicionarMusica(new Musica("Wake Me Up", base + "Avicii Wake Me Up.mp3"));
        pop.adicionarMusica(new Musica("Titanium", base + "David Guetta Titanium ft. Sia.mp3"));

        EstiloMusica rap = new EstiloMusica(3, "Rap");
        rap.adicionarMusica(new Musica("Rap God", base + "Eminem Rap God.mp3"));

        EstiloMusica sertanejo = new EstiloMusica(4, "Sertanejo");
        sertanejo.adicionarMusica(new Musica("Marília Mendonça", base + "Marília Mendonça.mp3"));
        sertanejo.adicionarMusica(new Musica("Largado às Traças", base + "Zé Neto e Cristiano LARGADO ÀS TRAÇAS Zé Neto e Cristiano.mp3"));

        EstiloMusica infantil = new EstiloMusica(5, "Infantil");
        infantil.adicionarMusica(new Musica("Gummy Bear", base + "Eu Sou O Gummy Bear Gummy Bear.mp3"));

        estilosMusicais.add(rock);
        estilosMusicais.add(pop);
        estilosMusicais.add(rap);
        estilosMusicais.add(sertanejo);
        estilosMusicais.add(infantil);
    }

    public static void mostrarMenu(Scanner scanner, Usuario usuarioLogado) {

        carregarEstilos();

        while (true) {
            System.out.println("\n===== TELA INICIAL =====");
            System.out.println("\n1Bem-vindo, " + usuarioLogado.getNome());
            System.out.println("1 - Minhas Playlists");
            System.out.println("2 - Músicas Recentes");
            System.out.println("3 - Criar Playlist");
            System.out.println("4 - Remover Playlist");
            System.out.println("5 - Pesquisar Estilos Musicais");
            System.out.println("6 - Tocar Música da Playlist");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                System.out.println("\n===== Minhas Playlists =====");
                if (usuarioLogado.getPlaylists().isEmpty()) {
                    System.out.println("Você ainda não possui playlists.");
                } else {
                    for (Playlist p : usuarioLogado.getPlaylists()) {
                        System.out.println("\nID: " + p.getId() + " | " + p.getNome() + " | " + p.getDescricao());
                        if (!p.getMusicas().isEmpty()) {
                            System.out.println("  Músicas:");
                            for (Musica m : p.getMusicas()) {
                                System.out.println("   - " + m.getId() + " | " + m.getNome());
                            }
                        }
                    }
                }
                pausar(scanner);
            }

            else if (opcao == 2) {
                System.out.println("\nFunção em desenvolvimento...");
                pausar(scanner);
            }

            else if (opcao == 3) {
                System.out.println("\n===== Criar Playlist =====");
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Descrição: ");
                String desc = scanner.nextLine();
                int id = usuarioLogado.getPlaylists().size() + 1;
                usuarioLogado.adicionarPlaylist(new Playlist(id, nome, desc));
                System.out.println("\nPlaylist criada!");
                pausar(scanner);
            }

            else if (opcao == 4) {
                System.out.println("\n===== Remover Playlist =====");
                ArrayList<Playlist> listas = usuarioLogado.getPlaylists();
                if (listas.isEmpty()) {
                    System.out.println("Nenhuma playlist cadastrada.");
                    pausar(scanner);
                    continue;
                }
                for (int i = 0; i < listas.size(); i++) {
                    System.out.println((i + 1) + " - " + listas.get(i).getNome());
                }
                System.out.print("\nEscolha: ");
                int n = scanner.nextInt();
                scanner.nextLine();
                if (n < 1 || n > listas.size()) System.out.println("Opção inválida.");
                else {
                    listas.remove(n - 1);
                    System.out.println("Removida!");
                }
                pausar(scanner);
            }

            else if (opcao == 5) {
                System.out.println("\n===== Estilos Musicais =====");
                for (EstiloMusica e : estilosMusicais) System.out.println(e.getId() + " - " + e.getNome());

                System.out.print("\nEscolha o estilo (0 p/ voltar): ");
                int idEstilo = scanner.nextInt();
                scanner.nextLine();
                if (idEstilo == 0) continue;

                EstiloMusica estilo = estilosMusicais.stream().filter(e -> e.getId() == idEstilo).findFirst().orElse(null);
                if (estilo == null) { System.out.println("Estilo inválido."); pausar(scanner); continue; }

                System.out.println("\n===== Músicas =====");
                for (Musica m : estilo.getMusicas()) System.out.println(m.getId() + " - " + m.getNome());

                System.out.print("\nEscolha a música: ");
                int idMusica = scanner.nextInt();
                scanner.nextLine();

                Musica musica = estilo.getMusicas().stream().filter(m -> m.getId() == idMusica).findFirst().orElse(null);
                if (musica == null) { System.out.println("Música inválida."); pausar(scanner); continue; }

                System.out.println("\n===== Suas Playlists =====");
                for (Playlist p2 : usuarioLogado.getPlaylists()) System.out.println(p2.getId() + " - " + p2.getNome());

                System.out.print("\nEscolha uma playlist: ");
                int idPlaylist = scanner.nextInt();
                scanner.nextLine();

                Playlist playlist = usuarioLogado.getPlaylists().stream().filter(p2 -> p2.getId() == idPlaylist).findFirst().orElse(null);
                if (playlist == null) { System.out.println("Playlist inválida."); pausar(scanner); continue; }

                // CRIA NOVA INSTÂNCIA para garantir ID único na playlist e manter ordem de adição
                Musica copia = new Musica(musica.getNome(), musica.getCaminho());
                playlist.adicionarMusica(copia);

                System.out.println("\nMúsica adicionada!");
                pausar(scanner);
            }

            else if (opcao == 6) {
                System.out.println("\n===== Selecionar Playlist =====");
                if (usuarioLogado.getPlaylists().isEmpty()) { System.out.println("Nenhuma playlist disponível."); pausar(scanner); continue; }

                for (Playlist p : usuarioLogado.getPlaylists()) System.out.println(p.getId() + " - " + p.getNome());

                System.out.print("\nEscolha: ");
                int idPlaylist = scanner.nextInt();
                scanner.nextLine();

                Playlist playlist = usuarioLogado.getPlaylists().stream().filter(p -> p.getId() == idPlaylist).findFirst().orElse(null);
                if (playlist == null) { System.out.println("Playlist inválida."); pausar(scanner); continue; }

                PlayerMusica player = new PlayerMusica();

                while (true) {
                    System.out.println("\n===== Músicas =====");
                    for (Musica m : playlist.getMusicas()) System.out.println(m.getId() + " - " + m.getNome());

                    System.out.println("""
                            
                            Ações:
                            1 - Tocar
                            2 - Pausar
                            3 - Continuar
                            4 - Parar
                            0 - Voltar
                            Escolha: """);

                    int acao = scanner.nextInt();
                    scanner.nextLine();
                    if (acao == 0) break;

                    switch (acao) {
                        case 1 -> {
                            System.out.print("ID da música: ");
                            int idMus = scanner.nextInt();
                            scanner.nextLine();

                            Musica esc = playlist.getMusicas().stream().filter(m -> m.getId() == idMus).findFirst().orElse(null);
                            if (esc != null) player.tocar(esc.getCaminho());
                            else System.out.println("ID inválido.");
                        }
                        case 2 -> player.pausar();
                        case 3 -> player.continuar();
                        case 4 -> player.parar();
                        default -> System.out.println("Opção inválida.");
                    }
                }
            }

            else if (opcao == 0) {
                System.out.println("\nVocê saiu da conta.");
                break;
            }

            else {
                System.out.println("\nOpção inválida.");
                pausar(scanner);
            }
        }
    }
}
