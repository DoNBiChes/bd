module com.example.bd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires h2;
    //requires com.h2database;


    opens com.example.bd to javafx.fxml;
    exports com.example.bd;
}