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
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import java.net.URL;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.ArrayList;

public class IPhoneSearchResultsController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToLoginPage (ActionEvent event) throws IOException {
        search.clear();
        typeColumn.clear();
        IPhoneSearchController.clearSearch();
        IPhoneSearchController.clearTypeColumn();
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    public void switchToIPhoneSearchPage(ActionEvent event) throws IOException {
        search.clear();
        typeColumn.clear();
        IPhoneSearchController.clearSearch();
        IPhoneSearchController.clearTypeColumn();
        root = FXMLLoader.load(getClass().getResource("iPhoneSearch.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private TableView<ProductSearchResultModel> iPhoneTableView;
    @FXML
    private TableColumn<ProductSearchResultModel, String> productTableColumn;
    @FXML
    private TableColumn<ProductSearchResultModel, String> priceTableColumn;
    @FXML
    private TableColumn<ProductSearchResultModel, Void> addTableColumn;

    ObservableList<ProductSearchResultModel> iPhoneSearchModelObservableList = FXCollections.observableArrayList();

    private ArrayList<String> search = IPhoneSearchController.getSearch();
    private ArrayList<String> typeColumn = IPhoneSearchController.getTypeColumn();

    @Override
    public void initialize(URL url, ResourceBundle resource){
        String queryStatement = "SELECT ProductID FROM AppleInventory.iPhone;";

        if(search.size() == 0){
            queryStatement = "SELECT ProductID FROM AppleInventory.iPhone;";
        }
        else if(search.size() == 1){
            queryStatement = "SELECT ProductID FROM AppleInventory.iPhone WHERE " + typeColumn.get(0) + " = '" + search.get(0) + "';";
        }
        else if(search.size() == 2){
            queryStatement = "SELECT ProductID FROM AppleInventory.iPhone WHERE " + typeColumn.get(0) + " = '" + search.get(0) + "' AND "
                    + typeColumn.get(1) + " = '" + search.get(1) + "';";
        }
        else if(search.size() == 3){
            queryStatement = "SELECT ProductID FROM AppleInventory.iPhone WHERE " + typeColumn.get(0) + " = '" + search.get(0) + "' AND "
                    + typeColumn.get(1) + " = '" + search.get(1) + "' AND "
                    + typeColumn.get(2) + " = '" + search.get(2) + "';";

        }
        else if(search.size() == 4){
            queryStatement = "SELECT ProductID FROM AppleInventory.iPhone WHERE " + typeColumn.get(0) + " = '" + search.get(0) + "' AND "
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
                        double queryPrice = rs.getDouble("Price");
                        iPhoneSearchModelObservableList.add(new ProductSearchResultModel(queryProduct, queryPrice));
                    }
                    productTableColumn.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
                    priceTableColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));

                    addTableColumn.setCellFactory(tc -> new TableCell<>() {
                        private final Button btn = new Button("Add");

                        @Override
                        protected void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                            } else {
                                btn.setOnAction(event -> {
                                    ProductSearchResultModel model = getTableView().getItems().get(getIndex());
                                    ShoppingCartController.addCartProduct(model.getProductName());
                                    ShoppingCartController.addCartProductPrices(model.getPrice());
                                });
                                setGraphic(btn);
                            }
                        }
                    });

                    iPhoneTableView.setItems(iPhoneSearchModelObservableList);

                } catch(SQLException e){
                    Logger.getLogger(IPhoneSearchResultsController.class.getName()).log(Level.SEVERE, null, e);
                    e.printStackTrace();
                }
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
