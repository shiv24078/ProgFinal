package com.example.coche;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
public class InicioController {
    private Stage stage;
    @FXML
    private TextField txtCorreo;
    @FXML
    private PasswordField txtContraseña;
    @FXML
    private Label errorLabel;
    private DatabaseHelper databaseHelper = new DatabaseHelper();
    private UserData userData = new UserData();  // Create a UserData instance



    // Método para ir a registro
    @FXML
    void Regis(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Registro.fxml"));
        Parent root = loader.load();
        RegistroController controller = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        controller.init(txtCorreo.getText(), stage, this);
        stage.show();
        this.stage = stage;   // Set this.stage to the new Stage
    }

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public void show() {
        stage.show();
    }


    // Método para ir a ZonaAlquiler
    @FXML
    void Menu(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        String correo = txtCorreo.getText();
        String password = txtContraseña.getText();

        if (databaseHelper.checkLogin(correo, password)) {
            // Login successful, navigate to the next page
            userData.setUserEmail(correo);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ZonaAlquiler.fxml"));
            Parent root = loader.load();
            ZonaAlquilerController controller = loader.getController();
            // Get the current Stage
            Stage currentStage = (Stage) txtCorreo.getScene().getWindow();
            controller.init(userData, currentStage);  // Pass the current Stage
            Scene scene = new Scene(root);
            currentStage.setScene(scene);
            currentStage.show();
        } else {
            // Login failed, show error message
            errorLabel.setText("Error: verifique sus credenciales.");
        }
    }



    // Método para cerrar la aplicación
    @FXML
    private void Salir(ActionEvent event) {
        System.out.println("Gracias por pasarse por nuestra aplicación");
        System.exit(0);
    }

    public void init(Stage primaryStage) {
    }
}
