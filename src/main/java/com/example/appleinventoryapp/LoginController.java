package com.example.appleinventoryapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private Button CreateAccountButton;

    @FXML
    private Button LoginButton;

    @FXML
    private Label CreateMessageLabel;

    @FXML
    private Label LoginMessageLabel;

    public void CreateAccountButtonOnAction(ActionEvent e){
        CreateMessageLabel.setText("Fill Out All Fields");
    }

    public void LoginButtonOnAction(ActionEvent e){
        LoginMessageLabel.setText("Fill Out All Fields");
    }
}