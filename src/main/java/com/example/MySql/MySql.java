package com.example.MySql;

import com.example.Log;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySql implements InterfaceBancoDados {


    static Connection c;

    public MySql() throws IOException {
    }

    @Override
    public void conectar(String db_url, String db_user, String db_password) throws SQLException {
        try {
            com.example.MySql.Logger.getLog().logger.info("Método Conectar, a conexão com o banco será estabelecida");
            c = DriverManager.getConnection(db_url, db_user, db_password);
            System.out.println("Conectado ao Banco.");
            com.example.MySql.Logger.getLog().logger.info("Conexão com o Banco realizada com sucesso");
        } catch (Exception e) {
            com.example.MySql.Logger.getLog().logger.info("Erro ao tentar conexão com o banco de dados.");
            throw new SQLException("Erro ao tentar conexão com o banco de dados.");

        }
    }

    @Override
    public void desconectar() throws SQLException {
        c.close();
        System.out.println("Desconectado ao Banco.");
    }

    @Override
    public ResultSet consultar(String db_query) throws SQLException {
        PreparedStatement ps = c.prepareStatement(db_query);
        ResultSet resultSet = ps.executeQuery();
        com.example.MySql.Logger.getLog().logger.info("Requisição de consulta realizada com sucesso com a seguinte query: " + db_query);
        return resultSet;
    }

    @Override
    public int inserirAlterarExcluir(String db_query) throws SQLException {
        PreparedStatement ps = c.prepareStatement(db_query);
        int result = ps.executeUpdate();
        com.example.MySql.Logger.getLog().logger.info("Requisição de inserirAlterarExcluir realizada com sucesso com a seguinte query: " + db_query);
        return result;
    }

}
