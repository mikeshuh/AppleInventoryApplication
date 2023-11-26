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

public class ChargingCableSearchController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button backButton;
    @FXML
    private Button logoutButton;
    @FXML
    private ChoiceBox<String> modelChoiceBox;
    @FXML
    private ChoiceBox<String> lengthChoiceBox;
    @FXML
    private Label modelLabel;
    @FXML
    private Button searchButton;
    @FXML
    private Label titleLabel;

    private String[] model = {"USB-C to Lightning", "USB-C to USB-C", "USB-C to MagSafe"};
    private String[] length = {"1 m", "2 m"};

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
        lengthChoiceBox.getItems().addAll(length);
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

    public void switchToChargingCableSearchResults(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("chargingCableSearchResults.fxml"));
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
        if(lengthChoiceBox.getValue() != null){
            typeColumn.add("Length");
            search.add(lengthChoiceBox.getValue());
        }
        switchToChargingCableSearchResults(a);
    }
}
