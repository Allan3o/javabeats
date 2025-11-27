public class EstiloMusica {

    private int id;
    private String nome;
    private String descricao;
    private String estilo;

    public EstiloMusica(int id, String nome, String descricao, String estilo) {
        this.id = id;
        this.nome = nome;
        this. descricao = descricao;
        this. estilo = estilo;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getestilo() {
        return estilo;
    }

}
