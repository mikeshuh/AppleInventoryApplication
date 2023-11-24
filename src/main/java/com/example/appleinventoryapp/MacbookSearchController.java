package com.example.appleinventoryapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MacbookSearchController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

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

    private String[] model = {"Air", "Pro"};
    private String[] color = {"Gold", "Midnight", "Silver", "Starlight", "Space Black", "Space Gray"};
    private String[] size = {"13 in", "14 in", "15 in", "16 in"};
    private String[] chip = {"M1", "M2", "M3", "M3 Pro", "M3 Max"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelChoiceBox.getItems().addAll(model);
        colorChoiceBox.getItems().addAll(color);
        sizeChoiceBox.getItems().addAll(size);
        chipChoiceBox.getItems().addAll(chip);
    }

    public void switchToLoginPage (ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToHomePage (ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
