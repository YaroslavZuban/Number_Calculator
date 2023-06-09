package com.example.number_calculator;

import com.example.number_calculator.editor_number_systems.EditorConverter;
import com.example.number_calculator.editor_number_systems.InputTPNumber;
import com.example.number_calculator.editor_number_systems.ProcessorConverter;
import com.example.number_calculator.editor_number_systems.WorkingMemory;
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

/**
 * контроллер для графического интерфейса калькулятора, реализованного с помощью библиотеки JavaFX.
 * Он содержит определения элементов интерфейса (кнопки, поля ввода, слайдер, спиннер, изображения)
 * и методы для их обработки, реализации логики и взаимодействия между ними. Кроме того, контроллер
 * содержит ссылки на другие классы, такие как EditorConverter, ProcessorConverter и WorkingMemory,
 * которые отвечают за обработку ввода, вычисления и хранение результатов. В методе init()
 * устанавливается начальное состояние элементов интерфейса, добавляются обработчики событий
 * для их работы, а также создается окно и запускается интерфейс.
 */
public class ConverterController implements IController {
    @FXML
    private Button buttonBackSpace;
    @FXML
    private AnchorPane windowMain;
    @FXML
    private ImageView buttonClose;

    @FXML
    private ImageView buttonCollapse;

    @FXML
    private ImageView buttonHistory;

    @FXML
    private ImageView buttonInfo;

    @FXML
    private Button buttonResult;

    @FXML
    private Button button_0;

    @FXML
    private Button button_Result;
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
    private Button button_E;

    @FXML
    private Button button_F;

    @FXML
    private Button button_Multiplication;

    @FXML
    private Button button_OneDivisionX;

    @FXML
    private Button button_Point;

    @FXML
    private Button button_Pow_Two;

    @FXML
    private Button button_SignСhange;

    @FXML
    private Button button_Subtraction;

    @FXML
    private Button button_MC;

    @FXML
    private Button button_MPlus;

    @FXML
    private Button button_MR;

    @FXML
    private Button button_MS;
    @FXML
    private Slider sliderInput;

    @FXML
    private Spinner<Integer> spinnerInput;

    private SpinnerValueFactory<Integer> spinnerValueFactoryInput;

    @FXML
    private TextField textInput;
    @FXML
    private TextField textResult;
    private int systemInput;
    EditorConverter editorConverter = new EditorConverter();
    ProcessorConverter processorConverter = new ProcessorConverter();
    WorkingMemory memory = new WorkingMemory();

    private Stage stage;
    private double x, y;

