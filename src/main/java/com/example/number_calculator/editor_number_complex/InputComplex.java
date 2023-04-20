package com.example.number_calculator.editor_number_complex;

import com.example.number_calculator.Operator;

/**
 * Данный класс представляет собой часть программы калькулятора и отвечает за обработку ввода пользователя.
 */
public class InputComplex {
    public static StringBuilder inputLine = new StringBuilder();
    public static StringBuilder tempLine = new StringBuilder();
    public static StringBuilder resultLine = new StringBuilder();

    public static StringBuilder operation = new StringBuilder();

    /**
     *  добавляет символ открытой или закрытой скобки в строку inputLine,
     *  в зависимости от того, какую кнопку пользователь нажал на экране.
     * @param temp
     */
    public void inputSymbol(String temp) {
        if (temp.equals("OpenBracket")) {
            inputLine.append("(");
        } else {
            inputLine.append(")");
        }
    }

    /**
     * добавляет числа и точку в строку inputLine в соответствии с нажатыми пользователем кнопками.
     * @param temp
     */
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

    /**
     * добавляет оператор (+,-,*,/) в строку inputLine в соответствии с нажатыми пользователем кнопками,
     * проверяет правильность введенной операции и выбрасывает соответствующие исключения, если операция неправильна.
     * @param temp
     * @throws IncorrectTypeException
     * @throws UnrecognizableElementException
     * @throws IncorrectElementException
     */
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

    /**
     *  проверяет, является ли последний символ в строке inputLine знаком операции (+,-,*,/).
     * @param line
     * @return
     */
    public boolean isSign(StringBuilder line) {
        int end = line.length() - 1;

        if (line.charAt(end) == '+' || line.charAt(end) == '*' ||
                line.charAt(end) == '-' || line.charAt(end) == '/') {
            return true;
        }

        return false;
    }
}
