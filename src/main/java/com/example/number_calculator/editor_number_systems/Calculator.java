package com.example.number_calculator.editor_number_systems;

public class Calculator {
    public static TPNumber arithmetic(TPNumber number_one, TPNumber number_two,String operation) {
        TPNumber result = null;

        if (operation.contains("*")) {
            result = number_one.multiply(number_two);
        } else if (operation.contains("/")) {
            result = number_one.divide(number_two);
        } else if (operation.contains("+")) {
            result = number_one.add(number_two);
        } else if (operation.contains("-")) {
            result = number_one.subtract(number_two);
        }

        return result;
    }
}
