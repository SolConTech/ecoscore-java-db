package br.com.fiap.model.dao;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection abrirConexao() throws ClassNotFoundException, SQLException{
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
            final String USER = "rm573977";
            final String PASS = "200208";
            con = DriverManager.getConnection(url, USER, PASS);
            System.out.println("Conectado ao Ecoscore");
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("Erro: A classe digitada não foi encontrada. " + e.getMessage());
        } catch (SQLException e) {
            throw new SQLException("Erro de SQL:" + e.getMessage());
        }
        return con;
    }

    public static void fecharConexao(Connection con) throws SQLException{
        if (con == null){
            JOptionPane.showMessageDialog(null, "Essa conexão já está fechada");
            return;
        }
        try {
            con.close();
            System.out.println("Desconectado do Ecoscore");
        } catch (SQLException e) {
            throw new SQLException("Erro de SQL:" + e.getMessage());
        }
    }

}
