module com.example.number_calculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.number_calculator to javafx.fxml;
    exports com.example.number_calculator;
}