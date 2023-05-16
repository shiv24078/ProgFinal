package com.example.coche;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class MenuController {
    private InicioController controllerinicio;
    private RegistroController controllerregis;
    private Stage stage;
    private UserData userData;

    @FXML
    private Label txtCorreo;
    @FXML
    private SplitMenuButton brandFilter;
    @FXML
    private TableView<Vehiculo> carTable;
    @FXML
    private TableColumn<Vehiculo, String> modeloColumn;
    @FXML
    private TableColumn<Vehiculo, String> matriculaColumn;
    @FXML
    private TableColumn<Vehiculo, String> marcaColumn;
    @FXML
    private TableColumn<Vehiculo, Integer> capacidadColumn;
    @FXML
    private TableColumn<Vehiculo, Double> precioColumn;
    @FXML
    private ObservableList<Vehiculo> originalCarList;


// In initialize or populateTable method

    public void populateTable() throws SQLException, ClassNotFoundException {
        DatabaseHelper db = new DatabaseHelper();
        originalCarList = FXCollections.observableArrayList(db.getAllCars()); // Save the original car list
        carTable.setItems(originalCarList); // Set the original list to populate the table
    }
    public void initialize(UserData userData, Stage stage) throws SQLException, ClassNotFoundException {
        this.userData = userData; // Add this line
        populateTable();
        modeloColumn.setCellValueFactory(new PropertyValueFactory<>("nombre_modelo"));
        matriculaColumn.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        marcaColumn.setCellValueFactory(new PropertyValueFactory<>("marca"));
        capacidadColumn.setCellValueFactory(new PropertyValueFactory<>("capacidad_sitio"));
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio_semana"));
    }

    @FXML
    void selectBrand(ActionEvent event) {
        // Get the selected brand
        String selectedBrand = ((MenuItem) event.getSource()).getText();
        System.out.println("Selected Brand: " + selectedBrand);

        // Filter the car list based on the selected brand
        ObservableList<Vehiculo> filteredCarList;
        if (selectedBrand.equals("Todos")) {
            filteredCarList = originalCarList; // Restore the original unfiltered car list
        } else {
            filteredCarList = originalCarList.filtered(car -> car.getMarca().equalsIgnoreCase(selectedBrand));
        }

        carTable.setItems(filteredCarList);
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
        // Get the selected vehicle
        Vehiculo selectedVehicle = carTable.getSelectionModel().getSelectedItem();
        if (selectedVehicle == null) {
            // No vehicle is selected, handle the error
            return;
        }

        // Save the selected vehicle's model
        String selectedModel = selectedVehicle.getNombre_modelo();
        userData.setCarModel(selectedModel);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Compra.fxml"));
        Parent root = loader.load();
        CompraController controller = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        controller.init(txtCorreo.getText(), stage, this, selectedVehicle); // Pass the selected vehicle to the init() method
        stage.show();

        // Save the selected model to UserData


        String fileName = "historia_de_compra.txt";
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName, true))) { // FileWriter is opened in append mode
            String userEmail = userData.getUserEmail();
            String userProvincia = userData.getProvincia();
            out.println(userEmail + " desde " + userProvincia + " ha comprado un " + selectedModel);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void init(Stage stage) {
        // You can access the selected zone's properties here
        this.stage = stage;
        // Initialize the rest of the MenuController as needed
    }

}
