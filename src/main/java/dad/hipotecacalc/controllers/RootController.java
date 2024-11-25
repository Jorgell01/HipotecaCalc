package dad.hipotecacalc.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dad.hipotecacalc.models.Cuota;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    @FXML
    private TextField anyosTextField;

    @FXML
    private Button calcularButton;

    @FXML
    private TextField capitalTextField;

    @FXML
    private TableView<Cuota> datosTableView;

    @FXML
    private TableColumn<Cuota, Integer> numeroColumn;

    @FXML
    private TableColumn<Cuota, Integer> anyoColumn;

    @FXML
    private TableColumn<Cuota, Integer> mesColumn;

    @FXML
    private TableColumn<Cuota, Double> capitalinicialColumn;

    @FXML
    private TableColumn<Cuota, Double> interesesColumn;

    @FXML
    private TableColumn<Cuota, Double> capitalamortizadoColumn;

    @FXML
    private TableColumn<Cuota, Double> cuotaColumn;

    @FXML
    private TableColumn<Cuota, Double> capitalpendienteColumn;

    @FXML
    private TextField interesTextField;

    @FXML
    private BorderPane root;

    public RootController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootController.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize(URL location, ResourceBundle resources) {

        // Configuración de las columnas
        numeroColumn.setCellValueFactory(new PropertyValueFactory<>("numero"));
        anyoColumn.setCellValueFactory(new PropertyValueFactory<>("anyo"));
        mesColumn.setCellValueFactory(new PropertyValueFactory<>("mes"));
        capitalinicialColumn.setCellValueFactory(new PropertyValueFactory<>("capitalInicial"));
        interesesColumn.setCellValueFactory(new PropertyValueFactory<>("intereses"));
        capitalamortizadoColumn.setCellValueFactory(new PropertyValueFactory<>("capitalAmortizado"));
        cuotaColumn.setCellValueFactory(new PropertyValueFactory<>("cuota"));
        capitalpendienteColumn.setCellValueFactory(new PropertyValueFactory<>("capitalPendiente"));
    }

    @FXML
    void onActionCalcular(ActionEvent event) {
        try {
            double capital = Double.parseDouble(capitalTextField.getText());
            double interes = Double.parseDouble(interesTextField.getText());
            int anyos = Integer.parseInt(anyosTextField.getText());

            // Realizar la solicitud HTTP
            HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:3000/hipoteca?capital="
                    + capital + "&interes=" + interes + "&plazo=" + anyos).openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Convertir la respuesta JSON en una lista de cuotas
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);

                // Extraer el array de cuotas
                Cuota[] cuotas = gson.fromJson(jsonObject.getAsJsonArray("cuotas"), Cuota[].class);

                // Actualizar la tabla
                datosTableView.getItems().setAll(cuotas);

            } else {
                showError("Error en la respuesta del servidor: " + connection.getResponseMessage());
            }

            connection.disconnect();

        } catch (NumberFormatException e) {
            showError("Por favor, introduce valores numéricos válidos.");
        } catch (IOException e) {
            showError("Error de conexión: " + e.getMessage());
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }

    public BorderPane getRoot() {
        return root;
    }

}

