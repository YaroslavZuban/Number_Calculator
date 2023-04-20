package com.example.number_calculator.editor_number_complex;

import com.example.number_calculator.Operator;

public class InputComplex {
    public static StringBuilder inputLine = new StringBuilder();
    public static StringBuilder tempLine = new StringBuilder();
    public static StringBuilder resultLine = new StringBuilder();

    public static StringBuilder operation = new StringBuilder();

    public void inputSymbol(String temp) {
        if (temp.equals("OpenBracket")) {
            inputLine.append("(");
        } else {
            inputLine.append(")");
        }
    }

    public void input(String temp) {
        if (!resultLine.toString().equals("")) {
            resultLine = new StringBuilder();
            inputLine = new StringBuilder();
            tempLine = new StringBuilder();
        }

        if (inputLine.toString().equals("") && temp.equals("Point")) {
            inputLine.append("0,");
        } else {
            if (!tempLine.toString().equals("")) {
                tempLine = new StringBuilder();
            }

            if (temp.equals("Point")) {
                if (isSign(inputLine)) {
                    inputLine.append("0,");
                } else {
                    inputLine.append(",");
                }
            } else {
                inputLine.append(temp);
            }
        }
    }

    public void inputOperation(String temp) throws IncorrectTypeException, UnrecognizableElementException, IncorrectElementException {
        String inputOperator = Operator.operatorDefinition(temp);
        operation = new StringBuilder(inputOperator);

        if (!tempLine.toString().equals("")) {
            tempLine = new StringBuilder();
        }

        if (isSign(inputLine)) {
            inputLine.deleteCharAt(inputLine.length() - 1);
            inputLine.append(inputOperator);
        } else {
            if (!resultLine.toString().equals("")) {
                inputLine = new StringBuilder(resultLine);
                resultLine = new StringBuilder();
            }

            inputLine.append(operation);
            tempLine = new StringBuilder();
        }
    }

    public boolean isSign(StringBuilder line) {
        int end = line.length() - 1;

        if (line.charAt(end) == '+' || line.charAt(end) == '*' ||
                line.charAt(end) == '-' || line.charAt(end) == '/') {
            return true;
        }

        return false;
    }

    public static StringBuilder getInputLine() {
        return inputLine;
    }

    public static void setInputLine(StringBuilder inputLine) {
        InputComplex.inputLine = inputLine;
    }

    public static StringBuilder getTempLine() {
        return tempLine;
    }

    public static void setTempLine(StringBuilder tempLine) {
        InputComplex.tempLine = tempLine;
    }

    public static StringBuilder getResultLine() {
        return resultLine;
    }

    public static void setResultLine(StringBuilder resultLine) {
        InputComplex.resultLine = resultLine;
    }

    public static StringBuilder getOperation() {
        return operation;
    }

    public static void setOperation(StringBuilder operation) {
        InputComplex.operation = operation;
    }

}
