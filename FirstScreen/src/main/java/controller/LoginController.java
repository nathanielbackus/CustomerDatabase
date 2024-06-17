package controller;

import helper.JDBC;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController extends Application implements Initializable {
    Stage stage;
    Parent scene;
    @FXML
    private Label SetCurrentLocationLabel, UsernameLabel, PasswordLabel, CurrentLocationLabel;
    @FXML
    private Button LoginButton, ExitButton;
    @FXML
    private TextField UserNameTextField, PasswordTextField;
    @FXML
    void OnActionExit(ActionEvent event) {
        JDBC.closeConnection();
        System.exit(0);
    }
    @FXML
    void OnActionLogin(ActionEvent event) throws IOException, SQLException {
        JDBC.openConnection();
        String sql = "SELECT * FROM USERS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        String UsernameEntered = UserNameTextField.getText();
        String PasswordEntered = PasswordTextField.getText();
        boolean isAuthenticated = false;
        while (rs.next()) {
            String Username = rs.getString("User_Name");
            String Password = rs.getString("Password");
            if (UsernameEntered.equals(Username) && PasswordEntered.equals(Password)) {
                isAuthenticated = true;
            }
        }
        if (isAuthenticated) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("CustomersAppointments.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        } else {
            Locale locale = Locale.getDefault();
            ResourceBundle rb = ResourceBundle.getBundle("Lang", locale);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("Title"));
            alert.setHeaderText(rb.getString("Header"));
            alert.setContentText(rb.getString("Content"));
            alert.showAndWait();
        }
    }
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    private void setLocationLabel() {
        ZoneId zoneId = ZoneId.systemDefault();
       SetCurrentLocationLabel.setText(zoneId.toString());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Locale locale = Locale.getDefault();
        ResourceBundle rb = ResourceBundle.getBundle("Lang", locale);
        UsernameLabel.setText(rb.getString("Username"));
        PasswordLabel.setText(rb.getString("Password"));
        CurrentLocationLabel.setText(rb.getString("CurrentLocation"));
        LoginButton.setText(rb.getString("Login"));
        ExitButton.setText(rb.getString("Exit"));
        setLocationLabel();
    }
    public static void main(String[] args) throws SQLException {
        launch(args);
        JDBC.openConnection();
    }
}