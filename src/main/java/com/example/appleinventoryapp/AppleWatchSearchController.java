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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AppleWatchSearchController implements Initializable {

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

    private String[] model = {"SE", "Series", "Ultra"};
    private String[] color = {"Midnight (Aluminum)", "Pink (Aluminum)", "Red (Aluminum)",
            "Silver (Aluminum)", "Starlight (Aluminum)", "Gold (Steel)", "Graphite (Steel)",
            "Silver (Steel)", "Natural (Titanium)"};
    private String[] size = {"40 mm", "41 mm", "44 mm", "45 mm", "49 mm"};
    private String[] chip = {"S8", "S9"};

    private ArrayList<String> search = new ArrayList<>();

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

    public void searchButtonOnAction(ActionEvent a){
        if(modelChoiceBox.getValue() == null &&
                sizeChoiceBox.getValue() == null &&
                chipChoiceBox.getValue() == null &&
                colorChoiceBox.getValue() == null
        ){
            System.out.println("All null");
        }
        if(modelChoiceBox.getValue() != null){
            search.add(modelChoiceBox.getValue());
        }
        if(sizeChoiceBox.getValue() != null){
            search.add(sizeChoiceBox.getValue());
        }
        if(chipChoiceBox.getValue() != null){
            search.add(chipChoiceBox.getValue());
        }
        if(colorChoiceBox.getValue() != null){
            search.add(colorChoiceBox.getValue());
        }
        if(modelChoiceBox.getValue() != null ||
                sizeChoiceBox.getValue() != null ||
                chipChoiceBox.getValue() != null ||
                colorChoiceBox.getValue() != null){
            System.out.println(search);
        }
        search.clear();
    }
}
