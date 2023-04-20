package com.example;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import com.example.MySql.MySql;


public class App {

    private static boolean isInteger(String str) {
        return str != null && str.matches("[0-9]*");
    }

    public static void main(String[] args) throws SQLException {
        int opcao = 0;
        while (opcao != 4) {
            Service service = new Service();
            service.conectar();
            String resposta = JOptionPane.showInputDialog(null,
                    "Qual operação deseja realizar ?\n [1] - Cadastrar novo Aluno | Professor \n [2] - Exibir todos \n [3] - Buscar Aluno | Professor \n [4] - Sair  ",
                    "Universidade", 1);
            if (isInteger(resposta)) {
                opcao = java.lang.Integer.parseInt(resposta);
                System.out.println(opcao);
                switch (opcao) {
                    case 1: {
                        String nome;
                        String funcao;
                        String email;
                        nome = JOptionPane.showInputDialog(null, "Nome");
                        funcao = JOptionPane.showInputDialog(null, "Aluno ou Professor??");
                        email = JOptionPane.showInputDialog(null, "Email");
                        int confirmacao = JOptionPane.showConfirmDialog(null, "Dados: \n Nome: " + nome + "\n Função: "
                                + funcao + "\n RA: " + email + "\n Confirmar cadastro ? ");
                        if (confirmacao == 0) {
                            //service.novaPessoa(nome, funcao, ra);
                            JOptionPane.showMessageDialog(null, "Cadastro realizado !!!");
                        }
                        System.out.println(nome + funcao + email);
                    }
                        break;
                    case 2: {
                        String renderData = "";
                        List<String> dados = service.consultar();
                        for ( String pessoa : dados) {
                            renderData += "\n" + pessoa;
                        }

                        JOptionPane.showMessageDialog(null, renderData);
                    }
                    break;
                    case 3: {
                        JOptionPane.showInputDialog(null, "Digite o RA !!!");
                        JOptionPane.showMessageDialog(null, "Dados da busca.");
                    }
                    break;
                    case 4: {
                        JOptionPane.showMessageDialog(null, "Obrigado por utilizar nosso sistema. !!!");
                        service.desconectar();
                    }
                    break;
                    default: {
                        JOptionPane.showMessageDialog(null, "Entrada inválida.");
                    }

                }
            }else{
                JOptionPane.showMessageDialog(null, "Entrada inválida.", "Universidade UFN", 1);
            }

        }

    }

}
