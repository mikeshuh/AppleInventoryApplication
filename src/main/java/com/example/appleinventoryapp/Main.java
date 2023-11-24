package com.example.appleinventoryapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Apple Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void createDatabase() {
        String createDB = "CREATE DATABASE IF NOT EXISTS AppleInventory;";
        String customerTable =
                "CREATE TABLE IF NOT EXISTS AppleInventory.Customer ("
                + "CustomerID INT AUTO_INCREMENT PRIMARY KEY, "
                + "Username VARCHAR(255) NOT NULL UNIQUE, "
                + "Password VARCHAR(255) NOT NULL, "
                + "FirstName VARCHAR(255) NOT NULL, "
                + "LastName VARCHAR(255) NOT NULL"
                + ");";
        String productTable =
                "CREATE TABLE IF NOT EXISTS AppleInventory.Product ("
                + "    ProductID INT AUTO_INCREMENT PRIMARY KEY, "
                + "    ProductName VARCHAR(255) NOT NULL, "
                + "    Price DECIMAL(10, 2) NOT NULL"
                + ");";
        String invoiceTable =
                "CREATE TABLE IF NOT EXISTS AppleInventory.Invoice ("
                + "    InvoiceID INT AUTO_INCREMENT PRIMARY KEY, "
                + "    CustomerID INT NOT NULL, "
                + "    TotalPrice DECIMAL(10, 2) NOT NULL, "
                + "    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)"
                + ");";
        String purchasedProductTable =
                "CREATE TABLE IF NOT EXISTS AppleInventory.PurchasedProduct ("
                + "    InvoiceID INT NOT NULL, "
                + "    ProductID INT NOT NULL, "
                + "    PRIMARY KEY (InvoiceID, ProductID), "
                + "    FOREIGN KEY (InvoiceID) REFERENCES Invoice(InvoiceID), "
                + "    FOREIGN KEY (ProductID) REFERENCES Product(ProductID)"
                + ");";
        String macBookTable =
                "CREATE TABLE IF NOT EXISTS AppleInventory.MacBook ("
                + "    MacBookID INT AUTO_INCREMENT PRIMARY KEY, "
                + "    ProductID INT NOT NULL UNIQUE, "
                + "    Model VARCHAR(255) NOT NULL, "
                + "    ScreenSize VARCHAR(255) NOT NULL, "
                + "    Chip VARCHAR(255) NOT NULL, "
                + "    Color VARCHAR(255) NOT NULL, "
                + "    FOREIGN KEY (ProductID) REFERENCES Product(ProductID)"
                + ");";
        String iPhoneTable =
                "CREATE TABLE IF NOT EXISTS AppleInventory.iPhone ("
                + "    iPhoneID INT AUTO_INCREMENT PRIMARY KEY, "
                + "    ProductID INT NOT NULL UNIQUE, "
                + "    Model VARCHAR(255) NOT NULL, "
                + "    ScreenSize VARCHAR(255) NOT NULL, "
                + "    Chip VARCHAR(255) NOT NULL, "
                + "    Color VARCHAR(255) NOT NULL, "
                + "    FOREIGN KEY (ProductID) REFERENCES Product(ProductID)"
                + ");";
        String iPadTable =
                "CREATE TABLE IF NOT EXISTS AppleInventory.iPad ("
                + "    iPadID INT AUTO_INCREMENT PRIMARY KEY, "
                + "    ProductID INT UNIQUE, "
                + "    Model VARCHAR(255), "
                + "    ScreenSize VARCHAR(255), "
                + "    Chip VARCHAR(255), "
                + "    Color VARCHAR(255), "
                + "    FOREIGN KEY (ProductID) REFERENCES Product(ProductID)"
                + ");";
        String appleWatchTable =
                "CREATE TABLE IF NOT EXISTS AppleInventory.AppleWatch ("
                + "    AppleWatchID INT AUTO_INCREMENT PRIMARY KEY, "
                + "    ProductID INT UNIQUE NOT NULL, "
                + "    Model VARCHAR(255) NOT NULL, "
                + "    ScreenSize VARCHAR(255) NOT NULL, "
                + "    Chip VARCHAR(255) NOT NULL, "
                + "    Color VARCHAR(255) NOT NULL, "
                + "    FOREIGN KEY (ProductID) REFERENCES Product(ProductID)"
                + ");";
        String airPodsTable =
                "CREATE TABLE IF NOT EXISTS AppleInventory.AirPods ("
                + "    AirPodsID INT AUTO_INCREMENT PRIMARY KEY, "
                + "    ProductID INT NOT NULL UNIQUE, "
                + "    Model VARCHAR(255) NOT NULL, "
                + "    Chip VARCHAR(255) NOT NULL, "
                + "    Color VARCHAR(255), "
                + "    FOREIGN KEY (ProductID) REFERENCES Product(ProductID)"
                + ");";
        String chargingCableTable =
                "CREATE TABLE IF NOT EXISTS AppleInventory.ChargingCable ("
                + "    ChargingCableID INT AUTO_INCREMENT PRIMARY KEY, "
                + "    ProductID INT NOT NULL UNIQUE, "
                + "    Model VARCHAR(255) NOT NULL, "
                + "    Length VARCHAR(255) NOT NULL, "
                + "    FOREIGN KEY (ProductID) REFERENCES Product(ProductID)"
                + ");";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            // create DB
            stmt.execute(createDB);
            System.out.println("Database Created");

            // create customer table
            stmt.executeUpdate(customerTable);
            System.out.println("Created Customer table");

            // create product table
            stmt.execute(productTable);
            System.out.println("Created Product table");

            // create invoice table
            stmt.execute(invoiceTable);
            System.out.println("Created Invoice Table");

            // create purchased product table
            stmt.execute(purchasedProductTable);
            System.out.println("Created PurchasedProduct Table");

            // create macbook table
            stmt.execute(macBookTable);
            System.out.println("Created MacBook Table");

            // create iphone table
            stmt.execute(iPhoneTable);
            System.out.println("Created iPhone Table");

            // create iPad table
            stmt.execute(iPadTable);
            System.out.println("Created iPad Table");

            // create apple watch table
            stmt.execute(appleWatchTable);
            System.out.println("Created AppleWatch Table");

            // create airpods table
            stmt.execute(airPodsTable);
            System.out.println("Created AirPods Table");

            // create charging cable table
            stmt.execute(chargingCableTable);
            System.out.println("Created ChargingCable Table");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void populateDatabase() {
        String insertProductMacBook =
                "INSERT INTO AppleInventory.Product (ProductName, Price) VALUES" +
                "('MacBook Air 13-inch M1 Space Gray', 999.00)," +
                "('MacBook Air 13-inch M1 Silver', 999.00)," +
                "('MacBook Air 13-inch M1 Gold', 999.00)," +
                "('MacBook Air 13-inch M2 Midnight', 1099.00)," +
                "('MacBook Air 13-inch M2 Starlight', 1099.00)," +
                "('MacBook Air 13-inch M2 Space Gray', 1099.00)," +
                "('MacBook Air 13-inch M2 Silver', 1099.00)," +
                "('MacBook Air 15-inch M2 Midnight', 1299.00)," +
                "('MacBook Air 15-inch M2 Starlight', 1299.00)," +
                "('MacBook Air 15-inch M2 Space Gray', 1299.00)," +
                "('MacBook Air 15-inch M2 Silver', 1299.00)," +
                "('MacBook Pro 14-inch M3 Space Gray', 1599.00)," +
                "('MacBook Pro 14-inch M3 Silver', 1599.00)," +
                "('MacBook Pro 14-inch M3 Pro Space Black', 1999.00)," +
                "('MacBook Pro 14-inch M3 Pro Silver', 1999.00)," +
                "('MacBook Pro 14-inch M3 Max Space Black', 3199.00)," +
                "('MacBook Pro 14-inch M3 Max Silver', 3199.00)," +
                "('MacBook Pro 16-inch M3 Pro Space Black', 2499.00)," +
                "('MacBook Pro 16-inch M3 Pro Silver', 2499.00)," +
                "('MacBook Pro 16-inch M3 Max Space Black', 3499.00)," +
                "('MacBook Pro 16-inch M3 Max Silver', 3499.00);";
        String insertMacBook =
                "INSERT INTO AppleInventory.MacBook (ProductID, Model, ScreenSize, Chip, Color) VALUES" +
                "(1, 'Air', '13 in', 'M1', 'Space Gray')," +
                "(2, 'Air', '13 in', 'M1', 'Silver')," +
                "(3, 'Air', '13 in', 'M1', 'Gold')," +
                "(4, 'Air', '13 in', 'M2', 'Midnight')," +
                "(5, 'Air', '13 in', 'M2', 'Starlight')," +
                "(6, 'Air', '13 in', 'M2', 'Space Gray')," +
                "(7, 'Air', '13 in', 'M2', 'Silver')," +
                "(8, 'Air', '15 in', 'M2', 'Midnight')," +
                "(9, 'Air', '15 in', 'M2', 'Starlight')," +
                "(10, 'Air', '15 in', 'M2', 'Space Gray')," +
                "(11, 'Air', '15 in', 'M2', 'Silver')," +
                "(12, 'Pro', '14 in', 'M3', 'Space Gray')," +
                "(13, 'Pro', '14 in', 'M3', 'Silver')," +
                "(14, 'Pro', '14 in', 'M3 Pro', 'Space Black')," +
                "(15, 'Pro', '14 in', 'M3 Pro', 'Silver')," +
                "(16, 'Pro', '14 in', 'M3 Max', 'Space Black')," +
                "(17, 'Pro', '14 in', 'M3 Max', 'Silver')," +
                "(18, 'Pro', '16 in', 'M3 Pro', 'Space Black')," +
                "(19, 'Pro', '16 in', 'M3 Pro', 'Silver')," +
                "(20, 'Pro', '16 in', 'M3 Max', 'Space Black')," +
                "(21, 'Pro', '16 in', 'M3 Max', 'Silver');";
        String insertProductIPad =
                "INSERT INTO AppleInventory.Product (ProductName, Price) VALUES" +
                "('iPad 9th generation Space Gray', 329.00)," +
                "('iPad 9th generation Silver', 329.00)," +
                "('iPad 10th generation Silver', 449.00)," +
                "('iPad 10th generation Pink', 449.00)," +
                "('iPad 10th generation Blue', 449.00)," +
                "('iPad 10th generation Yellow', 449.00)," +
                "('iPad Air 5th generation Space Gray', 599.00)," +
                "('iPad Air 5th generation Starlight', 599.00)," +
                "('iPad Air 5th generation Pink', 599.00)," +
                "('iPad Air 5th generation Purple', 599.00)," +
                "('iPad Air 5th generation Blue', 599.00)," +
                "('iPad Mini 6th generation Space Gray', 499.00)," +
                "('iPad Mini 6th generation Starlight', 499.00)," +
                "('iPad Mini 6th generation Pink', 499.00)," +
                "('iPad Mini 6th generation Purple', 499.00)," +
                "('iPad Pro 4th generation 11-inch Space Gray', 799.00)," +
                "('iPad Pro 4th generation 11-inch Silver', 799.00)," +
                "('iPad Pro 6th generation 12.9-inch Space Gray', 1099.00)," +
                "('iPad Pro 6th generation 12.9-inch Silver', 1099.00);";
        String insertIPad =
                "INSERT INTO AppleInventory.iPad (ProductID, Model, ScreenSize, Chip, Color) VALUES" +
                "(22, 'Base', '10.2 in', 'A13', 'Space Gray')," +
                "(23, 'Base', '10.2 in', 'A13', 'Silver')," +
                "(24, 'Base', '10.9 in', 'A14', 'Silver')," +
                "(25, 'Base', '10.9 in', 'A14', 'Pink')," +
                "(26, 'Base', '10.9 in', 'A14', 'Blue')," +
                "(27, 'Base', '10.9 in', 'A14', 'Yellow')," +
                "(28, 'Air', '10.9 in', 'M1', 'Space Gray')," +
                "(29, 'Air', '10.9 in', 'M1', 'Starlight')," +
                "(30, 'Air', '10.9 in', 'M1', 'Pink')," +
                "(31, 'Air', '10.9 in', 'M1', 'Purple')," +
                "(32, 'Air', '10.9 in', 'M1', 'Blue')," +
                "(33, 'Mini', '8.3 in', 'A15', 'Space Gray')," +
                "(34, 'Mini', '8.3 in', 'A15', 'Starlight')," +
                "(35, 'Mini', '8.3 in', 'A15', 'Pink')," +
                "(36, 'Mini', '8.3 in', 'A15', 'Purple')," +
                "(37, 'Pro', '11 in', 'M2', 'Space Gray')," +
                "(38, 'Pro', '11 in', 'M2', 'Silver')," +
                "(39, 'Pro', '12.9 in', 'M2', 'Space Gray')," +
                "(40, 'Pro', '12.9 in', 'M2', 'Silver');";
        String insertProductIPhone =
                "INSERT INTO AppleInventory.Product (ProductName, Price) VALUES" +
                "('iPhone 15 Pink', 799.00)," +
                "('iPhone 15 Yellow', 799.00)," +
                "('iPhone 15 Green', 799.00)," +
                "('iPhone 15 Blue', 799.00)," +
                "('iPhone 15 Black', 799.00)," +
                "('iPhone 15 Plus Pink', 899.00)," +
                "('iPhone 15 Plus Yellow', 899.00)," +
                "('iPhone 15 Plus Green', 899.00)," +
                "('iPhone 15 Plus Blue', 899.00)," +
                "('iPhone 15 Plus Black', 899.00)," +
                "('iPhone 15 Pro Natural Titanium', 999.00)," +
                "('iPhone 15 Pro Blue Titanium', 999.00)," +
                "('iPhone 15 Pro White Titanium', 999.00)," +
                "('iPhone 15 Pro Black Titanium', 999.00)," +
                "('iPhone 15 Pro Max Natural Titanium', 1199.00)," +
                "('iPhone 15 Pro Max Blue Titanium', 1199.00)," +
                "('iPhone 15 Pro Max White Titanium', 1199.00)," +
                "('iPhone 15 Pro Max Black Titanium', 1199.00);";
        String insertIPhone =
                "INSERT INTO AppleInventory.iPhone (ProductID, Model, ScreenSize, Chip, Color) VALUES" +
                "(41, 'Base', '6.1 in', 'A16', 'Pink')," +
                "(42, 'Base', '6.1 in', 'A16', 'Yellow')," +
                "(43, 'Base', '6.1 in', 'A16', 'Green')," +
                "(44, 'Base', '6.1 in', 'A16', 'Blue')," +
                "(45, 'Base', '6.1 in', 'A16', 'Black')," +
                "(46, 'Plus', '6.7 in', 'A16', 'Pink')," +
                "(47, 'Plus', '6.7 in', 'A16', 'Yellow')," +
                "(48, 'Plus', '6.7 in', 'A16', 'Green')," +
                "(49, 'Plus', '6.7 in', 'A16', 'Blue')," +
                "(50, 'Plus', '6.7 in', 'A16', 'Black')," +
                "(51, 'Pro', '6.1 in', 'A17', 'Natural Titanium')," +
                "(52, 'Pro', '6.1 in', 'A17', 'Blue Titanium')," +
                "(53, 'Pro', '6.1 in', 'A17', 'White Titanium')," +
                "(54, 'Pro', '6.1 in', 'A17', 'Black Titanium')," +
                "(55, 'Pro Max', '6.7 in', 'A17', 'Natural Titanium')," +
                "(56, 'Pro Max', '6.7 in', 'A17', 'Blue Titanium')," +
                "(57, 'Pro Max', '6.7 in', 'A17', 'White Titanium')," +
                "(58, 'Pro Max', '6.7 in', 'A17', 'Black Titanium');";
        String insertProductAppleWatch =
                "INSERT INTO AppleInventory.Product (ProductName, Price) VALUES" +
                "('Apple Watch SE 40 mm Midnight (Aluminum)', 279.00)," +
                "('Apple Watch SE 40 mm Starlight (Aluminum)', 279.00)," +
                "('Apple Watch SE 40 mm Silver (Aluminum)', 279.00)," +
                "('Apple Watch SE 44 mm Midnight (Aluminum)', 309.00)," +
                "('Apple Watch SE 44 mm Starlight (Aluminum)', 309.00)," +
                "('Apple Watch SE 44 mm Silver (Aluminum)', 309.00)," +
                "('Apple Watch Series 9 41 mm Midnight (Aluminum)', 399.00)," +
                "('Apple Watch Series 9 41 mm Starlight (Aluminum)', 399.00)," +
                "('Apple Watch Series 9 41 mm Silver (Aluminum)', 399.00)," +
                "('Apple Watch Series 9 41 mm Pink (Aluminum)', 399.00)," +
                "('Apple Watch Series 9 41 mm Red (Aluminum)', 399.00)," +
                "('Apple Watch Series 9 41 mm Graphite (Steel)', 699.00)," +
                "('Apple Watch Series 9 41 mm Silver (Steel)', 699.00)," +
                "('Apple Watch Series 9 41 mm Gold (Steel)', 699.00)," +
                "('Apple Watch Series 9 45 mm Midnight (Aluminum)', 429.00)," +
                "('Apple Watch Series 9 45 mm Starlight (Aluminum)', 429.00)," +
                "('Apple Watch Series 9 45 mm Silver (Aluminum)', 429.00)," +
                "('Apple Watch Series 9 45 mm Pink (Aluminum)', 429.00)," +
                "('Apple Watch Series 9 45 mm Red (Aluminum)', 429.00)," +
                "('Apple Watch Series 9 45 mm Graphite (Steel)', 749.00)," +
                "('Apple Watch Series 9 45 mm Silver (Steel)', 749.00)," +
                "('Apple Watch Series 9 45 mm Gold (Steel)', 749.00)," +
                "('Apple Watch Ultra 2 49 mm Natural (Titanium)', 799.00);";
        String insertAppleWatch =
                "INSERT INTO AppleInventory.AppleWatch (ProductID, Model, ScreenSize, Chip, Color) VALUES" +
                "(59, 'SE', '40 mm', 'S8', 'Midnight (Aluminum)')," +
                "(60, 'SE', '40 mm', 'S8', 'Starlight (Aluminum)')," +
                "(61, 'SE', '40 mm', 'S8', 'Silver (Aluminum)')," +
                "(62, 'SE', '44 mm', 'S8', 'Midnight (Aluminum)')," +
                "(63, 'SE', '44 mm', 'S8', 'Starlight (Aluminum)')," +
                "(64, 'SE', '44 mm', 'S8', 'Silver (Aluminum)')," +
                "(65, 'Series', '41 mm', 'S9', 'Midnight (Aluminum)')," +
                "(66, 'Series', '41 mm', 'S9', 'Starlight (Aluminum)')," +
                "(67, 'Series', '41 mm', 'S9', 'Silver (Aluminum)')," +
                "(68, 'Series', '41 mm', 'S9', 'Pink (Aluminum)')," +
                "(69, 'Series', '41 mm', 'S9', 'Red (Aluminum)')," +
                "(70, 'Series', '41 mm', 'S9', 'Graphite (Steel)')," +
                "(71, 'Series', '41 mm', 'S9', 'Silver (Steel)')," +
                "(72, 'Series', '41 mm', 'S9', 'Gold (Steel)')," +
                "(73, 'Series', '45 mm', 'S9', 'Midnight (Aluminum)')," +
                "(74, 'Series', '45 mm', 'S9', 'Starlight (Aluminum)')," +
                "(75, 'Series', '45 mm', 'S9', 'Silver (Aluminum)')," +
                "(76, 'Series', '45 mm', 'S9', 'Pink (Aluminum)')," +
                "(77, 'Series', '45 mm', 'S9', 'Red (Aluminum)')," +
                "(78, 'Series', '45 mm', 'S9', 'Graphite (Steel)')," +
                "(79, 'Series', '45 mm', 'S9', 'Silver (Steel)')," +
                "(80, 'Series', '45 mm', 'S9', 'Gold (Steel)')," +
                "(81, 'Ultra', '49 mm', 'S9', 'Natural (Titanium)');";
        String insertProductAirPods =
                "INSERT INTO AppleInventory.Product (ProductName, Price) VALUES" +
                "('AirPods 3rd Generation Lightning', 169.00)," +
                "('AirPods 3rd Generation MagSafe', 179.00)," +
                "('AirPods Pro 2nd Generation', 249.00)," +
                "('AirPods Max Silver', 549.00)," +
                "('AirPods Max Space Gray', 549.00)," +
                "('AirPods Max Sky Blue', 549.00)," +
                "('AirPods Max Pink', 549.00)," +
                "('AirPods Max Green', 549.00);";
        String insertAirPods =
                "INSERT INTO AppleInventory.AirPods (ProductID, Model, Chip, Color) VALUES" +
                "(82, 'Base Lightning', 'H1', 'White')," +
                "(83, 'Base MagSafe', 'H1', 'White')," +
                "(84, 'Pro', 'H2', 'White')," +
                "(85, 'Max', 'H1', 'Silver')," +
                "(86, 'Max', 'H1', 'Space Gray')," +
                "(87, 'Max', 'H1', 'Sky Blue')," +
                "(88, 'Max', 'H1', 'Pink')," +
                "(89, 'Max', 'H1', 'Green');";
        String insertProductChargingCable =
                "INSERT INTO AppleInventory.Product (ProductName, Price) VALUES" +
                "('USB-C to Lightning Cable 1 m', 19.00)," +
                "('USB-C to Lightning Cable 2 m', 29.00)," +
                "('USB-C to USB-C Cable 1 m', 19.00)," +
                "('USB-C to USB-C Cable 2 m', 29.00)," +
                "('USB-C to MagSafe Charger 2 m', 49.00);";
        String insertChargingCable =
                "INSERT INTO AppleInventory.ChargingCable (ProductID, Model, Length) VALUES" +
                "(90, 'Lightning', '1 m')," +
                "(91, 'Lightning', '2 m')," +
                "(92, 'USB-C', '1 m')," +
                "(93, 'USB-C', '2 m')," +
                "(94, 'MagSafe', '2 m');";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            // insert macbook products
            stmt.execute(insertProductMacBook);
            stmt.execute(insertMacBook);
            System.out.println("Populated Database with MacBooks");

            // insert ipad products
            stmt.execute(insertProductIPad);
            stmt.execute(insertIPad);
            System.out.println("Populated Database with iPads");

            // insert iphone products
            stmt.execute(insertProductIPhone);
            stmt.execute(insertIPhone);
            System.out.println("Populated Database with iPhones");

            // insert apple watch products
            stmt.execute(insertProductAppleWatch);
            stmt.execute(insertAppleWatch);
            System.out.println("Populated Database with Apple Watches");

            // insert airpods products
            stmt.execute(insertProductAirPods);
            stmt.execute(insertAirPods);
            System.out.println("Populated Database with AirPods");

            // insert charging cable products
            stmt.execute(insertProductChargingCable);
            stmt.execute(insertChargingCable);
            System.out.println("Populated Database with Charing Cables");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createDatabase();
        populateDatabase();

        launch();
    }
}