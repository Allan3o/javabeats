package src.main.telas;

import java.util.ArrayList;
import java.util.Scanner;

import src.main.modelos.EstiloMusica;
import src.main.modelos.Musica;
import src.main.modelos.Playlist;
import src.main.modelos.Usuario;
import src.main.play.PlayerMusica;

public class TelaInicial {

    private static ArrayList<EstiloMusica> estilosMusicais = new ArrayList<>();
    private static int musicaAtualIndex = -1;
    private static Playlist playlistAtual = null;
    private static PlayerMusica player = new PlayerMusica();

    public static void pausar(Scanner scanner) {
        System.out.print("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }

    private static void carregarEstilos() {
        if (!estilosMusicais.isEmpty()) return;

        String base = "Musicas/";

        EstiloMusica rock = new EstiloMusica(1, "Rock");
        rock.adicionarMusica(new Musica("In The End - Linkin Park", base + "In The End.mp3"));
        rock.adicionarMusica(new Musica("Smells Like Teen Spirit - Nirvana", base + "Nirvana Smells Like Teen Spirit.mp3"));

        EstiloMusica pop = new EstiloMusica(2, "Pop");
        pop.adicionarMusica(new Musica("Wake Me Up", base + "Avicii Wake Me Up.mp3"));
        pop.adicionarMusica(new Musica("Titanium", base + "David Guetta Titanium ft. Sia.mp3"));

        EstiloMusica rap = new EstiloMusica(3, "Hip-Hop/Rap");
        rap.adicionarMusica(new Musica("Rap God - Eminem", base + "Eminem Rap God.mp3"));
        rap.adicionarMusica(new Musica("In Da Club - 50 Cent", base + "50 Cent In Da Club.mp3"));

        EstiloMusica sertanejo = new EstiloMusica(4, "Sertanejo");
        sertanejo.adicionarMusica(new Musica("Marília Mendonça", base + "Marília Mendonça.mp3"));
        sertanejo.adicionarMusica(new Musica("Largado às Traças", base + "Zé Neto e Cristiano LARGADO ÀS TRAÇAS Zé Neto e Cristiano.mp3"));

        EstiloMusica infantil = new EstiloMusica(5, "Infantil");
        infantil.adicionarMusica(new Musica("Eu Sou O Gummy Bear Gummy Bear - Gummy Bear", base + "Eu Sou O Gummy Bear Gummy Bear.mp3"));
        infantil.adicionarMusica(new Musica("Pintinho Amarelinho - Galinha Pintadinha", base + "Pintinho Amarelinho.mp3"));

        estilosMusicais.add(rock);
        estilosMusicais.add(pop);
        estilosMusicais.add(rap);
        estilosMusicais.add(sertanejo);
        estilosMusicais.add(infantil);
    }

    private static void tentarDefinirId(Musica musica, int id) {
       
        String[] metodos = {"setId", "setIdentificador", "setCodigo", "setIdMusica"};
        
        for (String metodo : metodos) {
            try {
                musica.getClass().getMethod(metodo, int.class).invoke(musica, id);
                return;
            } catch (Exception e) {
               
            }
        }
    }

    
    private static int encontrarProximoId(Playlist playlist) {
        if (playlist.getMusicas().isEmpty()) {
            return 1; // Primeira música
        }
        
        
        int maiorId = 0;
        for (Musica m : playlist.getMusicas()) {
            if (m.getId() > maiorId) {
                maiorId = m.getId();
            }
        }
        
        return maiorId + 1; 
    }

   
    private static void corrigirIdsSequenciais(Playlist playlist) {
        if (playlist.getMusicas().isEmpty()) {
            return;
        }
        
        
        int novoId = 1;
        for (Musica musica : playlist.getMusicas()) {
            tentarDefinirId(musica, novoId);
            novoId++;
        }
    }

    public static void mostrarMenu(Scanner scanner, Usuario usuarioLogado) {
        carregarEstilos();

        while (true) {
           
            System.out.print("\033[H\033[2J");
            System.out.flush();

          
            System.out.println("╔══════════════════════════════════════════════════════╗");
            System.out.printf("║               ♪  BEM-VINDO, %-20s ♪  ║\n", 
                            truncarTexto(usuarioLogado.getNome(), 20));
            System.out.println("╠══════════════════════════════════════════════════════╣");
            
          
            if (musicaAtualIndex >= 0 && playlistAtual != null && 
                musicaAtualIndex < playlistAtual.getMusicas().size()) {
                Musica atual = playlistAtual.getMusicas().get(musicaAtualIndex);
                System.out.println("║                                                        ║");
                System.out.printf("║          ▶  TOCANDO AGORA: %-25s ║\n", 
                                truncarTexto(atual.getNome(), 25));
                System.out.printf("║          Playlist: %-31s ║\n", 
                                truncarTexto(playlistAtual.getNome(), 31));
                System.out.println("╠══════════════════════════════════════════════════════╣");
            }
            
            System.out.println("║                                                        ║");
            System.out.println("║     [1]  ≡  Minhas Playlists                          ║");
            System.out.println("║     [2]  +  Criar Playlist                            ║");
            System.out.println("║     [3]  ✗  Remover Playlist                          ║");
            System.out.println("║     [4]  ♫  Explorar Estilos Musicais                 ║");
            System.out.println("║     [5]  ▶  Tocar Músicas                             ║");
            System.out.println("║     [0]  ←  Sair da Conta                             ║");
            System.out.println("║                                                        ║");
            System.out.println("╠══════════════════════════════════════════════════════╣");
            System.out.print("║  ?  Opção: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                mostrarPlaylists(scanner, usuarioLogado);
            } else if (opcao == 2) {
                criarPlaylist(scanner, usuarioLogado);
            } else if (opcao == 3) {
                removerPlaylist(scanner, usuarioLogado);
            } else if (opcao == 4) {
                explorarEstilos(scanner, usuarioLogado);
            } else if (opcao == 5) {
                tocarMusicas(scanner, usuarioLogado);
            } else if (opcao == 0) {
                sairConta(usuarioLogado, scanner);
                break;
            } else {
                opcaoInvalida();
                pausar(scanner);
            }
        }
    }

    private static void mostrarPlaylists(Scanner scanner, Usuario usuarioLogado) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║                    ≡  MINHAS PLAYLISTS               ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        
        if (usuarioLogado.getPlaylists().isEmpty()) {
            System.out.println("║                                                        ║");
            System.out.println("║            Você ainda não possui playlists.           ║");
            System.out.println("║            Crie sua primeira playlist!                ║");
            System.out.println("║                                                        ║");
        } else {
            System.out.println("║                                                        ║");
            for (Playlist p : usuarioLogado.getPlaylists()) {
                
                corrigirIdsSequenciais(p);
                
                System.out.printf("║    [#%02d]  %-35s ║\n", 
                                p.getId(), 
                                truncarTexto(p.getNome(), 35));
                System.out.printf("║          \"%-38s\" ║\n", 
                                truncarTexto(p.getDescricao(), 38));
                
                if (!p.getMusicas().isEmpty()) {
                    System.out.println("║          Músicas: %-30d ║".formatted(p.getMusicas().size()));
                    
                    for (Musica m : p.getMusicas()) {
                        System.out.printf("║             [#%02d] %-30s ║\n", 
                                        m.getId(), 
                                        truncarTexto(m.getNome(), 30));
                    }
                } else {
                    System.out.println("║          Nenhuma música adicionada              ║");
                }
                System.out.println("║    ────────────────────────────────────────────────║");
            }
        }
        System.out.println("╚══════════════════════════════════════════════════════╝");
        pausar(scanner);
    }

    private static void criarPlaylist(Scanner scanner, Usuario usuarioLogado) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║                    +  CRIAR PLAYLIST                  ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║                                                        ║");
        
        System.out.print("║  Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("║  Descrição: ");
        String desc = scanner.nextLine();
        
        int id = usuarioLogado.getPlaylists().size() + 1;
        usuarioLogado.adicionarPlaylist(new Playlist(id, nome, desc));
        
        System.out.println("║                                                        ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║                    ✓  PLAYLIST CRIADA!                ║");
        System.out.printf("║          Nome: %-33s ║\n", truncarTexto(nome, 33));
        System.out.printf("║          ID: #%-32d ║\n", id);
        System.out.println("╚══════════════════════════════════════════════════════╝");
        
        pausar(scanner);
    }

    private static void removerPlaylist(Scanner scanner, Usuario usuarioLogado) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║                    ✗  REMOVER PLAYLIST                ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        
        ArrayList<Playlist> listas = usuarioLogado.getPlaylists();
        if (listas.isEmpty()) {
            System.out.println("║                                                        ║");
            System.out.println("║            Nenhuma playlist cadastrada.               ║");
            System.out.println("║                                                        ║");
            System.out.println("╚══════════════════════════════════════════════════════╝");
            pausar(scanner);
            return;
        }
        
        System.out.println("║                                                        ║");
        for (int i = 0; i < listas.size(); i++) {
            System.out.printf("║    [%d]  #%02d | %-31s ║\n", 
                            (i + 1), 
                            listas.get(i).getId(), 
                            truncarTexto(listas.get(i).getNome(), 31));
        }
        System.out.println("║                                                        ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.print("║  ?  Escolha (0 para cancelar): ");
        
        int n = scanner.nextInt();
        scanner.nextLine();
        
        if (n == 0) {
            System.out.println("║                                                        ║");
            System.out.println("║               Operação cancelada.                     ║");
            System.out.println("╚══════════════════════════════════════════════════════╝");
        } else if (n < 1 || n > listas.size()) {
            System.out.println("║                                                        ║");
            System.out.println("║               Opção inválida.                         ║");
            System.out.println("╚══════════════════════════════════════════════════════╝");
        } else {
            String nomeRemovido = listas.get(n - 1).getNome();
            listas.remove(n - 1);
            System.out.println("║                                                        ║");
            System.out.println("║               ✓  PLAYLIST REMOVIDA!                   ║");
            System.out.printf("║          \"%-38s\" ║\n", truncarTexto(nomeRemovido, 38));
            System.out.println("╚══════════════════════════════════════════════════════╝");
        }
        pausar(scanner);
    }

    private static void explorarEstilos(Scanner scanner, Usuario usuarioLogado) {
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            
            System.out.println("╔══════════════════════════════════════════════════════╗");
            System.out.println("║                    ♫  EXPLORAR ESTILOS                ║");
            System.out.println("╠══════════════════════════════════════════════════════╣");
            System.out.println("║                                                        ║");
            
            for (EstiloMusica e : estilosMusicais) {
                System.out.printf("║    [%d]  %-12s → %-2d músicas disponíveis ║\n", 
                                e.getId(), e.getNome(), e.getMusicas().size());
            }
            
            System.out.println("║    [0]  ← Voltar                                       ║");
            System.out.println("║                                                        ║");
            System.out.println("╠══════════════════════════════════════════════════════╣");
            System.out.print("║  ?  Escolha o estilo: ");
            
            int idEstilo = scanner.nextInt();
            scanner.nextLine();
            
            if (idEstilo == 0) break;
            
            EstiloMusica estilo = estilosMusicais.stream()
                .filter(e -> e.getId() == idEstilo)
                .findFirst().orElse(null);
                
            if (estilo == null) {
                opcaoInvalida("ESTILO");
                pausar(scanner);
                continue;
            }
            
            adicionarMusicaAoEstilo(scanner, usuarioLogado, estilo);
        }
    }

