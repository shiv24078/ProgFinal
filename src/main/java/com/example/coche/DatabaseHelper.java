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
                "matricula VARCHAR(255) NOT NULL UNIQUE," +
                "marca VARCHAR(255) NOT NULL," +
                "precio_semana BIGINT NOT NULL," +
                "capacidad_sitio BIGINT NOT NULL," +
                "FOREIGN KEY (id_tipo_vehiculo) REFERENCES tipo_vehiculo(id_tipo_vehiculo)" +

                ")";
        String updateVehiculoTableSQL = "ALTER TABLE vehiculo " +
                "ADD COLUMN id_tipo BIGINT," +
                "ADD FOREIGN KEY (id_tipo) REFERENCES tipo_vehiculo(id_tipo)";

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

        String createTipoVehiculoTableSQL = "CREATE TABLE IF NOT EXISTS tipo_vehiculo (" +
                "id_tipo BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "tipo VARCHAR(255) NOT NULL" +
                ")";











        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
            statement.execute(createClienteTableSQL);
            statement.execute(createVehiculoTableSQL);
            statement.execute(updateVehiculoTableSQL);

            statement.execute(createZona_AlquilerTableSQL);
            statement.execute(createAdminTableSQL);
            statement.execute(createReservaTableSQL);
            statement.execute(createTipoVehiculoTableSQL);

        }
    }
    public void insertInfo() throws SQLException, ClassNotFoundException {
        String insertVehicloTableSQL = "INSERT INTO vehiculo (nombre_modelo, matricula, marca, precio_semana, capacidad_sitio, id_tipo_vehiculo) " +
                "VALUES ('Audi A3', '1234ABC', 'Audi', 100, 5, 1), " +
                "('BMW X5', '5678DEF', 'BMW', 150, 7, 2)," +
                " ('Ford Fiesta', '9012GHI', 'Ford', 50, 4, 1)," +
                "('Toyota Corolla', '3456JKL', 'Toyota', 80, 5, 3)," +
                " ('Mercedes-Benz C-Class', '7890MNO', 'Mercedes-Benz', 120, 5, 1), " +
                "('Hyundai Tucson', '1234PQR', 'Hyundai', 90, 5, 2)," +
                " ('Volvo XC90', '5678STU', 'Volvo', 170, 7, 2)," +
                " ('Nissan Rogue', '9012VWX', 'Nissan', 100, 5, 2)," +
                "('Jeep Wrangler', '7890BCD', 'Jeep', 150, 4, 4)," +
                "('Kia Sorento', '1234EFG', 'Kia', 110, 5, 2)" +
                ";";
        String insertTipoVehiculoTableSQL = "INSERT INTO tipo_vehiculo (tipo) " +
                "VALUES ('Compacto'), " +
                "('SUV'), " +
                "('Sedán'), " +
                "('Todoterreno');";


        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
            statement.execute(insertVehicloTableSQL);
            statement.execute(insertTipoVehiculoTableSQL);

        } catch (SQLException e) {
            // The exception can be caused by the UNIQUE constraint on the "matricula" column.
            // Here you can handle the exception, for example, log it:
            System.out.println("Un coche con esta matricula ya existe en la base de datos.");
        }
    }

}
