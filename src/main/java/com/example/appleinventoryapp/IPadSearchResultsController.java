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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IPadSearchResultsController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToLoginPage (ActionEvent event) throws IOException {
        search.clear();
        typeColumn.clear();
        IPadSearchController.clearSearch();
        IPadSearchController.clearTypeColumn();
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToIPadSearchPage (ActionEvent event) throws IOException {
        search.clear();
        typeColumn.clear();
        IPadSearchController.clearSearch();
        IPadSearchController.clearTypeColumn();
        root = FXMLLoader.load(getClass().getResource("iPadSearch.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private TableView<ProductSearchResultModel> iPadTableView;
    @FXML
    private TableColumn<ProductSearchResultModel, String> productTableColumn;
    @FXML
    private TableColumn<ProductSearchResultModel, String> priceTableColumn;

    ObservableList<ProductSearchResultModel> iPadSearchModelObservableList = FXCollections.observableArrayList();

    private ArrayList<String> search = IPadSearchController.getSearch();
    private ArrayList<String> typeColumn = IPadSearchController.getTypeColumn();

    @Override
    public void initialize(URL url, ResourceBundle resource){
        System.out.println(search.size());
        String queryStatement = "SELECT ProductID FROM AppleInventory.iPad;";

        if(search.size() == 0){
            queryStatement = "SELECT ProductID FROM AppleInventory.iPad;";
        }
        else if(search.size() == 1){
            queryStatement = "SELECT ProductID FROM AppleInventory.iPad WHERE " + typeColumn.get(0) + " = '" + search.get(0) + "';";
        }
        else if(search.size() == 2){
            queryStatement = "SELECT ProductID FROM AppleInventory.iPad WHERE " + typeColumn.get(0) + " = '" + search.get(0) + "' AND "
                    + typeColumn.get(1) + " = '" + search.get(1) + "';";
        }
        else if(search.size() == 3){
            queryStatement = "SELECT ProductID FROM AppleInventory.iPad WHERE " + typeColumn.get(0) + " = '" + search.get(0) + "' AND "
                    + typeColumn.get(1) + " = '" + search.get(1) + "' AND "
                    + typeColumn.get(2) + " = '" + search.get(2) + "';";

        }
        else if(search.size() == 4){
            queryStatement = "SELECT ProductID FROM AppleInventory.iPad WHERE " + typeColumn.get(0) + " = '" + search.get(0) + "' AND "
                    + typeColumn.get(1) + " = '" + search.get(1) + "' AND "
                    + typeColumn.get(2) + " = '" + search.get(2) + "' AND "
                    + typeColumn.get(3) + " = '" + search.get(3) + "';";
        }

        ResultSet queryResult = DatabaseConnection.query(queryStatement);
        try{
            while(queryResult.next()){
                String q = "SELECT * FROM AppleInventory.Product WHERE ProductID = " + queryResult.getInt(1) + ";";
                ResultSet rs = DatabaseConnection.query(q);
                try{
                    while(rs.next()){
                        String queryProduct = rs.getString("ProductName");
                        String queryPrice = rs.getString("Price");
                        iPadSearchModelObservableList.add(new ProductSearchResultModel(queryProduct, queryPrice));
                    }
                    productTableColumn.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
                    priceTableColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));

                    iPadTableView.setItems(iPadSearchModelObservableList);

                } catch(SQLException e){
                    Logger.getLogger(IPadSearchResultsController.class.getName()).log(Level.SEVERE, null, e);
                    e.printStackTrace();
                }
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}