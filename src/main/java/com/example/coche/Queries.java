package com.example.coche;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Queries {
    public static List<Cliente> getAllClientes() throws SQLException {
        String query = "SELECT * FROM cliente";
        List<Cliente> clientes = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projfinal", "sergishiv", "Cide2022");
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(resultSet.getLong("id_cliente"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setApellido(resultSet.getString("apellido"));
                cliente.setCorreo(resultSet.getString("correo"));
                cliente.setPassword(resultSet.getString("password"));
                clientes.add(cliente);
            }
        }
        return clientes;
    }

}
