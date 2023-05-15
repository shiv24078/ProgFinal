package com.example.coche;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.SQLException;
public class RegistroController {
    private InicioController controllerinicio;
    private Stage stage;
    @FXML
    private Label txtCorreo;
    @FXML
    private TextField correoField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;
    @FXML
    private Label confirmationLabel;
    public void init(String text, Stage stage, InicioController inicioController) {
        txtCorreo.setText(text);
        this.controllerinicio = inicioController;
        this.stage = stage;
    }

    @FXML
    void registerNewClient(ActionEvent event) {
        String correo = correoField.getText();
        String password = passwordField.getText();
        try {
            DatabaseHelper db = new DatabaseHelper();
            if (correo.isEmpty() || password.isEmpty()) {
                errorLabel.setText("Correo y contraseña son requeridos");
                return;
            }
            if (db.checkIfEmailExists(correo)) {
                errorLabel.setText("Este correo ya está registrado");
                return;
            }
            Cliente newClient = new Cliente(correo, password);
            db.addClient(newClient);
            errorLabel.setText("");
            confirmationLabel.setText("Registro exitoso");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("An error occurred while registering a new client.");
            e.printStackTrace();
        }
    }

}
