package com.example.number_calculator;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class Doing {
    @FXML
    private Text result;

    public void setResult(String text){
        result.setText(text);
    }
}
