package controller;

import dao.ContactDAOImpl;
import dao.CustomerDAO;
import dao.CustomerDAOImpl;
import helper.JDBC;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomersAppointmentsController implements Initializable {
    Stage stage;
    Parent scene;
    @FXML
    private TableView AllAppointmentsTableView;
    @FXML
    private TableView Next7DaysTableView;
    @FXML
    private TableView Next30DaysTableView;
    @FXML
    private TableView<Customer> AllCustomersTableView;
    @FXML
    private TableColumn<?, ?> AppointmentsTBContact, AppointmentsTBCustomerID, AppointmentsTBDesc, AppointmentsTBEnd,
            AppointmentsTBID, AppointmentsTBLocation, AppointmentsTBStart, AppointmentsTBTitle, AppointmentsTBType,
            AppointmentsTBUserID;
    @FXML
    private TableColumn<?, ?> CustomerTBAddress, CustomerTBDivisionID, CustomerTBID, CustomerTBName, CustomerTBPhone,
            CustomerTBPostalCode;
    @FXML
    void OnActionAddCustomer(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("AddCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    @FXML
    void OnActionUpdateCustomer(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UpdateCustomer.fxml"));
        loader.load();
        UpdateCustomerController UCController = loader.getController();
        UCController.setCustomer(AllCustomersTableView.getSelectionModel().getSelectedItem());
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.showAndWait();
    }

    @FXML
    public void OnActionDeleteCustomer(ActionEvent event) throws IOException, SQLException {
        Customer selectedCustomer = AllCustomersTableView.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Customer?");
            alert.setHeaderText("Are you sure you want to delete the selected customer?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                CustomerDAO.deleteCustomer(selectedCustomer);
            } else if (result.get() == ButtonType.CANCEL) {
                return;
            }
        }
    }
    @FXML
    void OnActionAddAppointment(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    @FXML
    void OnActionUpdateAppointment(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("UpdateAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    @FXML
    void OnActionDeleteAppointment(ActionEvent event) throws IOException {
    }
    @FXML
    void OnActionReports(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("Reports.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    @FXML
    void OnActionLogOut(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    @FXML
    void OnActionExit(ActionEvent event) throws IOException {
        JDBC.closeConnection();
        System.exit(0);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            CustomerDAOImpl.loadAllCustomers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        AllCustomersTableView.setItems(CustomerDAOImpl.getAllCustomers());
        CustomerTBID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        CustomerTBAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        CustomerTBDivisionID.setCellValueFactory(new PropertyValueFactory<>("DivisionID"));
        CustomerTBName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        CustomerTBPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        CustomerTBPostalCode.setCellValueFactory(new PropertyValueFactory<>("PostalCode"));
    }
}
