package com.example.coche;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                "correo VARCHAR(255) NOT NULL," +
                "password VARCHAR(255) NOT NULL" +
                ")";
        String createTipoVehiculoTableSQL = "CREATE TABLE IF NOT EXISTS tipo_vehiculo (" +
                "id_tipo_vehiculo BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "tipo VARCHAR(255) NOT NULL" +
                ")";
        String createVehiculoTableSQL = "CREATE TABLE IF NOT EXISTS vehiculo (" +
                "id_vehiculo BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "nombre_modelo VARCHAR(255) NOT NULL," +
                "matricula VARCHAR(255) NOT NULL UNIQUE," +
                "marca VARCHAR(255) NOT NULL," +
                "precio_semana BIGINT NOT NULL," +
                "capacidad_sitio BIGINT NOT NULL," +
                "id_tipo_vehiculo BIGINT NOT NULL," +
                "FOREIGN KEY (id_tipo_vehiculo) REFERENCES tipo_vehiculo(id_tipo_vehiculo)" +
                ")";
        String createZona_AlquilerTableSQL = "CREATE TABLE IF NOT EXISTS zona_alquiler (" +
                "id_zona_alquiler BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "dirrecion VARCHAR(255) NOT NULL," +
                "provincia VARCHAR(255) NOT NULL" +
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
        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
            statement.execute(createClienteTableSQL);
            statement.execute(createTipoVehiculoTableSQL);
            statement.execute(createVehiculoTableSQL);
            statement.execute(createZona_AlquilerTableSQL);
            statement.execute(createReservaTableSQL);
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
            statement.execute(insertTipoVehiculoTableSQL); // Move this line before the vehiculo insert
            statement.execute(insertVehicloTableSQL);
            connection.commit();
        } catch (SQLException e) {
            // The exception can be caused by the UNIQUE constraint on the "matricula" column.
            // Here you can handle the exception, for example, log it:
            System.out.println("Un coche con esta matricula ya existe en la base de datos.");
        }
    }

    public void insertCliente(String correo, String password) throws SQLException, ClassNotFoundException {
        String insertClienteTableSQL = "INSERT INTO cliente (correo, password) VALUES (?, ?);";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(insertClienteTableSQL)) {

            preparedStatement.setString(1, correo);
            preparedStatement.setString(2, password);

            preparedStatement.execute();
        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                System.out.println("Un cliente con este correo ya existe en la base de datos.");
            } else {
                System.out.println("Error al insertar el cliente: " + e.getMessage());
            }
        }
    }


    public void insertZonaAlquiler() throws SQLException, ClassNotFoundException {
        String insertZonaAlquilerTableSQL = "INSERT INTO zona_alquiler (dirrecion, provincia) " +
                "VALUES ('Calle Falsa 123', 'Madrid'), " +
                "('Avenida Que Viva 742', 'Barcelona');";

        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
            statement.execute(insertZonaAlquilerTableSQL);
        } catch (SQLException e) {
            System.out.println("Una zona de alquiler con esta dirección ya existe en la base de datos.");
        }
    }
    public void insertReserva() throws SQLException, ClassNotFoundException {
        String insertReservaTableSQL = "INSERT INTO reserva (id_cliente, id_vehiculo, id_zona_alquiler, fecha_inicio, fecha_fin) " +
                "VALUES (1, 1, 1, '2023-05-20', '2023-05-27'), " +
                "(2, 2, 2, '2023-06-01', '2023-06-08');";

        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
            statement.execute(insertReservaTableSQL);
        } catch (SQLException e) {
            System.out.println("Una reserva con estos datos ya existe en la base de datos.");
        }
    }
    public boolean checkIfEmailExists(String correo) throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(*) FROM Cliente WHERE correo = ?";
        Connection dbConnection = connect();
        PreparedStatement statement = dbConnection.prepareStatement(query);
        statement.setString(1, correo);
        ResultSet rs = statement.executeQuery();
        rs.next();
        return rs.getInt(1) > 0;
    }

    public void addClient(Cliente cliente) throws SQLException, ClassNotFoundException {
        Connection connection = connect();
        String query = "INSERT INTO cliente (correo, password) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, cliente.getCorreo());
        statement.setString(2, cliente.getPassword());
        try {
            statement.executeUpdate();
        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                System.out.println("Un cliente con este correo ya existe en la base de datos.");
            } else {
                System.out.println("Error al insertar el cliente: " + e.getMessage());
            }
        }
    }
    public boolean checkLogin(String correo, String password) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM cliente WHERE correo = ? AND password = ?";
        Connection dbConnection = connect();
        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setString(1, correo);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }
    public List<Vehiculo> getAllCars() throws SQLException, ClassNotFoundException {
        List<Vehiculo> cars = new ArrayList<>();
        String query = "SELECT * FROM vehiculo";
        Connection dbConnection = connect();
        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setId_vehiculo(resultSet.getLong("id_vehiculo"));
            vehiculo.setNombre_modelo(resultSet.getString("nombre_modelo"));
            vehiculo.setMatricula(resultSet.getString("matricula"));
            vehiculo.setMarca(resultSet.getString("marca"));
            vehiculo.setPrecio_semana(resultSet.getLong("precio_semana"));
            vehiculo.setCapacidad_sitio(resultSet.getLong("capacidad_sitio"));
            vehiculo.setId_tipo_vechiculo(resultSet.getLong("id_tipo_vehiculo"));
            cars.add(vehiculo);
        }
        resultSet.close();
        preparedStatement.close();
        dbConnection.close();
        return cars;
    }

    public List<String> getAllProvincias() throws SQLException, ClassNotFoundException {
        List<String> provincias = new ArrayList<>();
        String query = "SELECT DISTINCT provincia FROM zona_alquiler";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                provincias.add(resultSet.getString("provincia"));
            }
        }
        return provincias;
    }
}
