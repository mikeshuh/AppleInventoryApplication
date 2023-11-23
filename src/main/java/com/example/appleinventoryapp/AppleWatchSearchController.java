package com.example.appleinventoryapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AppleWatchSearchController implements Initializable {

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

    private String[] model = {"SE", "Series", "Ultra"};
    private String[] color = {"Midnight", "Starlight", "Silver", "Pink", "Red", "Gold", "Silver", "Graphite"};
    private String[] size = {"40-mm", "41-mm", "44-mm", "45-mm"};
    private String[] chip = {"S8 SiP", "S9 SiP"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelChoiceBox.getItems().addAll(model);
        colorChoiceBox.getItems().addAll(color);
        sizeChoiceBox.getItems().addAll(size);
        chipChoiceBox.getItems().addAll(chip);
    }
}
