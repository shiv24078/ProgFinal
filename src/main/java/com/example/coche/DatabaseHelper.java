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
                "id_reserva BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "id_cliente BIGINT NOT NULL," +
                "id_vehiculo BIGINT NOT NULL," +
                "id_zona_alquiler BIGINT NOT NULL," +
                "fecha_inicio DATE NOT NULL," +
                "fecha_fin DATE NOT NULL," +
                "FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)," +
                "FOREIGN KEY (id_vehiculo) REFERENCES vehiculo(id_vehiculo)," +
                "FOREIGN KEY (id_zona_alquiler) REFERENCES zona_alquiler(id_zona_alquiler)" +
                ")";
        String insertVehicloTableSQL = "INSERT INTO vehiculo (nombre_modelo, matricula, marca, precio_semana, capacidad_sitio) " +
                "VALUES ('Audi A3', '1234ABC', 'Audi', 100, 5), " +
                "('BMW X5', '5678DEF', 'BMW', 150, 7)," +
                " ('Ford Fiesta', '9012GHI', 'Ford', 50, 4)," +
                "('Toyota Corolla', '3456JKL', 'Toyota', 80, 5)," +
                " ('Mercedes-Benz C-Class', '7890MNO', 'Mercedes-Ben', 120, 5), " +
                "('Hyundai Tucson', '1234PQR', 'Hyundai', 90, 5)," +
                " ('Volvo XC90', '5678STU', 'Volvo', 170, 7)," +
                " ('Nissan Rogue', '9012VWX', 'Nissan', 100, 5)," +
                "('Jeep Wrangler', '7890BCD', 'Jeep', 150, 4)," +
                "('Kia Sorento', '1234EFG', 'Kia', 110, 5)" +
                ";";







        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
            statement.execute(createClienteTableSQL);
            statement.execute(createVehiculoTableSQL);
            statement.execute(createZona_AlquilerTableSQL);
            statement.execute(createAdminTableSQL);
            statement.execute(createReservaTableSQL);
            statement.execute(insertVehicloTableSQL);

        }
    }
}
