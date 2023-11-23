package com.example.appleinventoryapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.lang.module.ModuleDescriptor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {
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

    public void CreateAccountButtonOnAction(ActionEvent e){
        if(     FirstNameTextField.getText().isBlank() ||
                LastNameTextField.getText().isBlank() ||
                CreateUsernameTextField.getText().isBlank() ||
                CreatePasswordPasswordField.getText().isBlank() ||
                CreateConfirmPasswordPasswordField.getText().isBlank()
        ){
            CreateMessageLabel.setText("Fill Out All Fields");
        }
        else{

        }
    }

    public void LoginButtonOnAction(ActionEvent e){
        if(     !LoginUsernameTextField.getText().isBlank() &&
                !LoginPasswordPasswordField.getText().isBlank()
        ){
            validateLogin();
        }
        else if(LoginUsernameTextField.getText().isBlank() &&
                !LoginPasswordPasswordField.getText().isBlank()
        ){
            LoginMessageLabel.setText("Enter Username");
        }
        else if(! LoginUsernameTextField.getText().isBlank() &&
                LoginPasswordPasswordField.getText().isBlank()
        ){
            LoginMessageLabel.setText("Enter Password");
        }
        else{
            LoginMessageLabel.setText("Enter Username & Password");
        }
    }

    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM Customer WHERE Username = '" + LoginUsernameTextField.getText() + "' AND Password = '" + LoginPasswordPasswordField.getText() + "';";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    LoginMessageLabel.setText("Welcome");
                }
                else{
                    LoginMessageLabel.setText("Invalid Login. Please Try Again");
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

}