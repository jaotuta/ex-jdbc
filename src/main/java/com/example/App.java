package com.example;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class App {

    private static boolean isInteger(String str) {
        return str != null && str.matches("[0-9]*");
    }

    public static void main(String[] args) throws SQLException, IOException {

        int opcao = 0;
        while (opcao != 4) {
            Service service = new Service();
            service.conectar();
            String resposta = JOptionPane.showInputDialog(null,
                    "Qual operação deseja realizar ?\n [1] - Cadastrar novo Aluno | Professor \n [2] - Exibir todos \n [3] - Buscar Aluno | Professor \n [4] - Sair  ",
                    "Universidade", 1);
            if (isInteger(resposta)) {
                opcao = java.lang.Integer.parseInt(resposta);
                switch (opcao) {
                    case 1: {
                        com.example.MySql.Logger.getLog().logger.info("Operação de cadastro todos selecionada");
                        String nome;
                        String funcao;
                        String email;
                        nome = JOptionPane.showInputDialog(null, "Nome", "Universidade", 1);
                        funcao = JOptionPane.showInputDialog(null, "Aluno ou Professor??", "Universidade", 1);
                        email = JOptionPane.showInputDialog(null, "Email", "Universidade", 1);
                        int confirmacao = JOptionPane.showConfirmDialog(null, "Dados: \n Nome: " + nome + "\n Função: "
                                + funcao + "\n Email: " + email + "\n Confirmar cadastro ? ", "Universidade", 1);
                        if (confirmacao == 0) {
                            service.novaPessoa(nome, funcao, email);
                            JOptionPane.showMessageDialog(null, "Cadastro realizado !!!", "Universidade", 1);
                        }
                    }
                        break;
                    case 2: {
                        com.example.MySql.Logger.getLog().logger.info("Operação de buscar todos selecionada");
                        String renderData = "";
                        List<String> dados = service.consultar();
                        for (String pessoa : dados) {
                            renderData += "\n" + pessoa;
                        }

                        JOptionPane.showMessageDialog(null, renderData);
                    }
                        break;
                    case 3: {
                        com.example.MySql.Logger.getLog().logger.info("Operação de busca por nome selecionada");
                        String renderData = "";
                        String nomeBusca = JOptionPane.showInputDialog(null, "Digite o nome.", "Universidade", 1);
                        List<String> dados = service.buscarPessoa(nomeBusca);
                        for (String pessoa : dados) {
                            renderData += "\n" + pessoa;
                        }
                        if (dados.size() > 1) {
                            JOptionPane.showMessageDialog(null, renderData, "Universidade", 1);
                        } else if(dados.size() > 0) {
                            int retorno = java.lang.Integer.parseInt(JOptionPane.showInputDialog(null,
                                    renderData + "\n [1] - Voltar \n [2] - Editar \n [3] - Excluir", "Universidade",
                                    1));
                            if (retorno == 2) {
                                String[] preId = renderData.split("  ");
                                String novoNome = JOptionPane.showInputDialog(null, "Nome:", preId[3]);
                                String novoEmail = JOptionPane.showInputDialog(null, "Email:", preId[5]);
                                String novoFunc = JOptionPane.showInputDialog(null, "Função:", preId[7]);
                                service.attPessoa(preId[1], novoNome, novoFunc, novoEmail);
                                JOptionPane.showMessageDialog(null, "Contato alterado com sucesso!", "Universidade", 1);
                            }
                            if (retorno == 3) {
                                String[] preId = renderData.split("  ");
                                int confirmacao = JOptionPane.showConfirmDialog(null,
                                        "Dados: \n Nome: " + preId[3] + "\n Função: "
                                                + preId[7] + "\n Email: " + preId[5] + "\n Certeza que deseja excluir o usuário ? ",
                                        "Universidade", 1);
                                if (confirmacao == 0) {
                                    service.excluirPessoa(java.lang.Integer.parseInt(preId[1]));
                                    JOptionPane.showMessageDialog(null, "Usuário excluido com sucesso.");
                                }

                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Nenhum usuário encontrado");
                        }
                    }
                        break;
                    case 4: {
                        com.example.MySql.Logger.getLog().logger.info("Operação de finalizar aplicação selecionada");
                        JOptionPane.showMessageDialog(null, "Obrigado por utilizar nosso sistema. !!!", "Universidade",
                                1);
                        service.desconectar();
                    }
                        break;
                    default: {
                        com.example.MySql.Logger.getLog().logger.info("Entrada não permitida");
                        JOptionPane.showMessageDialog(null, "Entrada inválida.", "Universidade", 1);
                    }

                }
            } else {
                com.example.MySql.Logger.getLog().logger.info("Opção do menu não permitida");
                JOptionPane.showMessageDialog(null, "Entrada inválida.", "Universidade UFN", 1);
            }

        }

    }

}
