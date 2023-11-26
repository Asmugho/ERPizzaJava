package dao.produto;

import dao.ConexaoDAO;
import models.Cliente;
import models.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProdutoDAO {
    public static boolean cadastrarProduto(Produto produto) throws ClassNotFoundException {
        Connection conexao = ConexaoDAO.obterConexao();
        int rowsAffected = 0;
        try (PreparedStatement preparedStatement = conexao
                .prepareStatement("INSERT INTO produtos (nome,preco) VALUES (?, ?)")) {
            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setDouble(2, produto.getPreco());
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
    @SuppressWarnings("finally")
    public static Produto buscarProdutoPorId(long idDesejado) throws ClassNotFoundException {
        Connection conexao = ConexaoDAO.obterConexao();
        String sql = "SELECT * FROM produtos WHERE id = ?";
        Produto produto = null;
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setLong(1, idDesejado);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                double preco = resultSet.getDouble("preco");
                preparedStatement.close();
                produto = new Produto(idDesejado,nome,preco);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            ConexaoDAO.fecharConexao(conexao);
            return produto != null ? produto : null;
        }
    }

    public static List<Produto> obterTodosOsProdutos() throws SQLException, ClassNotFoundException {
        List<Produto> produtos = new ArrayList<Produto>();
        Connection conexao = ConexaoDAO.obterConexao();
        String sql = "SELECT * FROM produtos WHERE produtos.datadelete IS NULL";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                double preco = resultSet.getDouble("preco");

                Produto produto = new Produto(id,nome,preco);
                produtos.add(produto);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        ConexaoDAO.fecharConexao(conexao);
        return produtos;
    }

    @SuppressWarnings("finally")
    public static boolean atualizarProdutoPorId(Produto produto) throws ClassNotFoundException {
        Connection conexao = ConexaoDAO.obterConexao();
        String sql = "UPDATE produtos SET nome = ?, preco = ? WHERE id = ?";
        int rowsAffected = 0;
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setDouble(2, produto.getPreco());
            preparedStatement.setLong(3, produto.getId());
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

    public static boolean deletarProdutoPorId(long id) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoDAO.obterConexao();
        String sql = "UPDATE produtos SET datadelete = ? WHERE id = ?";
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
