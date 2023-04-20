package com.example.number_calculator;

import com.example.number_calculator.editor_number_complex.*;
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
import java.text.ParseException;

public class ComplexController implements IController {
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
    private TextField textExponent;

    @FXML
    private TextField textCalculationResult;

    @FXML
    private AnchorPane windowMain;
    private EditorComplex complex = new EditorComplex();
    private ProcessorComplex processorComplex = new ProcessorComplex();
    private WorkingMemory memory = new WorkingMemory();

    private Stage stage;
    private double x, y;

    public void init(Stage stage) {
        this.stage=stage;
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
            Info.information(Reference.infoComplex, windowMain);
        });

        buttonClose.setOnMouseClicked(event -> stage.close());
        buttonCollapse.setOnMouseClicked(event -> stage.setIconified(true));

    }
    @FXML
    void actionOperator(MouseEvent event) {
        try {
            processorComplex.actionOperator(event);
        } catch (IncorrectTypeException | UnrecognizableElementException | IncorrectElementException e) {
            throw new RuntimeException(e);
        }

        getProcessor();
        printEditor();
    }
    @FXML
    void onBaskSpace(MouseEvent event) {
        complex.onBackSpace(event);

        getEditor();
        printEditor();
    }
    @FXML
    void onClean(MouseEvent event) {
        complex.onClean(event);

        getEditor();
        printEditor();
    }
    @FXML
    void onCleanEntry(MouseEvent event) {
        complex.onCleanEntry(event);

        getEditor();
        printEditor();
    }
    @FXML
    void onMemory(MouseEvent event) {
        memory.workingMemory(event);

        getMemory();
        printEditor();
    }
    @FXML
    void onNumberClicked(MouseEvent event) {
        complex.entryNumber(event);

        getEditor();
        printEditor();
    }
    @FXML
    void onOperatorClicking(MouseEvent event) {
        complex.entryOperator(event);

        getEditor();
        printEditor();
    }
    @FXML
    void onResultClicked(MouseEvent event) {
        try {
            processorComplex.result();
        } catch (IncorrectTypeException | UnrecognizableElementException | IncorrectElementException |
                 ParseException e) {
            throw new RuntimeException(e);
        }

        getProcessor();
        printEditor();
    }
    public void onMdlClicked(MouseEvent event) {
        try {
            processorComplex.mdl();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        getProcessor();
        printEditor();
    }

    public void onCndClicked(MouseEvent event) {
        try {
            processorComplex.cnd();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        getProcessor();
        printEditor();
    }

    public void onCnrClicked(MouseEvent event) {
        try {
            processorComplex.cnr();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        getProcessor();
        printEditor();
    }

    public void onBrackets(MouseEvent event) {
        complex.entrySymbol(event);

        getEditor();
        printEditor();
    }

    public void onRootClicked(MouseEvent event) {
        if (!textExponent.getText().equals("")) {
            try {
                processorComplex.root(Integer.parseInt(textExponent.getText()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            getProcessor();
            printEditor();
        }
    }

    public void onWindowConverter(MouseEvent event) {
        Stage ss = (Stage) windowMain.getScene().getWindow();//береться параметры стратого она и закрывается
        ss.close();//закрытия окна

        try {
            ConverterController controller = new FXMLManager().loadFXML("MainWindowInterface.fxml", "Калькулятор - конвертер", StageStyle.TRANSPARENT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void onWindowFraction(MouseEvent event) {
        Stage oldStage = (Stage) windowMain.getScene().getWindow();
        oldStage.close();

        try {
            FractionController controller =  new FXMLManager().loadFXML("MainWindowsInterfaceFraction.fxml", "Калькулятор - дроби", StageStyle.TRANSPARENT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void printEditor() {
        textInput.setText(EditorComplex.inputLine.toString());
        textResult.setText(EditorComplex.resultLine.toString());
        textCalculationResult.setText(EditorComplex.functionResult.toString());
    }

    private void getMemory() {
        EditorComplex.inputLine = WorkingMemory.inputLine;
        EditorComplex.resultLine = WorkingMemory.resultLine;

        ProcessorComplex.inputLine = WorkingMemory.inputLine;
        ProcessorComplex.resultLine = WorkingMemory.resultLine;
    }

    private void getEditor() {
        WorkingMemory.inputLine = EditorComplex.inputLine;
        WorkingMemory.resultLine = EditorComplex.resultLine;

        ProcessorComplex.inputLine = EditorComplex.inputLine;
        ProcessorComplex.resultLine = EditorComplex.resultLine;
        ProcessorComplex.tempLine = EditorComplex.tempLine;
        ProcessorComplex.operation = EditorComplex.operation;
        ProcessorComplex.functionResult = EditorComplex.functionResult;
    }

    private void getProcessor() {
        WorkingMemory.inputLine = ProcessorComplex.inputLine;
        WorkingMemory.resultLine = ProcessorComplex.resultLine;

        EditorComplex.inputLine = ProcessorComplex.inputLine;
        EditorComplex.resultLine = ProcessorComplex.resultLine;
        EditorComplex.tempLine = ProcessorComplex.tempLine;
        EditorComplex.operation = ProcessorComplex.operation;
        EditorComplex.functionResult = ProcessorComplex.functionResult;
    }
}
