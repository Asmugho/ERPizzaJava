package models.dtos;

public record PedidoDTO(long id,long clienteId,long produtoId,String nome,String nomeProduto,String endereco ,double preco){}
