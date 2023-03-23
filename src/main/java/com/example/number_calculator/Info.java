package com.example.number_calculator;

import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Info {
    public static void information(String line, Pane main) {// Строка Line - прописывает какая именно ошибка произошла Pane - от какой панели будет выводиться ошибка
        Alert alert = new Alert(Alert.AlertType.INFORMATION);//значек информации
        alert.initOwner((Stage) main.getScene().getWindow().getScene().getWindow()); // создается окно, на основы панели
        alert.setTitle("Ошибка");// названия окна
        // Header Text: null
        alert.setHeaderText(null);//текст заголовка, в данном случаи он нам не нужен
        alert.setContentText(line);// текст который будет выводиться в окне

        alert.showAndWait();//Показывает диалоговое окно и ожидает ответа пользователя
    }
}
