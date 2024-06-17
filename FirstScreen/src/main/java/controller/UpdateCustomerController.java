package controller;

import dao.CountryDAO;
import dao.DivisionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Country;
import model.Customer;
import model.Division;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateCustomerController implements Initializable {
    Stage stage;
    Parent scene;
    @FXML
    private TextField CustomerIDTextField, AddressTextField, NameTextField, PhoneTextField, PostalCodeTextField;
    @FXML
    private ComboBox<Country> CountryComboBox;
    @FXML
    private ComboBox<Division> DivisionComboBox;
    Customer CurrentCustomer;
    @FXML
    void OnActionCustomerAppointments(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("CustomersAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    public void setCustomer(Customer customer) throws SQLException {
        CurrentCustomer = customer;
        CustomerIDTextField.setText(String.valueOf(customer.getCustomerID()));
        NameTextField.setText(customer.getCustomerName());
        AddressTextField.setText(customer.getAddress());
        PostalCodeTextField.setText(customer.getPostalCode());
        PhoneTextField.setText(customer.getPhone());


        //nearly best result?
//        ResultSet countryName = CountryDAO.getCountryNameByDivisionID(customer.getDivisionID());
//        CountryComboBox.setValue(countryName.getCountry());
//        DivisionComboBox.setValue(customer.getDivisionID());
    }
    @FXML
    void OnActionSaveUpdatedCustomer(ActionEvent event) throws IOException {
        return;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<Country> Countries = CountryDAO.getAllCountries();
            CountryComboBox.setItems(Countries);
            //REWORK THIS PLEASE LOL i want it just to be dynamic based on a selection i guess
            CountryComboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                if (newValue != null) {
                    ObservableList<Division> divisions = FXCollections.observableArrayList();
                    int countryId = newValue.getCountryID();
                    switch (countryId) {
                        case 1:
                            divisions = DivisionDAO.getAllUSDivisions();
                            break;
                        case 2:
                            divisions = DivisionDAO.getAllUKDivisions();
                            break;
                        case 3:
                            divisions = DivisionDAO.getAllCADivisions();
                            break;
                    }
                    DivisionComboBox.setItems(divisions);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
