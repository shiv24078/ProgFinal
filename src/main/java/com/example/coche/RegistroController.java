package com.example.coche;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistroController {
private InicioController controllerinicio;
private Stage stage;
@FXML
private Label txtCorreo;


//Metodo volver al inicio
@FXML
void Volver(ActionEvent event) {
controllerinicio.show();
stage.close();
}

//Metodo init para nombre etc
    public void init(String text, Stage stage, InicioController inicioController) {
        txtCorreo.setText(text);
        this.controllerinicio = inicioController;
        this.stage = stage;
    }

    @FXML
    void Menu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();
        MenuController controller = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        controller.init(txtCorreo.getText(), stage, this);
        stage.show();
        this.stage.close();
    }
    public void setStage (Stage primaryStage){
        stage = primaryStage;
    }

    public void show () {
        stage.show();
    }
}
