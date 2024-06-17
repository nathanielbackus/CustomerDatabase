package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {
    Stage stage;
    Parent scene;
    @FXML
    private Button OnActionCustomerAppointments;
    @FXML
    private TableView Reports1TableView, Reports2TableView, Reports3TableView;
    @FXML
    private TableColumn<?, ?> Reports1AppointmentMonth, Reports1AppointmentType, Reports1TotalAppointments;
    @FXML
    private TableColumn<?, ?> Reports2CustomerID, Reports2TotalAppointments;
    @FXML
    private TableColumn<?, ?> Reports3AppointmentDescription, Reports3AppointmentID, Reports3AppointmentLocation,
            Reports3AppointmentTitle, Reports3AppointmentType, Reports3CustomerID, Reports3EndDate, Reports3StartDate,
            Reports3UserID;
    @FXML
    private ComboBox Reports3ContactComboBox;

    @FXML
    void OnActionCustomerAppointments(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("CustomersAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
