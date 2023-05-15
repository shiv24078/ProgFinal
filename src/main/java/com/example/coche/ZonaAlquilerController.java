package com.example.coche;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ZonaAlquilerController {
    private Stage stage;
    private MenuController menuController;

    @FXML
    private ListView<String> provinciaListView;

    @FXML
    private Button goToMenuButton;

    public void init(Stage stage) {
        this.stage = stage;
        populateProvincias();
        provinciaListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                provinciaSelected(newSelection);
            }
        });
    }

    public void initialize() throws SQLException, ClassNotFoundException {
        DatabaseHelper db = new DatabaseHelper();
        ObservableList<String> provincias = FXCollections.observableArrayList(db.getAllProvincias());
        provinciaListView.setItems(provincias);
    }

    private void populateProvincias() {
        try {
            DatabaseHelper db = new DatabaseHelper();
            List<String> provincias = db.getAllProvincias();
            ObservableList<String> provinciasObservableList = FXCollections.observableArrayList(provincias);
            provinciaListView.setItems(provinciasObservableList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void provinciaSelected(String selectedProvincia) {
        goToMenu();
    }

    @FXML
    private void goToMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Parent root = loader.load();
            MenuController menuController = loader.getController();
            menuController.init(stage);
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

