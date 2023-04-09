package com.example.number_calculator.editor_number_systems;

import com.example.number_calculator.CalculatorMemory;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class EditorConverter {
    public static TPNumber number_one = null;
    public static TPNumber number_two = null;
    public static TPNumber number_result = null;
    private static TPNumber number_temp = null;
    public static StringBuilder lineInput = new StringBuilder("0");
    public static StringBuilder lineResult = new StringBuilder();
    public static int system = 10;
    public static String operation = "";
    private static String operationTemp = "";
    private Calculator calculator = new Calculator();
    private Input input = new Input(number_one, number_two, number_result, operation, system);
    private ProcessorConverter result = new ProcessorConverter(number_one, number_two, number_result, number_temp, operation, system);
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
        setResult();

        dataInput();
    }

    public void onResultClicked() {
        getResult();
        result.onResultClicked(lineResult);

        number_result = ProcessorConverter.getNumber_result();
        dataInput();

        setResult();
        setInput();
    }

    public void actionOperator(MouseEvent event) {
        String temp = ((Button) event.getSource()).getId().replace("button_", "");
        result.actionOperator(temp);

        getResult();
        setInput();

        dataInput();
    }

    public void onBaskSpace(MouseEvent event) {
        if (number_two != null) {
            deleteSymbol(number_two);
        } else if (!operation.equals("")) {
            operation = "";
        } else {
            deleteSymbol(number_one);
        }

        setResult();
        setInput();

        dataInput();
    }

    public void onClean(MouseEvent event) {
        lineInput = new StringBuilder("0");
        lineResult = new StringBuilder();

        number_one = null;
        number_two = null;
        number_result = null;

        system = 10;
        operation = "";

        setResult();
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

        setResult();
        setInput();

        dataInput();
    }

    public void memory(MouseEvent event) {
        String temp = ((Button) event.getSource()).getId().replace("button_", "");

        result.memory(temp);

        getResult();
        setInput();

        if(temp.contains("MR")){
            lineInput=new StringBuilder();
            lineResult=new StringBuilder();

            dataInput();
        }

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

    private void inputNumberSymbol(String temp) {
        if (number_one != null && number_two != null && number_result != null) {
            number_two = null;
            number_one = null;
            number_result = null;

            operation = "";

            lineInput = new StringBuilder();
            lineResult = new StringBuilder();

            setResult();
            setInput();
        }

        input.inputNumberSymbol(temp);

        getInput();
        setResult();

        dataInput();
    }

    private void log() {
        if (number_one != null) {
            System.out.println("number_one: " + number_one.toString());
        } else {
            System.out.println("number_one: " + null);
        }

        if (number_two != null) {
            System.out.println("number_two: " + number_two.toString());
        } else {
            System.out.println("number_two: " + null);
        }

        if (number_result != null) {
            System.out.println("number_result: " + number_result.toString());
        } else {
            System.out.println("number_result: " + null);
        }

        if (lineResult != null) {
            System.out.println("lineResult: " + lineResult.toString());
        } else {
            System.out.println("lineResult: " + null);
        }

        if (lineInput != null) {
            System.out.println("lineInput: " + lineInput.toString());
        } else {
            System.out.println("lineInput: " + null);
        }
    }

    private void dataInput() {
        StringBuilder result = new StringBuilder();

        if (number_one != null) {
            result.append(number_one.getNumber());
        }

        if (!operation.equals("")) {
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

    private void setResult() {
        ProcessorConverter.setNumber_one(number_one);
        ProcessorConverter.setNumber_two(number_two);
        ProcessorConverter.setNumber_result(number_result);
        ProcessorConverter.setNumber_temp(number_temp);
        ProcessorConverter.setOperation(operation);
        ProcessorConverter.setSystem(system);
    }

    private void getResult() {
        number_one = ProcessorConverter.getNumber_one();
        number_two = ProcessorConverter.getNumber_two();
        number_result = ProcessorConverter.getNumber_result();
        number_temp = ProcessorConverter.getNumber_temp();
        system = ProcessorConverter.getSystem();
        operation = ProcessorConverter.getOperation();
    }

    private void setInput() {
        Input.setNumber_one(number_one);
        Input.setNumber_two(number_two);
        Input.setNumber_result(number_result);
        Input.setNumber_temp(number_temp);
        Input.setOperation(operation);
        Input.setSystem(system);
    }

    private void getInput() {
        number_one = Input.getNumber_one();
        number_two = Input.getNumber_two();
        number_result = Input.getNumber_result();
        number_temp = Input.getNumber_temp();
        system = Input.getSystem();
        operation = Input.getOperation();
    }

}