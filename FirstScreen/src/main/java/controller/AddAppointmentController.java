package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {
    Stage stage;
    Parent scene;
    @FXML
    private TextField AddAppointmentCustomerID, AddAppointmentDescription, AddAppointmentLocation, AddAppointmentTitle,
            AddAppointmentType, AddAppointmentUserID, AppointmentID;
    @FXML
    private DatePicker AddAppointmentEndDateDatePicker, AddAppointmentStartDate;
    @FXML
    private ComboBox<?> AddAppointmentEndTimeComboBox, AddAppointmentStartTimeComboBoxDatePicker, AddContactComboBox;
    @FXML
    private Button OnActionCustomerAppointments, OnActionSaveUpdatedCustomer;
    @FXML
    void OnActionCustomerAppointments(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("CustomersAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    @FXML
    void OnActionSaveNewAppointment(ActionEvent event) throws IOException {
        return;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
