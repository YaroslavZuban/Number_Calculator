package com.example.number_calculator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Данный класс представляет контроллер для окна "История" в калькуляторе.
 * Он содержит методы для инициализации этого окна, обработки событий
 * нажатия кнопок, а также для отображения записей из истории
 * вычислений в таблице на этом окне.
 */
public class HistoryController implements Initializable {
    @FXML
    private ImageView buttonClose;

    @FXML
    private ImageView buttonCollapse;

    @FXML
    private ImageView buttonHistory;

    @FXML
    private ImageView buttonInfo;

    @FXML
    private GridPane grid;

    @FXML
    private AnchorPane windowMain;

    private double x, y;

    public void init(Stage stage) {

        //с помощью этих методов выполняется перетаскивание окна на рабочем столе
        windowMain.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        windowMain.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });

        buttonCollapse.setOnMouseClicked(mouseEvent -> stage.setIconified(true));//сворачивание окна
        buttonClose.setOnMouseClicked(mouseEvent -> stage.close());//закрытие приложения

        buttonInfo.setOnMouseClicked(mouseEvent -> {
            Info.information(Reference.infoHistory, windowMain);
        });

        buttonHistory.setOnMouseClicked(mouseEvent -> {
            Stage ss = (Stage) windowMain.getScene().getWindow();//береться параметры стратого она и закрывается
            ss.close();//закрытия окна

            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("MainWindowInterface.fxml"));//считывание дизайн самого интерфейса

            Stage s = new Stage();
            Scene scene = null;//запуск дизайн

            try {
                scene = new Scene(fxmlLoader.load());
                //   stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("bit.png")));//установление иконки
                scene.setFill(Color.TRANSPARENT);
                s.setTitle("Калькулятор");
                s.initStyle(StageStyle.TRANSPARENT);
                s.setResizable(false);
                s.setScene(scene);
                s.setScene(scene);//установка Scene для Stage
                s.getIcons().add(new Image(getClass().getResourceAsStream("/ico/calculator.png")));
                ((ConverterController) fxmlLoader.getController()).init(s);
                s.show();//Попытки показать это окно, установив для видимости значение true
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int column = 0;
        int row = 1;

        try {
            for (int i = 0; i < History.data.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();

                fxmlLoader.setLocation(getClass().getResource("Doing.fxml"));//подгружаем окно doing.fxml
                AnchorPane anchorPane = fxmlLoader.load();//позволяет вам определять точки привязки fxml формат

                Doing doing = fxmlLoader.getController();//загружаем doing контроллер
                String computing = History.data.get(i);//устанавливаем значение итерации

                doing.setResult(computing);//вносим данные в fxml формат

                if (column == 1) {//данное условие позволяет в таблице создавать только одну колонку
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);//вставляем в таблицу значения

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_COMPUTED_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_COMPUTED_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));//устанавливает отступ между ячейками
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
