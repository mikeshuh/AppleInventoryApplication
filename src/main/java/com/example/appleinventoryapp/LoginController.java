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

import java.io.IOException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button createAccountButton;
    @FXML
    private Label createMessageLabel;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField createUsernameTextField;
    @FXML
    private PasswordField createPasswordPasswordField;
    @FXML
    private PasswordField createConfirmPasswordPasswordField;

    @FXML
    private Button loginButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField loginUsernameTextField;
    @FXML
    private PasswordField loginPasswordPasswordField;

    public void createAccountButtonOnAction() {
        if (firstNameTextField.getText().isBlank() ||
                lastNameTextField.getText().isBlank() ||
                createUsernameTextField.getText().isBlank() ||
                createPasswordPasswordField.getText().isBlank() ||
                createConfirmPasswordPasswordField.getText().isBlank()
        ) {
            createMessageLabel.setText("Fill out all fields");
        } else if (!createPasswordPasswordField.getText().equals(createConfirmPasswordPasswordField.getText())) {
            createMessageLabel.setText("Passwords do not match");
        } else {
            createAccount();
        }
    }

    public void createAccount() {
        ResultSet queryResult = DatabaseConnection.query("SELECT Username From AppleInventory.Customer;");

        boolean uniqueUsername = true;
        try{
            while (queryResult.next()) {
                if (createUsernameTextField.getText().equals(queryResult.getString(1))) {
                    uniqueUsername = false;
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        if (uniqueUsername) {
            DatabaseConnection.executeSQL(
                    "INSERT INTO AppleInventory.Customer (Username, Password, FirstName, LastName) VALUES ('" +
                            createUsernameTextField.getText() + "', '" + createPasswordPasswordField.getText() + "', '" +
                            firstNameTextField.getText() + "', '" + lastNameTextField.getText() + "');"
            );
            createMessageLabel.setText("User has been registered");
        } else {
            createMessageLabel.setText("Username is taken");
        }
    }

    public void loginButtonOnAction(ActionEvent a) {
        if (!loginUsernameTextField.getText().isBlank() &&
                !loginPasswordPasswordField.getText().isBlank()
        ) {
            validateLogin(a);
        } else if (loginUsernameTextField.getText().isBlank() &&
                !loginPasswordPasswordField.getText().isBlank()
        ) {
            loginMessageLabel.setText("Enter username");
        } else if (!loginUsernameTextField.getText().isBlank() &&
                loginPasswordPasswordField.getText().isBlank()
        ) {
            loginMessageLabel.setText("Enter password");
        } else {
            loginMessageLabel.setText("Enter username & password");
        }
    }

    public void validateLogin(ActionEvent a) {
        ResultSet queryResult = DatabaseConnection.query(
                "SELECT count(1) FROM AppleInventory.Customer WHERE Username = '" +
                        loginUsernameTextField.getText() + "' AND Password = '" +
                        loginPasswordPasswordField.getText() + "';"
        );

        try {
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    switchToHomePage(a);
                } else {
                    loginMessageLabel.setText("Invalid login. Please try again");
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToHomePage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }
}