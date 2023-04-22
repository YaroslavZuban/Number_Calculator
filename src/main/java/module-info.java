module com.example.number_calculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.number_calculator to javafx.fxml;
    exports com.example.number_calculator;
    exports com.example.number_calculator.editor_number_systems;
    opens com.example.number_calculator.editor_number_systems to javafx.fxml;
    exports com.example.number_calculator.editor_simple_fraction;
    opens com.example.number_calculator.editor_simple_fraction to javafx.fxml;
    exports com.example.number_calculator.tak;
    opens com.example.number_calculator.tak to javafx.fxml;
}