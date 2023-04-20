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

/**
 * отвечает за загрузку и создание экземпляра контроллера для FXML-файлов,
 * которые используются для создания графического интерфейса приложения на JavaFX.
 */
public class FXMLManager {
    /**
     * загружает FXML-файл из указанного пути, создает новый экземпляр контроллера с
     * помощью метода setControllerFactory класса FXMLLoader, и инициализирует его с
     * помощью метода init класса IController, передавая в качестве параметра объект
     * Stage, который представляет текущее окно приложения. Затем метод создает
     * новую сцену с загруженным FXML-контентом, устанавливает ее в качестве сцены
     * текущего окна, настраивает свойства окна (заголовок, иконка, стиль),
     * и отображает его на экране с помощью метода show()
     */
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
