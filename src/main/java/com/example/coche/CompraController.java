package com.example.coche;

import javafx.beans.property.SimpleLongProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CompraController {
    private MenuController controllermenu;
    private Stage stage;

    @FXML
    private Label txtCorreo;
    @FXML
    private TextField modeloTextField;
    @FXML
    private TextField marcaTextField;
    @FXML
    private TextField matriculaTextField;
    @FXML
    private TextField capacidadTextField;
    @FXML
    private TableView<Vehiculo> carTable;
    @FXML
    private TableColumn<Vehiculo, Long> precioSemanaColumn;

    public void init(String text, Stage stage, MenuController menuController, Vehiculo selectedVehicle) {
        txtCorreo.setText(text);
        this.controllermenu = menuController;
        this.stage = stage;

        // Display the details of the selected vehicle
        modeloTextField.setText(selectedVehicle.getNombre_modelo());
        marcaTextField.setText(selectedVehicle.getMarca());
        matriculaTextField.setText(selectedVehicle.getMatricula());
        capacidadTextField.setText(String.valueOf(selectedVehicle.getCapacidad_sitio()));

        // Populate the TableView with the prices of the selected vehicle
        precioSemanaColumn.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getPrecio_semana()).asObject());

        carTable.getItems().add(selectedVehicle);
    }

    @FXML
    void Pagar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("felicidades.fxml"));
            Parent root = loader.load();
            FinalController felicidadesController = loader.getController();
            felicidadesController.init(txtCorreo.getText(), stage, controllermenu);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