    private static void adicionarMusicaAoEstilo(Scanner scanner, Usuario usuarioLogado, EstiloMusica estilo) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.printf("║               ♫  %-35s ♫  ║\n", estilo.getNome().toUpperCase());
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║                                                        ║");
        
        for (Musica m : estilo.getMusicas()) {
            System.out.printf("║    [#%02d]  %-37s ║\n", m.getId(), m.getNome());
        }
        
        System.out.println("║                                                        ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.print("║  ?  Escolha a música (0 para voltar): ");
        
        int idMusica = scanner.nextInt();
        scanner.nextLine();
        
        if (idMusica == 0) return;
        
        Musica musica = estilo.getMusicas().stream()
            .filter(m -> m.getId() == idMusica)
            .findFirst().orElse(null);
            
        if (musica == null) {
            opcaoInvalida("MÚSICA");
            pausar(scanner);
            return;
        }
        
        escolherPlaylistParaAdicionar(scanner, usuarioLogado, musica);
    }

    private static void escolherPlaylistParaAdicionar(Scanner scanner, Usuario usuarioLogado, Musica musica) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.printf("║          ADICIONAR: %-30s ║\n", truncarTexto(musica.getNome(), 30));
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║                                                        ║");
        
        if (usuarioLogado.getPlaylists().isEmpty()) {
            System.out.println("║           Nenhuma playlist disponível.               ║");
            System.out.println("║           Crie uma playlist primeiro!                ║");
            System.out.println("╚══════════════════════════════════════════════════════╝");
            pausar(scanner);
            return;
        }
        
