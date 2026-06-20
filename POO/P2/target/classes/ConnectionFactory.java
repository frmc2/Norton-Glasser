package com.fatec.biblioteca.dao; // Alinhado com o seu pacote

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca_db?useTimezone=true&serverTimezone=UTC";
    private static final String USER = "root";

    private static final String PASSWORD = "*963,*963,";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver do MySQL não foi encontrado nas bibliotecas do projeto!", e);
        } catch (SQLException e) {
            throw new SQLException("Falha na autenticação do banco! Verifique usuário/senha na ConnectionFactory. Detalhe: " + e.getMessage(), e);
        }
    }
}
