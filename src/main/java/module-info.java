module com.example.bum4dot1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires okhttp3;
    requires com.google.gson;


    opens com.example.bum4dot1 to javafx.fxml,com.google.gson;
    exports com.example.bum4dot1;
}