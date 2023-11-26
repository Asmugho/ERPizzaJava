package dao.pedido;

import dao.ConexaoDAO;
import models.Cliente;
import models.Pedido;
import models.Produto;
import models.dtos.PedidoDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PedidoDAO {
    public static boolean cadastrarPedido(Pedido pedido) throws ClassNotFoundException {
        Connection conexao = ConexaoDAO.obterConexao();
        int rowsAffected = 0;
        try (PreparedStatement preparedStatement = conexao
                .prepareStatement("INSERT INTO pedidos (fk_clienteId,fk_produtoId) VALUES (?, ?)")) {
            preparedStatement.setLong(1, pedido.getClienteId());
            preparedStatement.setLong(2, pedido.getProdutoId());
            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        finally {
            ConexaoDAO.fecharConexao(conexao);
            return rowsAffected > 0;
        }
    }

    public static List<PedidoDTO> obterTodosOsPedidos() throws SQLException, ClassNotFoundException {
        List<PedidoDTO> pedidos = new ArrayList<>();
        Connection conexao = ConexaoDAO.obterConexao();
        String sql = "SELECT " +
                "pedidos.id AS id," +
                "clientes.nome AS nome_cliente," +
                "produtos.nome AS produto_pedido," +
                "clientes.endereco," +
                "produtos.preco," +
                "clientes.id AS clienteId," +
                "produtos.id AS produtoId " +
                "FROM pedidos " +
                "JOIN clientes ON pedidos.fk_clienteId = clientes.id " +
                "JOIN produtos ON pedidos.fk_produtoId = produtos.id WHERE pedidos.datadelete IS NULL";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long idPedido = resultSet.getLong("id");
                long idCliente = resultSet.getLong("clienteId");
                long idProduto = resultSet.getLong("produtoId");
                String nomeCliente = resultSet.getString("nome_cliente");
                String produtoPedido = resultSet.getString("produto_pedido");
                String endereco = resultSet.getString("endereco");
                double preco = resultSet.getDouble("preco");

                PedidoDTO pedidoAtual = new PedidoDTO(idPedido, idCliente, idProduto, nomeCliente, produtoPedido, endereco, preco);
                pedidos.add(pedidoAtual);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            ConexaoDAO.fecharConexao(conexao);
        }

        return pedidos;
    }



    public static PedidoDTO obterPedidoPorId(long idDesejado) throws SQLException, ClassNotFoundException {
        PedidoDTO pedidoAtual = null;
        Connection conexao = ConexaoDAO.obterConexao();
        String sql = "SELECT " +
                "pedidos.id AS id," +
                "clientes.nome AS nome_cliente," +
                "produtos.nome AS produto_pedido," +
                "clientes.endereco," +
                "produtos.preco," +
                "clientes.id AS clienteId," +
                "produtos.id AS produtoId " +
                "FROM pedidos " +
                "JOIN clientes ON pedidos.fk_clienteId = clientes.id " +
                "JOIN produtos ON pedidos.fk_produtoId = produtos.id WHERE pedidos.id = ?;";;

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setLong(1,idDesejado);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id= resultSet.getLong("id");
                long idCliente = resultSet.getLong("produtoId");
                long idPedido = resultSet.getLong("id");
                String nomeCliente = resultSet.getString("nome_cliente");
                String produtoPedido = resultSet.getString("produto_pedido");
                String endereco = resultSet.getString("endereco");
                double preco = resultSet.getDouble("preco");

                pedidoAtual = new PedidoDTO(idPedido,idCliente,idPedido,nomeCliente,produtoPedido,endereco,preco);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        ConexaoDAO.fecharConexao(conexao);
        return pedidoAtual;
    }

    public static boolean atualizarPedidoPorId(Pedido pedido) throws ClassNotFoundException {
        Connection conexao = ConexaoDAO.obterConexao();
        String sql = "UPDATE pedidos SET fk_clienteId = ?, fk_produtoId = ? WHERE id = ?";
        int rowsAffected = 0;
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setLong(1, pedido.getClienteId());
            preparedStatement.setLong(2, pedido.getProdutoId());
            rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            ConexaoDAO.fecharConexao(conexao);
            return rowsAffected > 0;
        }
    }

    public static boolean deletarPedidoPorId(long id) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoDAO.obterConexao();
        String sql = "UPDATE pedidos SET datadelete = ? WHERE id = ?";
        Date dataHoraAtual = new Date();
        int rowsAffected = 0;

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            java.sql.Date dataHoraAtualSQL = new java.sql.Date(dataHoraAtual.getTime());
            preparedStatement.setDate(1, dataHoraAtualSQL);
            preparedStatement.setLong(2, id);
            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            ConexaoDAO.fecharConexao(conexao);
        }
        return rowsAffected > 0;
    }
}
