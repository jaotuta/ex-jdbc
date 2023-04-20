package com.example.MySql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySql implements InterfaceBancoDados {

    static Connection c;

    @Override
    public void conectar(String db_url, String db_user, String db_password) throws SQLException {
        try {
            c = DriverManager.getConnection(db_url, db_user, db_password);
            System.out.println("Conectado ao Banco.");
        } catch (Exception e) {
            throw new SQLException("Erro ao tentar conexão com o banco de dados.");
        }
    }

    @Override
    public void desconectar() throws SQLException {
        c.close();
    }

    @Override
    public ResultSet consultar(String db_query) throws SQLException {

        PreparedStatement ps = c.prepareStatement(db_query);
        ResultSet resultSet = ps.executeQuery();
        return resultSet;

    }

    @Override
    public int inserirAlterarExcluir(String db_query) throws SQLException {
        PreparedStatement ps = c.prepareStatement(db_query);
        int result = ps.executeUpdate();
        return result;
    }

}
