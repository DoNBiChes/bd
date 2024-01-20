module com.example.bd {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bd to javafx.fxml;
    exports com.example.bd;
}