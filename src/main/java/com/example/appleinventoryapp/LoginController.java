package com.example.appleinventoryapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

import java.io.IOException;

import java.lang.module.ModuleDescriptor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button CreateAccountButton;
    @FXML
    private Label CreateMessageLabel;
    @FXML
    private TextField FirstNameTextField;
    @FXML
    private TextField LastNameTextField;
    @FXML
    private TextField CreateUsernameTextField;
    @FXML
    private PasswordField CreatePasswordPasswordField;
    @FXML
    private PasswordField CreateConfirmPasswordPasswordField;

    @FXML
    private Button LoginButton;
    @FXML
    private Label LoginMessageLabel;
    @FXML
    private TextField LoginUsernameTextField;
    @FXML
    private PasswordField LoginPasswordPasswordField;

    public void CreateAccountButtonOnAction(ActionEvent e) {
        if (FirstNameTextField.getText().isBlank() ||
                LastNameTextField.getText().isBlank() ||
                CreateUsernameTextField.getText().isBlank() ||
                CreatePasswordPasswordField.getText().isBlank() ||
                CreateConfirmPasswordPasswordField.getText().isBlank()
        ) {
            CreateMessageLabel.setText("Fill out all fields");
        } else if (!CreatePasswordPasswordField.getText().equals(CreateConfirmPasswordPasswordField.getText())) {
            CreateMessageLabel.setText("Passwords do not match");
        } else {
            createAccount();
        }
    }

    public void createAccount() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String createAccount = "INSERT INTO Customer (Username, Password, FirstName, LastName) VALUES ('" +
                CreateUsernameTextField.getText() + "', '" + CreatePasswordPasswordField.getText() + "', '" +
                FirstNameTextField.getText() + "', '" + LastNameTextField.getText() + "');";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery("SELECT Username From Customer;");

            boolean unique = true;
            while (queryResult.next()) {
                if (CreateUsernameTextField.getText().equals(queryResult.getString(1))) {
                    unique = false;
                }
            }
            if (unique) {
                statement.executeUpdate(createAccount);
                CreateMessageLabel.setText("User has been registered");
            } else {
                CreateMessageLabel.setText("Username is taken");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void LoginButtonOnAction(ActionEvent e) {
        if (!LoginUsernameTextField.getText().isBlank() &&
                !LoginPasswordPasswordField.getText().isBlank()
        ) {
            validateLogin(e);
        } else if (LoginUsernameTextField.getText().isBlank() &&
                !LoginPasswordPasswordField.getText().isBlank()
        ) {
            LoginMessageLabel.setText("Enter username");
        } else if (!LoginUsernameTextField.getText().isBlank() &&
                LoginPasswordPasswordField.getText().isBlank()
        ) {
            LoginMessageLabel.setText("Enter password");
        } else {
            LoginMessageLabel.setText("Enter username & password");
        }
    }

    public void validateLogin(ActionEvent a) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM Customer WHERE Username = '" + LoginUsernameTextField.getText() + "' AND Password = '" + LoginPasswordPasswordField.getText() + "';";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    switchToHomePage(a);
                } else {
                    LoginMessageLabel.setText("Invalid login. Please try again");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToHomePage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}