package models;

public class Pedido {
    private long id;
    private long clienteId;
    private long produtoId;

    public Pedido(){};
    public Pedido(long clienteId, long produtoId) {
        this.clienteId = clienteId;
        this.produtoId = produtoId;
    }
    public Pedido(long id ,long clienteId, long produtoId) {
        this.clienteId = clienteId;
        this.produtoId = produtoId;
        this.id = id;
    }

    public long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(long produtoId) {
        this.produtoId = produtoId;
    }

    public long getClienteId() {
        return clienteId;
    }

    public void setClienteId(long clienteId) {
        this.clienteId = clienteId;
    }
}
