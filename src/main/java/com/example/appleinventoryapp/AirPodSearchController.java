package com.example.appleinventoryapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AirPodSearchController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private ChoiceBox<String> chipChoiceBox;

    @FXML
    private Label chipLabel;

    @FXML
    private ChoiceBox<String> colorChoiceBox;

    @FXML
    private Label colorLabel;

    @FXML
    private Button logoutButton;

    @FXML
    private ChoiceBox<String> modelChoiceBox;

    @FXML
    private Label modelLabel;

    @FXML
    private Button searchButton;

    @FXML
    private Label titleLabel;

    private String[] model = {"Base", "Pro", "Max"};
    private String[] color = {"Space Gray", "Silver", "Pink", "Green", "Sky Blue"};
    private String[] chip = {"H1", "H2"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelChoiceBox.getItems().addAll(model);
        colorChoiceBox.getItems().addAll(color);
        chipChoiceBox.getItems().addAll(chip);
    }
}
