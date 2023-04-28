package com.example;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.MySql.MySql;

public class Service {
    MySql mySql = new MySql();
    Connection c;
    final  String db_url = "jdbc:mysql://localhost:3306/reuniao";
    final  String db_user = "root";
    final  String db_password = "";
    public  int cod_pessoa;
    Random gerador = new Random();

    public Service() throws IOException {

    }

    public void conectar() throws SQLException {
        this.mySql.conectar(this.db_url, this.db_user, this.db_password);
    }

    public void desconectar() throws SQLException {
        this.mySql.desconectar();
    }

    public List<String> consultar() throws SQLException {
        List<String> pessoas = new ArrayList<>();
        final String db_query = "SELECT * FROM pessoa";
        ResultSet resultSet = this.mySql.consultar(db_query);
        while (resultSet.next()) {
            pessoas.add("RA: " + resultSet.getString(1) + " | Nome: " + resultSet.getString(2) + " | Email: " + resultSet.getString(3) + " | Função: "
                    + resultSet.getString(4));
        }
        return pessoas;
    }

    public List<String> buscarPessoa(String nome) throws SQLException {
        List<String> pessoas = new ArrayList<>();
        final String db_query = "SELECT * FROM pessoa WHERE nome LIKE '%"+nome+"%'";
        ResultSet resultSet = this.mySql.consultar(db_query);
        while (resultSet.next()) {
            pessoas.add("RA:  " + resultSet.getString(1) + "  | Nome:  " + resultSet.getString(2) + "  | Email:  " + resultSet.getString(3) + "  | Função:  "
                    + resultSet.getString(4));
        }
        return pessoas;
    }

    public void novaPessoa(String nome, String funcao, String email) throws SQLException {
        cod_pessoa = gerador.nextInt(100000);
        String db_query = " INSERT INTO pessoa VALUES ( " + cod_pessoa + ",'" + nome + "','" + email + "','" + funcao +"' )";
        this.mySql.inserirAlterarExcluir(db_query);
    }
    
    public void attPessoa(String id, String nome, String funcao, String email) throws SQLException {
        String db_query = " UPDATE pessoa SET nome= '"+nome+"', email='"+email+"', cargo='"+funcao+"' WHERE ID ="+ id;
        this.mySql.inserirAlterarExcluir(db_query);
        
    }
    public void excluirPessoa(int id) throws SQLException {
        String db_query = "DELETE FROM pessoa WHERE ID =" + id;
        this.mySql.inserirAlterarExcluir(db_query);
    }




}
