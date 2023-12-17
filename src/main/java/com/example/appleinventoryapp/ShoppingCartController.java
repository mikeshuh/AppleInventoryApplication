package com.example.appleinventoryapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ShoppingCartController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public static LocalDate getCurrentUtcDate() {
        ZonedDateTime nowUtc = ZonedDateTime.now(ZoneId.of("UTC"));
        return nowUtc.toLocalDate();
    }

    private static ArrayList<String> cartProducts = new ArrayList<>();
    private static ArrayList<Double> cartProductPrices = new ArrayList<>();
    private static Map<String, Integer> cartProductQuantities = new HashMap<>();


    public static ArrayList<String> getCartProductss() {
        return new ArrayList<>(cartProducts);
    }

    public static void addCartProduct(String product) {
        cartProducts.add(product);
        cartProductQuantities.put(product, cartProductQuantities.getOrDefault(product, 0) + 1);
    }
    public static void addCartProductPrices(double productPrice) {
        cartProductPrices.add(productPrice);
    }

    @FXML
    private TableView<ProductSearchResultModel> shoppingCartTableView;
    @FXML
    private TableColumn<ProductSearchResultModel, String> productColumn;
    @FXML
    private TableColumn<ProductSearchResultModel, Double> priceColumn;
    @FXML
    private TableColumn<ProductSearchResultModel, Void> removeColumn;

    private void removeItemFromCart(ProductSearchResultModel item) {
        int index = cartProducts.indexOf(item.getProductName());
        if (index != -1) {
            cartProducts.remove(index);
            cartProductPrices.remove(index);
        }

        shoppingCartTableView.getItems().remove(item);

        int newQuantity = cartProductQuantities.get(item.getProductName()) - 1;
        if (newQuantity > 0) {
            cartProductQuantities.put(item.getProductName(), newQuantity);
        } else {
            cartProductQuantities.remove(item.getProductName());
        }
    }


    public void initialize() {
        productColumn.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));

        ObservableList<ProductSearchResultModel> cartItems = FXCollections.observableArrayList();

        for (int i = 0; i < cartProducts.size(); i++) {
            cartItems.add(new ProductSearchResultModel(cartProducts.get(i), cartProductPrices.get(i)));

            removeColumn.setCellFactory(tc -> new TableCell<ProductSearchResultModel, Void>() {
                private final Button btn = new Button("Remove");

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        btn.setOnAction(event -> {
                            ProductSearchResultModel currentItem = getTableView().getItems().get(getIndex());
                            removeItemFromCart(currentItem);
                        });
                        setGraphic(btn);
                    }
                }
            });
        }

        shoppingCartTableView.setItems(cartItems);
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

    @FXML
    private void handleCheckout(ActionEvent event) {
        if (!cartProducts.isEmpty()) {
            double totalPrice = cartProductPrices.stream().mapToDouble(Double::doubleValue).sum();
            int invoiceId = insertInvoiceAndGetId(totalPrice); // Create the invoice once

            for (Map.Entry<String, Integer> entry : cartProductQuantities.entrySet()) {
                String productName = entry.getKey();
                int quantity = entry.getValue();
                int productId = getProductIdByName(productName);
                insertIntoPurchasedProduct(invoiceId, productId, quantity);
            }

            cartProducts.clear();
            cartProductPrices.clear();
            cartProductQuantities.clear();
            shoppingCartTableView.getItems().clear();
        }
    }


    private int insertInvoiceAndGetId(double totalPrice) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet generatedKeys = null;
        int invoiceId = -1;

        try {
            conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO AppleInventory.Invoice (CustomerID, TotalPrice, Date) VALUES (?, ?, '" + getCurrentUtcDate() + "')";
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, LoginController.getCustomerID());
            pstmt.setDouble(2, totalPrice);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating invoice failed, no rows affected.");
            }

            generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                invoiceId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating invoice failed, no ID obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return invoiceId;
    }

    private int getProductIdByName(String productName) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int productId = -1;

        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT ProductID FROM AppleInventory.Product WHERE ProductName = ?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, productName);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                productId = rs.getInt("ProductID");
            } else {
                System.out.println("Product not found: " + productName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productId;
    }

    private void insertIntoPurchasedProduct(int invoiceId, int productId, int quantity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "INSERT INTO AppleInventory.PurchasedProduct (InvoiceID, ProductID, Quantity) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, invoiceId);
            preparedStatement.setInt(2, productId);
            preparedStatement.setInt(3, quantity);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}