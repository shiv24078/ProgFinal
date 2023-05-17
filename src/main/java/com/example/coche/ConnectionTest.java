package com.example.coche;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.sql.*;
public class ConnectionTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection dbConnection = null;
        DatabaseHelper dbHelper = new DatabaseHelper();
        try {
            dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projfinal", "sergishiv", "Cide2022");
            System.out.println("Conexión ha sido establecida");
            dbHelper.createTables();
            dbHelper.insertInfo();
            dbHelper.insertZonaAlquiler();

            System.out.println("Tabla creada");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
