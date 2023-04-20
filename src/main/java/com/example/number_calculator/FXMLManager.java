package com.example.number_calculator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class FXMLManager {
    public <T> T loadFXML(String fxmlPath, String title, StageStyle style) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        fxmlLoader.setControllerFactory(param -> {
            try {
                // создаем новый экземпляр контроллера
                return param.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException
                     | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initStyle(style);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/ico/calculator.png")));

        T controller = fxmlLoader.getController();
        ((IController) controller).init(stage);

        stage.show();

        return controller;
    }
}
