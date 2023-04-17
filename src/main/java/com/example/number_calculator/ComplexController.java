package com.example.number_calculator;

import com.example.number_calculator.editor_comprehensive.EditorComplex;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ComplexController {
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
    private Button button_A;

    @FXML
    private Button button_Addition;

    @FXML
    private Button button_B;

    @FXML
    private Button button_C;

    @FXML
    private Button button_D;

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
    private Button button_SignChange;

    @FXML
    private Button button_Subtraction;

    @FXML
    private TextField textInput;

    @FXML
    private TextField textResult;

    @FXML
    private AnchorPane windowMain;
    private EditorComplex complex=new EditorComplex();

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
    void actionOperator(MouseEvent event) {
        complex.actionOperator(event);
        printf();
    }

    @FXML
    void onBaskSpace(MouseEvent event) {

    }

    @FXML
    void onClean(MouseEvent event) {

    }

    @FXML
    void onCleanEntry(MouseEvent event) {

    }

    @FXML
    void onMemory(MouseEvent event) {

    }

    @FXML
    void onNumberClicked(MouseEvent event) {
        complex.entryNumber(event);
        printf();
    }

    @FXML
    void onOperatorClicking(MouseEvent event) {
        complex.entryOperator(event);
        printf();
    }

    @FXML
    void onResultClicked(MouseEvent event) {
        complex.onResultClicked();
        printf();
    }

    @FXML
    void onSymbolClicked(MouseEvent event) {

    }

    void printf(){
        textInput.setText(EditorComplex.getInputLine().toString());
        textResult.setText(EditorComplex.getResultLine().toString());
    }

    public void onMdlClicked(MouseEvent event) {

    }

    public void onCndClicked(MouseEvent event) {

    }

    public void onCnrClicked(MouseEvent event) {

    }

    public void onBrackets(MouseEvent event){
        complex.entrySymbol(event);
        printf();
    }

    public void onRootClicked(MouseEvent event) {


    }
}
