package dao.cliente;

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

public class ClienteDAO {

    public static boolean cadastrarCliente(Cliente cliente) throws ClassNotFoundException {
        Connection conexao = ConexaoDAO.obterConexao();
        int rowsAffected = 0;
        try (PreparedStatement preparedStatement = conexao
                .prepareStatement("INSERT INTO clientes (nome,endereco) VALUES (?, ?)")) {
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getEndereco());
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

    public static Cliente buscarClientePorId(long idDesejado) throws ClassNotFoundException {
        Connection conexao = ConexaoDAO.obterConexao();
        String sql = "SELECT * FROM clientes WHERE id = ?";
        Cliente cliente = null;
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setLong(1, idDesejado);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String endereco = resultSet.getString("endereco");
                preparedStatement.close();
                cliente = new Cliente(nome,endereco,idDesejado);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            ConexaoDAO.fecharConexao(conexao);
            return cliente != null ? cliente : null;
        }
    }

    public static List<Cliente> obterTodosOsClientes() throws SQLException, ClassNotFoundException {
        List<Cliente> clientes = new ArrayList<Cliente>();
        Connection conexao = ConexaoDAO.obterConexao();
        String sql = "SELECT * FROM clientes WHERE clientes.datadelete IS NULL";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String endereco = resultSet.getString("endereco");

                Cliente cliente = new Cliente(nome,endereco,id);

                clientes.add(cliente);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        ConexaoDAO.fecharConexao(conexao);
        return clientes;
    }


    @SuppressWarnings("finally")
    public static boolean atualizarClientePorId(Cliente cliente) throws ClassNotFoundException {
        Connection conexao = ConexaoDAO.obterConexao();
        String sql = "UPDATE clientes SET nome = ?, endereco = ? WHERE id = ?";
        int rowsAffected = 0;
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getEndereco());
            preparedStatement.setLong(3, cliente.getId());
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

    public static boolean deletarClientePorId(long id) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoDAO.obterConexao();
        String sql = "UPDATE clientes SET datadelete = ? WHERE id = ?";
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
