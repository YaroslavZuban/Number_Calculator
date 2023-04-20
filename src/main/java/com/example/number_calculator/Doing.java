package com.example.number_calculator;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

/**
 * Данный класс представляет собой контроллер для интерфейса пользователя на основе JavaFX.
 * Он содержит метод setResult, который устанавливает текст в объекте Text с идентификатором
 * result. Этот метод может вызываться из другого класса для установки текста в объекте
 * Text, который будет отображаться на экране в пользовательском интерфейсе.
 */
public class Doing {
    @FXML
    private Text result;

    public void setResult(String text){
        result.setText(text);
    }
}