        System.out.println("║          Suas Playlists:                              ║");
        for (Playlist p : usuarioLogado.getPlaylists()) {
            System.out.printf("║    [#%02d]  %-35s ║\n", p.getId(), truncarTexto(p.getNome(), 35));
        }
        
        System.out.println("║    [0]  ← Cancelar                                    ║");
        System.out.println("║                                                        ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.print("║  ?  Escolha uma playlist: ");
        
        int idPlaylist = scanner.nextInt();
        scanner.nextLine();
        
        if (idPlaylist == 0) return;
        
        Playlist playlist = usuarioLogado.getPlaylists().stream()
            .filter(p -> p.getId() == idPlaylist)
            .findFirst().orElse(null);
            
        if (playlist == null) {
            opcaoInvalida("PLAYLIST");
            pausar(scanner);
            return;
        }
        
      
        Musica copia = new Musica(musica.getNome(), musica.getCaminho());
        
        
        int proximoId = encontrarProximoId(playlist);
        tentarDefinirId(copia, proximoId);
        
       
        try {
            playlist.adicionarMusica(copia);
        } catch (Exception e) {
          
            try {
                java.lang.reflect.Field musicasField = Playlist.class.getDeclaredField("musicas");
                musicasField.setAccessible(true);
                @SuppressWarnings("unchecked")
                ArrayList<Musica> musicas = (ArrayList<Musica>) musicasField.get(playlist);
                musicas.add(copia);
            } catch (Exception e2) {
                System.out.println("║                                                        ║");
                System.out.println("║      !  ERRO AO ADICIONAR MÚSICA                    ║");
                System.out.println("╚══════════════════════════════════════════════════════╝");
                pausar(scanner);
                return;
            }
        }
        
       
        corrigirIdsSequenciais(playlist);
        
        System.out.println("║                                                        ║");
        System.out.println("║               ✓  MÚSICA ADICIONADA!                   ║");
        System.out.printf("║          À playlist: %-30s ║\n", truncarTexto(playlist.getNome(), 30));
        System.out.printf("║          Com ID: #%-32d ║\n", copia.getId());
        System.out.println("╚══════════════════════════════════════════════════════╝");
        
        pausar(scanner);
    }

