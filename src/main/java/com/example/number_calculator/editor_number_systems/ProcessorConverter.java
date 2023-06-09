package com.example.number_calculator.editor_number_systems;

import com.example.number_calculator.History;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/***
 * содержит методы для выполнения арифметических операций над числами в разных системах счисления,
 * а также для изменения чисел (возведение в квадрат и получение обратного числа)
 */
public class ProcessorConverter {

    public static TPNumber number_one;
    public static TPNumber number_two;
    public static TPNumber number_result;
    public static TPNumber number_temp;
    public static String operation;
    public static int system;

    /**
     * вызывается, когда пользователь нажимает кнопку "=",
     * чтобы выполнить операцию. Если есть два числа и оператор,
     * функция выполняет арифметическую операцию и сохраняет результат
     */
    public void onResultClicked(StringBuilder lineResult) {

        if (number_two != null && number_one != null && !number_two.getNumber().equals("")) {

            number_result = new TPNumber("0", number_two.getSystem());

            if (number_two.getSystem() != Integer.MAX_VALUE) {
                NumberSystemChecker.isNumberValidInBase(number_two.getNumber(), number_two.getSystem());
            }

            NumberSystemChecker.isNumberValidInBase(number_one.getNumber(), number_one.getSystem());

            number_result = Calculator.arithmetic(number_one, number_two, operation);

            String history = number_one.getNumber() + "(" + number_one.getSystem() + ")" + operation +
                    number_two.getNumber() + "(" + number_two.getSystem() + ") =";

            number_one = new TPNumber(number_result);
            lineResult = new StringBuilder(number_result.getNumber());

            History.data.add(history +
                    number_result.getNumber() + "(" + number_result.getSystem() + ")");

        } else if (number_one != null && !operation.equals("")) {
            if (number_temp == null) {
                number_temp = new TPNumber(number_one);
            }

            NumberSystemChecker.isNumberValidInBase(number_one.getNumber(), number_one.getSystem());
            number_two = new TPNumber(number_temp);

            NumberSystemChecker.isNumberValidInBase(number_two.getNumber(), number_two.getSystem());

            number_one = Calculator.arithmetic(number_one, number_two, operation);

            number_two = null;
        }
    }

    /**
     * вызывается, когда пользователь нажимает кнопку для изменения числа ("x^2" или "1/x").
     */
    public void actionOperator(MouseEvent event) {
        String temp = ((Button) event.getSource()).getId().replace("button_", "");


        TPNumber tempNumber = null;

        if (number_result == null) {
            if (number_two == null) {
                if (number_one != null) {
                    tempNumber = number_one;
                }
            } else {
                tempNumber = number_two;
            }
        } else {
            number_one = new TPNumber(number_result);
            number_two = null;
            number_result = null;

            tempNumber = number_one;
            operation = "";
        }

        if (temp.equals("Pow_Two")) {
            tempNumber = tempNumber.power(2);
        } else {
            tempNumber = tempNumber.reciprocalNumber();
        }


        if (number_two != null) {
            number_two = tempNumber;
        } else {
            if (number_one != null) {
                number_one = tempNumber;
            }
        }
    }

    public static TPNumber getNumber_one() {
        return number_one;
    }

    public static void setNumber_one(TPNumber number_one) {
        ProcessorConverter.number_one = number_one;
    }

    public static TPNumber getNumber_two() {
        return number_two;
    }

    public static void setNumber_two(TPNumber number_two) {
        ProcessorConverter.number_two = number_two;
    }

    public static TPNumber getNumber_result() {
        return number_result;
    }

    public static void setNumber_result(TPNumber number_result) {
        ProcessorConverter.number_result = number_result;
    }

    public static TPNumber getNumber_temp() {
        return number_temp;
    }

    public static void setNumber_temp(TPNumber number_temp) {
        ProcessorConverter.number_temp = number_temp;
    }

    public static String getOperation() {
        return operation;
    }

    public static void setOperation(String operation) {
        ProcessorConverter.operation = operation;
    }

    public static int getSystem() {
        return system;
    }

    public static void setSystem(int system) {
        ProcessorConverter.system = system;
    }

}
