package com.fatec.consultorio.atv15_05;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private static final String URL =
        "jdbc:mysql://localhost:3306/Clinica?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // ajuste conforme seu usuário MySQL
    private static final String PASSWORD = "123456"; // ajuste conforme sua senha MySQL

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