    private static void tocarMusicas(Scanner scanner, Usuario usuarioLogado) {
        if (usuarioLogado.getPlaylists().isEmpty()) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            
            System.out.println("╔══════════════════════════════════════════════════════╗");
            System.out.println("║                    ▶  TOCAR MÚSICAS                  ║");
            System.out.println("╠══════════════════════════════════════════════════════╣");
            System.out.println("║                                                        ║");
            System.out.println("║           Nenhuma playlist disponível.               ║");
            System.out.println("║           Crie uma playlist primeiro!                ║");
            System.out.println("╚══════════════════════════════════════════════════════╝");
            
            pausar(scanner);
            return;
        }
        
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            
            System.out.println("╔══════════════════════════════════════════════════════╗");
            System.out.println("║                    ▶  SELECIONAR PLAYLIST            ║");
            System.out.println("╠══════════════════════════════════════════════════════╣");
            System.out.println("║                                                        ║");
            
            for (Playlist p : usuarioLogado.getPlaylists()) {
                System.out.printf("║[#%02d]%-35s(%d músicas) ║\n", 
                                p.getId(), 
                                truncarTexto(p.getNome(), 30),
                                p.getMusicas().size());
            }
            
            System.out.println("║    [0]  ← Voltar                                       ║");
            System.out.println("║                                                        ║");
            System.out.println("╠══════════════════════════════════════════════════════╣");
            System.out.print("║  ?  Escolha uma playlist: ");
            
            int idPlaylist = scanner.nextInt();
            scanner.nextLine();
            
            if (idPlaylist == 0) break;
            
