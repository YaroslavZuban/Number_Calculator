package com.example.number_calculator.editor_number_systems;

import com.example.number_calculator.Editor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class EditorConverter {
    public static TPNumber number_one = null;
    public static TPNumber number_two = null;
    public static TPNumber number_result = null;
    public static TPNumber number_temp = null;
    public static StringBuilder lineInput = new StringBuilder("0");
    public static StringBuilder lineResult = new StringBuilder();
    public static int system = 10;
    public static String operation = "";
    private InputTPNumber input = new InputTPNumber();

    public void entrySymbol(MouseEvent event) {
        String temp = ((Button) event.getSource()).getId().replace("button_", "");
        inputNumberSymbol(temp);
    }


    public void entryNumber(MouseEvent event) {
        String temp = ((Button) event.getSource()).getId().replace("button_", "");
        inputNumberSymbol(temp);
    }


    public void entryOperator(MouseEvent event) {
        String temp = ((Button) event.getSource()).getId().replace("button_", "");

        input.inputOperator(temp, lineResult);

        getInput();
        dataInput();
        dataResult();
    }

    public void onBackSpace(MouseEvent event) {
        if (number_two != null) {
            deleteSymbol(number_two);

            if (number_two.getNumber().equals("")) {
                number_two = null;
            }
        } else if (!operation.equals("")) {
            operation = "";
        } else if(number_one != null){
            deleteSymbol(number_one);

            if (number_one.getNumber().equals("")) {
                number_one = null;
            }
        }

        setInput();

        dataInput();
        dataResult();
    }

    public void onClean() {
        lineInput = new StringBuilder();
        lineResult = new StringBuilder();

        number_one = null;
        number_two = null;
        number_result = null;

        system = 10;
        operation = "";

        setInput();
    }

    public void onCleanEntry(MouseEvent event) {
        if (number_two != null && !number_two.getNumber().equals("")) {
            number_two = new TPNumber("", system);
        } else if (!operation.equals("")) {
            operation = "";
            number_two = null;
        } else if (number_one != null && !number_one.getNumber().equals("")) {
            number_one = null;
        }

        setInput();

        dataInput();
        dataResult();
    }

    private void deleteSymbol(TPNumber number) {
        if (number.getNumber().length() != 0) {
            StringBuilder temp = new StringBuilder(number.getNumber());
            temp.deleteCharAt(temp.length() - 1);
            number.setNumber(temp.toString());
        }
    }

    private void inputNumberSymbol(String temp) {
        setInput();

        if (number_one != null && number_two != null && number_result != null) {
            number_two = null;
            number_one = null;
            number_result = null;

            operation = "";

            lineInput = new StringBuilder();
            lineResult = new StringBuilder();

            setInput();
        }

        input.inputNumberSymbol(temp);

        getInput();
        dataInput();
        dataResult();
    }

    public void dataInput() {
        StringBuilder result = new StringBuilder();

        if (number_one != null) {
            result.append(number_one.getNumber());
        }

        if (operation != null && !operation.equals("")) {
            result.append(operation);
        }

        if (number_two != null) {
            if (number_two.getNumber().contains("-")) {
                result.append("(" + number_two.getNumber() + ")");
            } else {
                result.append(number_two.getNumber());
            }
        }

        lineInput = new StringBuilder(result);
        result.delete(0, result.length());

        if (number_result != null) {
            result.append(number_result.getNumber());
        }

        lineResult = new StringBuilder(result);
    }

    public void dataResult() {
        StringBuilder result = new StringBuilder();

        if (number_result != null) {
            result.append(number_result.getNumber());
        }

        lineResult = new StringBuilder(result);
    }

    public void setInput() {
        InputTPNumber.number_one = number_one;
        InputTPNumber.number_two = number_two;
        InputTPNumber.number_result = number_result;
        InputTPNumber.system = system;
        InputTPNumber.operation = operation;
    }

    private void getInput() {
        number_one = InputTPNumber.number_one;
        number_two = InputTPNumber.number_two;
        number_result = InputTPNumber.number_result;
        number_temp = InputTPNumber.number_temp;
        system = InputTPNumber.system;
        operation = InputTPNumber.operation;
    }
}