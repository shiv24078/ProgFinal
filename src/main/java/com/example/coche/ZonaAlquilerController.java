package com.example.coche;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ZonaAlquilerController {
    private Stage stage;
    private MenuController menuController;

    @FXML
    private VBox provinciaButtonsVBox;

    @FXML
    private Button goToMenuButton;

    public void initialize() throws SQLException, ClassNotFoundException {
        DatabaseHelper db = new DatabaseHelper();
        List<String> provincias = db.getAllProvincias();
        populateProvincias(provincias);
    }

    private void populateProvincias(List<String> provincias) {
        try {
            provinciaButtonsVBox.setAlignment(Pos.CENTER); // Make buttons align in the center
            provinciaButtonsVBox.setSpacing(10); // Set a gap between the buttons

            for (String provincia : provincias) {
                Button provinciaButton = new Button(provincia);
                provinciaButton.getStyleClass().add("button"); // Add style class to the button
                provinciaButton.setOnAction(e -> provinciaSelected(provincia));
                provinciaButtonsVBox.getChildren().add(provinciaButton);
            }
        } catch (Exception e) {
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


