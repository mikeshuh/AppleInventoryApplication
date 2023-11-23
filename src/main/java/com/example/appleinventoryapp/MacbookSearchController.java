package com.example.appleinventoryapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MacbookSearchController implements Initializable {

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

    private String[] model = {"Base", "Pro", "Air"};
    private String[] color = {"Space Gray", "Gold", "Silver", "Midnight", "Starlight", "Space Black"};
    private String[] size = {"13-inch", "14-inch", "15-inch", "16-inch"};
    private String[] chip = {"M1", "M2", "M3", "M3 Pro", "M3 Max"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelChoiceBox.getItems().addAll(model);
        colorChoiceBox.getItems().addAll(color);
        sizeChoiceBox.getItems().addAll(size);
        chipChoiceBox.getItems().addAll(chip);
    }
}