    public void init(Stage stage) {
        this.stage = stage;

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
            Info.information(Reference.infoConverter, windowMain);
        });

        buttonClose.setOnMouseClicked(event -> stage.close());
        buttonCollapse.setOnMouseClicked(event -> stage.setIconified(true));

        spinnerValueFactoryInput =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 16);

        spinnerValueFactoryInput.setValue(10);
        spinnerInput.setValueFactory(spinnerValueFactoryInput);

        sliderInput.setValue((10));


        EditorConverter.system = 10;
        getEditor();
    }

    @FXML
    void actionOperator(MouseEvent event) {
        processorConverter.actionOperator(event);

        getProcessor();
        printEditor();
    }

    @FXML
    void onNumberSystemSlider(MouseEvent event) {
        systemInput = (int) sliderInput.getValue();
        EditorConverter.system = systemInput;
        numberSystemChanges(sliderInput, spinnerValueFactoryInput, spinnerInput, systemInput);
        getEditor();
    }

    @FXML
    void onNumberSystemSpinner(MouseEvent event) {
        systemInput = spinnerInput.getValue();
        EditorConverter.system = systemInput;
        numberSystemChanges(sliderInput, spinnerValueFactoryInput, spinnerInput, systemInput);
        getEditor();
    }

    private void numberSystemChanges(Slider slider,
                                     SpinnerValueFactory<Integer> factory,
                                     Spinner<Integer> spinner, int value) {
        factory.setValue(value);
        spinner.setValueFactory(factory);
        slider.setValue(value);
    }

    @FXML
    void onResultClicked(MouseEvent event) {
        processorConverter.onResultClicked(null);

        getProcessor();
        printEditor();
    }

    @FXML
    void onNumberClicked(MouseEvent event) {
        editorConverter.entryNumber(event);

        getEditor();
        printEditor();
    }

    @FXML
    void onSymbolClicked(MouseEvent event) {
        editorConverter.entrySymbol(event);

        getEditor();
        printEditor();

    }

    @FXML
    void onOperatorClicking(MouseEvent event) {
        editorConverter.entryOperator(event);

        getEditor();
        printEditor();
    }

    @FXML
    public void onClean(MouseEvent event) {
        editorConverter.onClean(event);

        numberSystemChanges(sliderInput, spinnerValueFactoryInput, spinnerInput, EditorConverter.system);
        getEditor();
        printEditor();
    }

    @FXML
    public void onMemory(MouseEvent event) {
        memory.memory(event);

        getMemory();
        printEditor();
    }

    @FXML
    public void onCleanEntry(MouseEvent event) {
        editorConverter.onCleanEntry(event);
        numberSystemChanges(sliderInput, spinnerValueFactoryInput, spinnerInput, EditorConverter.system);

        getEditor();
        printEditor();
    }

    @FXML
    public void onBaskSpace(MouseEvent event) {
        editorConverter.onBackSpace(event);
        numberSystemChanges(sliderInput, spinnerValueFactoryInput, spinnerInput, EditorConverter.system);

        getEditor();
        printEditor();
    }

    public void onWindowFraction(MouseEvent event) {
        Stage oldStage = (Stage) windowMain.getScene().getWindow();
        oldStage.close();

        try {
            FractionController controller = new FXMLManager().loadFXML("MainWindowsInterfaceFraction.fxml", "Калькулятор - дроби", StageStyle.TRANSPARENT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onWindowComplex(MouseEvent event) {
        Stage oldStage = (Stage) windowMain.getScene().getWindow();
        oldStage.close();

        try {
            ComplexController controller = new FXMLManager().loadFXML("MainWindowInterfaceComplex.fxml", "Калькулятор - комплексный", StageStyle.TRANSPARENT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void printEditor() {
        if (EditorConverter.number_two != null && EditorConverter.number_result!=null) {
            editorConverter.dataResult();
        } else {
            editorConverter.dataInput();
        }

        textInput.setText(EditorConverter.lineInput.toString());
        textResult.setText(EditorConverter.lineResult.toString());
    }

    private void getMemory() {
        ProcessorConverter.number_one = WorkingMemory.number_one;
        ProcessorConverter.number_two = WorkingMemory.number_two;
        ProcessorConverter.number_result = WorkingMemory.number_result;
        ProcessorConverter.number_temp = WorkingMemory.number_temp;
        ProcessorConverter.operation = WorkingMemory.operation;
        ProcessorConverter.system = WorkingMemory.system;

        EditorConverter.number_one = WorkingMemory.number_one;
        EditorConverter.number_two = WorkingMemory.number_two;
        EditorConverter.number_result = WorkingMemory.number_result;
        EditorConverter.number_temp = WorkingMemory.number_temp;
        EditorConverter.operation = WorkingMemory.operation;
        EditorConverter.system = WorkingMemory.system;

        editorConverter.setInput();
    }

    private void getEditor() {
        ProcessorConverter.number_one = EditorConverter.number_one;
        ProcessorConverter.number_two = EditorConverter.number_two;
        ProcessorConverter.number_result = EditorConverter.number_result;
        ProcessorConverter.number_temp = EditorConverter.number_temp;
        ProcessorConverter.operation = EditorConverter.operation;
        ProcessorConverter.system = EditorConverter.system;

        WorkingMemory.number_one = EditorConverter.number_one;
        WorkingMemory.number_two = EditorConverter.number_two;
        WorkingMemory.number_result = EditorConverter.number_result;
        WorkingMemory.number_temp = EditorConverter.number_temp;
        WorkingMemory.operation = EditorConverter.operation;
        WorkingMemory.system = EditorConverter.system;
    }

    private void getProcessor() {
        WorkingMemory.number_one = ProcessorConverter.number_one;
        WorkingMemory.number_two = ProcessorConverter.number_two;
        WorkingMemory.number_result = ProcessorConverter.number_result;
        WorkingMemory.number_temp = ProcessorConverter.number_temp;
        WorkingMemory.operation = ProcessorConverter.operation;
        WorkingMemory.system = ProcessorConverter.system;

        EditorConverter.number_one = ProcessorConverter.number_one;
        EditorConverter.number_two = ProcessorConverter.number_two;
        EditorConverter.number_result = ProcessorConverter.number_result;
        EditorConverter.number_temp = ProcessorConverter.number_temp;
        EditorConverter.operation = ProcessorConverter.operation;
        EditorConverter.system = ProcessorConverter.system;

        editorConverter.setInput();
    }
}