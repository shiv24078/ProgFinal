package com.example.coche;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {


    private InicioController controllerinicio;
    private RegistroController controllerregis;
    private Stage stage;
    @FXML
    private Label txtCorreo;
    @FXML
    private TextField txtCorreo2;
    //Metodo volver al inico
    @FXML
    void Volver(ActionEvent event) {
        controllerinicio.show();
        stage.close();
    }

//init para
    public void init(String text, Stage stage, InicioController inicioController) {
        txtCorreo.setText(text);
        this.controllerinicio = inicioController;
        this.stage = stage;
    }
    //Metodo parar programa
    @FXML
    private void Salir (ActionEvent event){
        System.out.println("Gracias por pasarse por nuestra aplicación");
        System.exit(0);
    }



//Metodo para ir a rentar coche
    @FXML
    void Renta(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Compra.fxml"));
        Parent root = loader.load();
        CompraController controller = loader.getController();
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

    public void init(String text, Stage stage, RegistroController regisController) {
        txtCorreo.setText(text);
        this.controllerregis = regisController;
        this.stage = stage;
    }
}

