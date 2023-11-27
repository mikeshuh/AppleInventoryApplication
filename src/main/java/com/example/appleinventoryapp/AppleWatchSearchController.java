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

    private static ArrayList<String> typeColumn = new ArrayList<>();
    private static ArrayList<String> search = new ArrayList<>();

    public static ArrayList<String> getTypeColumn(){
        return new ArrayList<>(typeColumn);
    }
    public static ArrayList<String> getSearch(){
        return new ArrayList<>(search);
    }
    public static void clearTypeColumn(){
        typeColumn.clear();
    }
    public static void clearSearch(){
        search.clear();
    }
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
        stage.centerOnScreen();
    }

    public void switchToHomePage (ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAppleWatchSearchResults(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("appleWatchSearchResults.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void searchButtonOnAction(ActionEvent a) throws IOException{
        if(modelChoiceBox.getValue() != null){
            typeColumn.add("Model");
            search.add(modelChoiceBox.getValue());
        }
        if(sizeChoiceBox.getValue() != null){
            typeColumn.add("ScreenSize");
            search.add(sizeChoiceBox.getValue());
        }
        if(chipChoiceBox.getValue() != null){
            typeColumn.add("Chip");
            search.add(chipChoiceBox.getValue());
        }
        if(colorChoiceBox.getValue() != null){
            typeColumn.add("Color");
            search.add(colorChoiceBox.getValue());
        }
        switchToAppleWatchSearchResults(a);
    }
}
