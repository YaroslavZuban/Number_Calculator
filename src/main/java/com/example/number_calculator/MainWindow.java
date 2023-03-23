package com.example.number_calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainWindow extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("MainWindowInterface.fxml"));
        Scene scene=new Scene(fxmlLoader.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setTitle("Калькулятор");
        stage.initStyle(StageStyle.TRANSPARENT);//убираем вверхнее меню, закрыть, свернуть приложение
        stage.setResizable(false);//нельяз расширять окно
        stage.setScene(scene);//установление сцены
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/ico/calculator.png")));
        ((MainController)fxmlLoader.getController()).init(stage);//устанавливаем сцену
        stage.show();//отображаем сцену
    }

    public static void main(String[] args) {
        launch();
    }
}