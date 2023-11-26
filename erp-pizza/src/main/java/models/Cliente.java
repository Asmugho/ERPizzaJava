package models;

public class Cliente {
    private long id;



    private String nome;
    private String endereco;

    public Cliente(){
        super();
    }
    public Cliente(String nome, String endereco, long id) {
        this.id = id;
        this.endereco = endereco;
        this.nome = nome;
    }

    public Cliente(String nome, String endereco) {
        this.endereco = endereco;
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}
