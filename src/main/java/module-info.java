module com.example.appleinventoryapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.appleinventoryapp to javafx.fxml;
    exports com.example.appleinventoryapp;
}