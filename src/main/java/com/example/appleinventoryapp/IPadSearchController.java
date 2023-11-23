package com.example.appleinventoryapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class IPadSearchController implements Initializable {

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
    private ChoiceBox<String> sizeChoiceBox;

    @FXML
    private Label sizeLabel;

    @FXML
    private Label titleLabel;

    private String[] model = {"Base", "Pro", "Air", "Mini"};
    private String[] color = {"Space Gray", "Silver", "Purple", "Pink", "Blue", "Starlight", "Yellow"};
    private String[] size = {"12.9-inch", "11-inch", "10.9-inch", "8.3-inch"};
    private String[] chip = {"M2", "M1", "A15", "A14", "A13"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelChoiceBox.getItems().addAll(model);
        colorChoiceBox.getItems().addAll(color);
        sizeChoiceBox.getItems().addAll(size);
        chipChoiceBox.getItems().addAll(chip);
    }
}
