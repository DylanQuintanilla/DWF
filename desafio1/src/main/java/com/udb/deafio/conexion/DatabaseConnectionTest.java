package com.udb.deafio.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Clase para poder comprobar que tenemos una conexion a base de datos
public class DatabaseConnectionTest {
    private static final String URL = "jdbc:mysql://localhost:3306/desafio";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("✅ Conexión a la base de datos exitosa.");
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}

