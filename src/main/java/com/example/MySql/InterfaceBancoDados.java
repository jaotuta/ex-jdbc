package com.example.MySql;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface InterfaceBancoDados {
    public void conectar (String db_url, String db_user, String db_password) throws SQLException;

    public void desconectar() throws SQLException;

    public ResultSet consultar(String db_query) throws SQLException;

    public int inserirAlterarExcluir(String db_query) throws SQLException;
}