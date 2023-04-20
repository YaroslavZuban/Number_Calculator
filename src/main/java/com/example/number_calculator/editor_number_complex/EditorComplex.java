package com.example.number_calculator.editor_number_complex;

import com.example.number_calculator.Editor;
import com.example.number_calculator.TextEditingListener;
import com.example.number_calculator.editor_number_systems.TPNumber;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.text.ParseException;

/**
 * Данный класс реализует два интерфейса: TextEditingListener и Editor.
 * Он отвечает за обработку ввода и редактирование текста в пользовательском интерфейсе.
 */
public class EditorComplex implements TextEditingListener,Editor {
    public static StringBuilder inputLine = new StringBuilder();
    public static StringBuilder tempLine = new StringBuilder();
    public static StringBuilder resultLine = new StringBuilder();

    public static StringBuilder operation = new StringBuilder();
    public static StringBuilder functionResult=new StringBuilder();

    public static InputComplex input = new InputComplex();

    /**
     * метод, вызываемый при нажатии на символьную кнопку в пользовательском интерфейсе.
     * Он добавляет соответствующий символ в строку ввода inputLine.
     * @param event
     */
    @Override
    public void entrySymbol(MouseEvent event) {
        setInput();
        String temp = ((Button) event.getSource()).getId().replace("button_", "");
        input.inputSymbol(temp);

        getInput();
    }

    /**
     *  метод, вызываемый при нажатии на цифровую кнопку в пользовательском интерфейсе.
     *  Он добавляет соответствующую цифру в строку ввода inputLine.
     * @param event
     */
    @Override
    public void entryNumber(MouseEvent event) {
        setInput();
        String temp = ((Button) event.getSource()).getId().replace("button_", "");
        input.input(temp);

        getInput();
    }

    /**
     * метод, вызываемый при нажатии на кнопку операции в пользовательском интерфейсе.
     * Он добавляет соответствующий оператор в строку operation.
     * @param event
     */
    @Override
    public void entryOperator(MouseEvent event) {
        setInput();
        String temp = ((Button) event.getSource()).getId().replace("button_", "");

        try {
            input.inputOperation(temp);
        } catch (IncorrectTypeException | UnrecognizableElementException | IncorrectElementException e) {
            throw new RuntimeException(e);
        }


        getInput();
    }

    /**
     * метод, вызываемый при нажатии на кнопку "Backspace" в пользовательском интерфейсе.
     * Он удаляет последний символ из строки inputLine.
     * @param event
     */
    @Override
    public void onBackSpace(MouseEvent event) {
        inputLine.deleteCharAt(inputLine.length() - 1);
        setInput();
    }

    /**
     * етод, вызываемый при нажатии на кнопку "C" в пользовательском интерфейсе. Он очищает строки все данные.
     * @param event
     */
    @Override
    public void onClean(MouseEvent event) {
        inputLine = new StringBuilder();
        resultLine = new StringBuilder();
        tempLine = new StringBuilder();

        setInput();
    }

    /**
     * метод, вызываемый при нажатии на кнопку "CE" в пользовательском интерфейсе.
     * Он удаляет последний введенный оператор из строки inputLine.
     * @param event
     */
    @Override
    public void onCleanEntry(MouseEvent event) {
        inputLine = new StringBuilder(removeLastPart(inputLine.toString()));

        setInput();
    }
    private String removeLastPart(String str) {
        char ch = str.charAt(str.length() - 1);

        if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
            return str.substring(0, str.length() - 1);
        } else {
            for (int i = str.length() - 1; i >= 0; i--) {
                ch = str.charAt(i);

                if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                    return str.substring(0, i + 1);
                }
            }
        }

        return "";
    }

    /**
     * метод, который получает текущее состояние ввода и операции из InputComplex.
     */
    private void getInput() {
        inputLine = InputComplex.inputLine;
        resultLine = InputComplex.resultLine;
        operation = InputComplex.operation;
        tempLine = InputComplex.tempLine;
    }

    /**
     * метод, который устанавливает текущее состояние ввода и операции в InputComplex.
     */
    private void setInput() {
        InputComplex.inputLine = inputLine;
        InputComplex.resultLine = resultLine;
        InputComplex.operation = operation;
        InputComplex.tempLine = tempLine;
    }
}
