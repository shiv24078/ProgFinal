package com.example.coche;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/projfinal";
    private static final String USER = "sergishiv";
    private static final String PASSWORD = "Cide2022";

    public Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    public void createTables() throws SQLException, ClassNotFoundException {

        String createClienteTableSQL = "CREATE TABLE IF NOT EXISTS cliente (" +
                "id_cliente BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "nombre VARCHAR(255) NOT NULL," +
                "apellido VARCHAR(255) NOT NULL," +
                "correo VARCHAR(255) NOT NULL," +
                "password VARCHAR(255) NOT NULL" +
                ")";
        String createVehiculoTableSQL = "CREATE TABLE IF NOT EXISTS vehiculo (" +
                "id_vehiculo BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "nombre_modelo VARCHAR(255) NOT NULL," +
                "matricula VARCHAR(255) NOT NULL," +
                "marca VARCHAR(255) NOT NULL," +
                "precio_semana BIGINT NOT NULL," +
                "capacidad_sitio BIGINT NOT NULL" +
                ")";
        String createZona_AlquilerTableSQL = "CREATE TABLE IF NOT EXISTS zona_alquiler (" +
                "id_zona_alquiler BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "dirrecion VARCHAR(255) NOT NULL," +
                "provincia VARCHAR(255) NOT NULL" +
                ")";
        String createAdminTableSQL = "CREATE TABLE IF NOT EXISTS admin (" +
                "id_admin BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "nombre VARCHAR(255) NOT NULL," +
                "apellido VARCHAR(255) NOT NULL," +
                "correo VARCHAR(255) NOT NULL," +
                "clave VARCHAR(255) NOT NULL" +
                ")";
        String createReservaTableSQL = "CREATE TABLE IF NOT EXISTS reserva (" +
                "id_reserva BIGINT AUTO_INCREMENT PRIMARY KEY" +
                ")";



        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
            statement.execute(createClienteTableSQL);
            statement.execute(createVehiculoTableSQL);
            statement.execute(createZona_AlquilerTableSQL);
            statement.execute(createAdminTableSQL);
            statement.execute(createReservaTableSQL);

        }
    }
}
