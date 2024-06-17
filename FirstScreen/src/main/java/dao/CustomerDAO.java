package dao;

import controller.CustomersAppointmentsController;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CustomerDAO {
    public static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    static List<Customer> getAllCustomers() {
        return null;
    }

    public Customer getCustomer(int CustomerID);

    public static int addCustomer(int CustomerID, String CustomerName, String Address, String PostalCode, String Phone, int DivisionID) throws SQLException {
        JDBC.openConnection();
        String sql = "INSERT INTO CUSTOMERS (Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES (?, ? , ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, CustomerID);
        ps.setString(2, CustomerName);
        ps.setString(3, Address);
        ps.setString(4, PostalCode);
        ps.setString(5, Phone);
        ps.setInt(6, DivisionID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public void updateCustomer(Customer customer);
    public static boolean deleteCustomer(Customer selectedCustomer) throws SQLException {
        if (selectedCustomer != null && CustomerDAOImpl.allCustomers.contains(selectedCustomer)) {
            int customerID = selectedCustomer.getCustomerID();
            String sql = "DELETE FROM customers WHERE Customer_ID = ?;";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, customerID);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                allCustomers.remove(selectedCustomer);
                return true;
            }
        }
        return false;
    }
}
