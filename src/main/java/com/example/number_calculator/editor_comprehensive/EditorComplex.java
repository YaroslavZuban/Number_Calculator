package com.example.number_calculator.editor_comprehensive;

import com.example.number_calculator.Editor;
import com.example.number_calculator.editor_number_systems.InputTPNumber;
import com.example.number_calculator.editor_number_systems.ProcessorConverter;
import com.example.number_calculator.editor_number_systems.TPNumber;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.text.ParseException;

public class EditorComplex implements Editor {
    public static StringBuilder getInputLine() {
        return inputLine;
    }

    public static StringBuilder getResultLine() {
        return resultLine;
    }

    public static StringBuilder inputLine = new StringBuilder();
    public static StringBuilder tempLine = new StringBuilder();
    public static StringBuilder resultLine = new StringBuilder();

    private static ProcessorComplex processor = new ProcessorComplex(inputLine, tempLine, resultLine);

    @Override
    public void entrySymbol(MouseEvent event) {
        String temp = ((Button) event.getSource()).getId().replace("button_", "");
        processor.inputSymbol(temp);

        getInput();
    }

    @Override
    public void entryNumber(MouseEvent event) {
        String temp = ((Button) event.getSource()).getId().replace("button_", "");
        processor.input(temp);

        getInput();
    }

    @Override
    public void entryOperator(MouseEvent event) {
        String temp = ((Button) event.getSource()).getId().replace("button_", "");

        try {
            processor.inputOperation(temp);
        } catch (IncorrectTypeException | UnrecognizableElementException | IncorrectElementException e) {
            throw new RuntimeException(e);
        }


        getInput();
    }

    @Override
    public void onResultClicked() {
        try {
            processor.result();
        } catch (IncorrectTypeException | UnrecognizableElementException | IncorrectElementException |
                 ParseException e) {
            throw new RuntimeException(e);
        }

        getInput();
    }

    @Override
    public void actionOperator(MouseEvent event) {
        String temp = ((Button) event.getSource()).getId().replace("button_", "");

        try {
            processor.actionOperator(temp);
        } catch (IncorrectTypeException | UnrecognizableElementException | IncorrectElementException e) {
            throw new RuntimeException(e);
        }

        getInput();
    }

    @Override
    public void onBackSpace(MouseEvent event) {
        inputLine.deleteCharAt(inputLine.length() - 1);
        setInput();
    }

    @Override
    public void onClean(MouseEvent event) {
        inputLine = new StringBuilder();
        resultLine = new StringBuilder();
        tempLine = new StringBuilder();

        processor = new ProcessorComplex(inputLine, resultLine, tempLine);

        setInput();
    }

    @Override
    public void onCleanEntry(MouseEvent event) {
        inputLine = new StringBuilder(removeLastPart(inputLine.toString()));

        setInput();
    }

    @Override
    public void memory(MouseEvent event) {
        String temp = ((Button) event.getSource()).getId().replace("button_", "");

        processor.workingMemory(temp);

        getInput();
    }

    private void deleteSymbol(TPNumber number) {
        if (number.getNumber().length() != 0) {
            StringBuilder temp = new StringBuilder(number.getNumber());
            temp.deleteCharAt(temp.length() - 1);
            number.setNumber(temp.toString());
        }

        if (number.getNumber().equals("")) {
            number = null;
        }
    }

    private void getInput() {
        inputLine = ProcessorComplex.getInputLine();
        resultLine = ProcessorComplex.getResultLine();
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

    private void setInput() {
        ProcessorComplex.setInputLine(inputLine);
        ProcessorComplex.setResultLine(resultLine);
        ProcessorComplex.setTempLine(tempLine);
    }
}
