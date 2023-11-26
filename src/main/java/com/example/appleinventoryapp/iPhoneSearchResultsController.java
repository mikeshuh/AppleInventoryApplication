package com.example.appleinventoryapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import java.net.URL;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class iPhoneSearchResultsController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToLoginPage (ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToiPhoneSearchPage (ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("iPhoneSearch.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private TableView<iPhoneSearchResultsModel> iPhoneTableView;
    @FXML
    private TableColumn<iPhoneSearchResultsModel, String> modelTableColumn;
    @FXML
    private TableColumn<iPhoneSearchResultsModel, String> colorTableColumn;
    @FXML
    private TableColumn<iPhoneSearchResultsModel, String> sizeTableColumn;
    @FXML
    private TableColumn<iPhoneSearchResultsModel, String> chipTableColumn;
    @FXML
    private TableColumn<iPhoneSearchResultsModel, String> priceTableColumn;

    ObservableList<iPhoneSearchResultsModel> iPhoneSearchModelObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String iPhoneQuery = "Select * FROM iPhoneTable";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(iPhoneQuery);

            while (queryOutput.next()) {
                String queryModel = queryOutput.getString("Model");
                String queryColor = queryOutput.getString("Color");
                String querySize = queryOutput.getString("Size");
                String queryChip = queryOutput.getString("Chip");
                String queryPrice = queryOutput.getString("Price");

                iPhoneSearchModelObservableList.add(new iPhoneSearchResultsModel(queryModel, queryColor, querySize, queryChip, queryPrice));

            }

            modelTableColumn.setCellValueFactory(new PropertyValueFactory<>("Model"));
            colorTableColumn.setCellValueFactory(new PropertyValueFactory<>("Color"));
            sizeTableColumn.setCellValueFactory(new PropertyValueFactory<>("Size"));
            chipTableColumn.setCellValueFactory(new PropertyValueFactory<>("Chip"));
            priceTableColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));

            iPhoneTableView.setItems(iPhoneSearchModelObservableList);

        } catch (SQLException e) {
            Logger.getLogger(iPhoneSearchResultsController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

}