            Playlist playlist = usuarioLogado.getPlaylists().stream()
                .filter(p -> p.getId() == idPlaylist)
                .findFirst().orElse(null);
                
            if (playlist == null) {
                opcaoInvalida("PLAYLIST");
                pausar(scanner);
                continue;
            }
            
            
            corrigirIdsSequenciais(playlist);
            
            playlistAtual = playlist;
            gerenciarPlayer(scanner, playlist);
        }
    }

    private static void gerenciarPlayer(Scanner scanner, Playlist playlist) {
      
        corrigirIdsSequenciais(playlist);
        
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            
            System.out.println("╔══════════════════════════════════════════════════════╗");
            System.out.printf("║               ♫  %-35s ♫  ║\n", 
                            truncarTexto(playlist.getNome(), 35));
            System.out.println("╠══════════════════════════════════════════════════════╣");
            System.out.println("║                                                        ║");
            
            if (playlist.getMusicas().isEmpty()) {
                System.out.println("║           Nenhuma música nesta playlist.            ║");
                System.out.println("║           Adicione músicas primeiro!                ║");
            } else {
                for (int i = 0; i < playlist.getMusicas().size(); i++) {
                    Musica m = playlist.getMusicas().get(i);
                    String indicador = (i == musicaAtualIndex) ? "▶ " : "  ";
                    System.out.printf("║    %s[#%02d]  %-35s ║\n", 
                                    indicador, m.getId(), truncarTexto(m.getNome(), 35));
                }
            }
            
            System.out.println("║                                                        ║");
            System.out.println("╠══════════════════════════════════════════════════════╣");
            System.out.println("║                    CONTROLES DO PLAYER               ║");
            System.out.println("║    [1]  ▶  Tocar música                               ║");
            System.out.println("║    [2]  ⏸  Pausar                                     ║");
            System.out.println("║    [3]  ⏵  Continuar                                  ║");
            System.out.println("║    [4]  ⏹  Parar                                      ║");
            System.out.println("║    [5]  ⏮  Música anterior                            ║");
            System.out.println("║    [6]  ⏭  Próxima música                             ║");
            System.out.println("║    [0]  ←  Voltar                                      ║");
            System.out.println("║                                                        ║");
            System.out.println("╠══════════════════════════════════════════════════════╣");
            System.out.print("║  ?  Ação: ");
            
            int acao = scanner.nextInt();
            scanner.nextLine();
            
            if (acao == 0) {
                player.parar();
                musicaAtualIndex = -1;
                break;
            }
            
            switch (acao) {
                case 1 -> tocarMusicaSelecionada(scanner, playlist);
                case 2 -> pausarMusica(scanner);
                case 3 -> continuarMusica(scanner);
                case 4 -> pararMusica(scanner);
                case 5 -> musicaAnterior(scanner, playlist);
                case 6 -> proximaMusica(scanner, playlist);
                default -> {
                    opcaoInvalida();
                    pausar(scanner);
                }
            }
        }
    }

    private static void tocarMusicaSelecionada(Scanner scanner, Playlist playlist) {
        if (playlist.getMusicas().isEmpty()) {
            System.out.println("║                                                        ║");
            System.out.println("║           !  NENHUMA MÚSICA DISPONÍVEL!             ║");
            System.out.println("╚══════════════════════════════════════════════════════╝");
            pausar(scanner);
            return;
        }
        
        System.out.print("║  ?  ID da música: ");
        int idMus = scanner.nextInt();
        scanner.nextLine();
        
      
        Musica musicaEscolhida = null;
        int indiceEscolhido = -1;
        
        for (int i = 0; i < playlist.getMusicas().size(); i++) {
            if (playlist.getMusicas().get(i).getId() == idMus) {
                musicaEscolhida = playlist.getMusicas().get(i);
                indiceEscolhido = i;
                break;
            }
        }
        
        if (musicaEscolhida != null) {
            musicaAtualIndex = indiceEscolhido;
            
            System.out.println("║                                                        ║");
            System.out.printf("║          ▶  TOCANDO: %-30s ║\n", 
                            truncarTexto(musicaEscolhida.getNome(), 30));
            System.out.printf("║          ID: #%02d | Posição: %d/%d               ║\n", 
                            idMus, indiceEscolhido + 1, playlist.getMusicas().size());
            System.out.println("╚══════════════════════════════════════════════════════╝");
            
            player.tocar(musicaEscolhida.getCaminho());
            pausar(scanner);
        } else {
            System.out.println("║                                                        ║");
            System.out.println("║               !  ID INVÁLIDO!                         ║");
            System.out.printf("║          IDs disponíveis: ");
            for (int i = 0; i < playlist.getMusicas().size(); i++) {
                System.out.printf("#%02d ", playlist.getMusicas().get(i).getId());
            }
            System.out.println("         ║");
            System.out.println("╚══════════════════════════════════════════════════════╝");
            pausar(scanner);
        }
    }

    private static void pausarMusica(Scanner scanner) {
        player.pausar();
        System.out.println("║                                                        ║");
        System.out.println("║               ⏸  MÚSICA PAUSADA                     ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        pausar(scanner);
    }

    private static void continuarMusica(Scanner scanner) {
        player.continuar();
        System.out.println("║                                                        ║");
        System.out.println("║               ⏵  MÚSICA CONTINUADA                  ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        pausar(scanner);
    }

    private static void pararMusica(Scanner scanner) {
        player.parar();
        musicaAtualIndex = -1;
        System.out.println("║                                                        ║");
        System.out.println("║               ⏹  MÚSICA PARADA                      ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        pausar(scanner);
    }

    private static void musicaAnterior(Scanner scanner, Playlist playlist) {
        if (playlist.getMusicas().isEmpty() || musicaAtualIndex < 0) {
            System.out.println("║                                                        ║");
            System.out.println("║           !  NENHUMA MÚSICA SENDO TOCADA!           ║");
            System.out.println("╚══════════════════════════════════════════════════════╝");
            pausar(scanner);
            return;
        }
        
        int novoIndex = musicaAtualIndex - 1;
        if (novoIndex < 0) {
            novoIndex = playlist.getMusicas().size() - 1; 
        }
        
        tocarPorIndex(scanner, playlist, novoIndex);
    }

    private static void proximaMusica(Scanner scanner, Playlist playlist) {
        if (playlist.getMusicas().isEmpty() || musicaAtualIndex < 0) {
            System.out.println("║                                                        ║");
            System.out.println("║           !  NENHUMA MÚSICA SENDO TOCADA!           ║");
            System.out.println("╚══════════════════════════════════════════════════════╝");
            pausar(scanner);
            return;
        }
        
        int novoIndex = musicaAtualIndex + 1;
        if (novoIndex >= playlist.getMusicas().size()) {
            novoIndex = 0; 
        }
        
        tocarPorIndex(scanner, playlist, novoIndex);
    }

    private static void tocarPorIndex(Scanner scanner, Playlist playlist, int index) {
        Musica musica = playlist.getMusicas().get(index);
        musicaAtualIndex = index;
        
        player.parar();
        player.tocar(musica.getCaminho());
        
        System.out.println("║                                                        ║");
        System.out.printf("║          ⏮/⏭  TOCANDO: %-30s ║\n", 
                        truncarTexto(musica.getNome(), 30));
        System.out.printf("║          ID: #%02d | Posição: %d/%d               ║\n", 
                        musica.getId(), index + 1, playlist.getMusicas().size());
        System.out.println("╚══════════════════════════════════════════════════════╝");
        
        pausar(scanner);
    }

    private static void sairConta(Usuario usuarioLogado, Scanner scanner) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║                                                        ║");
        System.out.println("║               ←  SAINDO DA CONTA                      ║");
        System.out.println("║                                                        ║");
        System.out.println("║        Até logo, " + truncarTexto(usuarioLogado.getNome(), 20) + "!              ");
        System.out.println("║                                                        ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        
        player.parar(); 
        
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void opcaoInvalida() {
        opcaoInvalida("OPÇÃO");
    }

    private static void opcaoInvalida(String tipo) {
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║                                                        ║");
        System.out.printf("║               !  %s INVÁLIDA!                       ║\n", tipo);
        System.out.println("║                                                        ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }
    
    private static String truncarTexto(String texto, int limite) {
        if (texto.length() > limite) {
            return texto.substring(0, limite - 3) + "...";
        }
        return texto;
    }
}