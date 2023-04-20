package com.example.number_calculator;

import javafx.stage.Stage;

/**
 * реализует интерфейс IController и имеет метод init(), который принимает объект типа Stage.
 * После вызова метода init(), этот объект сохраняется в приватное поле stage.
 * В целом, класс может использоваться как базовый класс для контроллеров
 * JavaFX-приложений, которым нужен доступ к объекту Stage, например, для открытия новых окон.
 */
public class MyController implements IController{
    private Stage stage;

    @Override
    public void init(Stage stage) {
        this.stage=stage;
    }
}
