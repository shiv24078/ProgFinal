package com.example.coche;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class CompraController {

    private MenuController controllermenu;
    private Stage stage;
    @FXML
    private Label txtCorreo;


    @FXML
    void Volver(ActionEvent event) {
        controllermenu.show();
        stage.close();
    }


    public void init(String text, Stage stage, MenuController menuController) {
        txtCorreo.setText(text);
        this.controllermenu = menuController;
        this.stage = stage;
    }


    @FXML
    void Pagar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Felicidades.fxml"));
        Parent root = loader.load();
        FinalController controller = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        controller.init(txtCorreo.getText(), stage, this);
        stage.show();
        this.stage.close();
    }

    public void setStage(Stage primaryStage) {

        stage = primaryStage;
    }

    public void show() {
        stage.show();
    }
}

