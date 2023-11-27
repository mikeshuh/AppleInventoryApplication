package com.example.appleinventoryapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToLoginPage (ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    public void switchToMacBookSearchPage (ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("macBookSearch.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToIPadSearchPage (ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("iPadSearch.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToIPhoneSearchPage (ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("iPhoneSearch.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAppleWatchSearchPage (ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("appleWatchSearch.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAirPodsSearchPage (ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("airPodsSearch.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToChargingCableSearchPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("chargingCableSearch.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToShoppingCart(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("shoppingCart.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
