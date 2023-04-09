package com.example.number_calculator;

import com.example.number_calculator.editor_number_systems.EditorConverter;
import com.example.number_calculator.editor_simple_fraction.EditorFraction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class FractionController {

    @FXML
    private Button buttonBackSpace;

    @FXML
    private ImageView buttonClose;

    @FXML
    private ImageView buttonCollapse;

    @FXML
    private ImageView buttonHistory;

    @FXML
    private ImageView buttonInfo;

    @FXML
    private Button button_0;

    @FXML
    private Button button_1;

    @FXML
    private Button button_2;

    @FXML
    private Button button_3;

    @FXML
    private Button button_4;

    @FXML
    private Button button_5;

    @FXML
    private Button button_6;

    @FXML
    private Button button_7;

    @FXML
    private Button button_8;

    @FXML
    private Button button_9;

    @FXML
    private Button button_Addition;

    @FXML
    private Button button_Division;

    @FXML
    private Button button_MC;

    @FXML
    private Button button_MPlus;

    @FXML
    private Button button_MR;

    @FXML
    private Button button_MS;

    @FXML
    private Button button_Multiplication;

    @FXML
    private Button button_OneDivisionX;

    @FXML
    private Button button_Point;

    @FXML
    private Button button_Pow_Two;

    @FXML
    private Button button_Result;

    @FXML
    private Button button_SignСhange;

    @FXML
    private Button button_Subtraction;

    @FXML
    private TextField textInput;

    @FXML
    private TextField textResult;

    @FXML
    private AnchorPane windowMain;

    EditorFraction editorFraction=new EditorFraction();

    private double x, y;

    public void init(Stage stage) {
        windowMain.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        windowMain.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });

        buttonHistory.setOnMouseClicked(mouseEvent -> {
            Stage ss = (Stage) windowMain.getScene().getWindow();//береться параметры стратого она и закрывается
            ss.close();//закрытия окна

            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("HistoryInterface.fxml"));//считывание дизайн самого интерфейса

            Stage s = new Stage();
            Scene scene = null;//запуск дизайн

            try {
                scene = new Scene(fxmlLoader.load());
                scene.setFill(Color.TRANSPARENT);
                s.setTitle("Калькулятор");
                s.initStyle(StageStyle.TRANSPARENT);
                s.setResizable(false);
                s.setScene(scene);
                s.setScene(scene);//установка Scene для Stage
                s.getIcons().add(new Image(getClass().getResourceAsStream("/ico/calculator.png")));
                ((HistoryController) fxmlLoader.getController()).init(s);
                s.show();//Попытки показать это окно, установив для видимости значение true
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        buttonInfo.setOnMouseClicked(mouseEvent -> {
            Info.information(Reference.info, windowMain);
        });

        buttonClose.setOnMouseClicked(event -> stage.close());
        buttonCollapse.setOnMouseClicked(event -> stage.setIconified(true));

    }

    @FXML
    void actionOperator(MouseEvent event){
        editorFraction.actionOperator(event);
        //Editor.actionOperator(event);
        //textInput.setText(Editor.value.toString());
        printf();
    }


    @FXML
    void onResultClicked(MouseEvent event){
        editorFraction.onResultClicked();
        printf();
    }

    @FXML
    void onNumberClicked(MouseEvent event) {
        editorFraction.waterNumber(event);
        printf();
    }

    @FXML
    void onOperatorClicking(MouseEvent event) {
        editorFraction.waterOperator(event);
        printf();
    }

    @FXML
    public void onClean(MouseEvent event) {
      //  editorFraction.onClean(event);
        printf();
    }

    @FXML
    public void onMemory(MouseEvent event){
       // editorFraction.memory(event);
        printf();
    }

    public void onDivisionClicked(MouseEvent event){
        editorFraction.onDivisionClicked();
        printf();
    }

    @FXML
    public void onCleanEntry(MouseEvent event) {
        //editorFraction.onCleanEntry(event);
        printf();
    }

    @FXML
    public void onBaskSpace(MouseEvent event) {
       // editorFraction.onBaskSpace(event);
        printf();
    }

    private void printf(){
        textInput.setText(EditorFraction.lineInput.toString());
        textResult.setText(EditorFraction.lineResult.toString());
    }

}