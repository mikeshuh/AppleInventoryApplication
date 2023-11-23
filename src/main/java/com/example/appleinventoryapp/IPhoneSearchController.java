package com.example.appleinventoryapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class IPhoneSearchController implements Initializable {

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

    private String[] model = {"Base", "Pro", "Pro Max"};
    private String[] color = {"Natural Titanium", "Blue Titanium", "White Titanium", "Black Titanium", "Blue",
            "Purple", "Yellow", "Red", "Pink", "Green","Midnight", "Starlight"};
    private String[] size = {"6.1-inch", "6.7-inch"};
    private String[] chip = {"A17", "A16", "A15"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelChoiceBox.getItems().addAll(model);
        colorChoiceBox.getItems().addAll(color);
        sizeChoiceBox.getItems().addAll(size);
        chipChoiceBox.getItems().addAll(chip);
    }
}
