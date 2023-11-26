package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class ConexaoDAO {

        // Construtor vazio para evitar instanciar a classe
        private ConexaoDAO() {
        }

        // Método para obter uma conexão com o banco de dados
        public static Connection obterConexao() throws ClassNotFoundException {
            Class.forName("org.postgresql.Driver");
            String URL = "jdbc:postgresql://localhost:5432/erp_pizza";
            String USUARIO = "postgres";
            String SENHA = "password";
            try{
                Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
                return conexao;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        // Método para fechar uma conexão
        public static void fecharConexao(Connection conexao) {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
