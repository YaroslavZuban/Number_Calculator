package com.example.number_calculator;

import javafx.stage.Stage;

public class MyController implements IController{
    private Stage stage;

    @Override
    public void init(Stage stage) {
        this.stage=stage;
    }
}
