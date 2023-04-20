package com.example.number_calculator;

/**
 * Данный класс предназначен для определения математического оператора,
 * соответствующего выбранной пользователем операции в калькуляторе
 */
public class Operator {
    public static String operatorDefinition(String temp) {
        String resultOperator = "";

        switch (temp) {
            case "Multiplication" -> resultOperator = "*";
            case "Division" -> resultOperator = "/";
            case "Addition" -> resultOperator = "+";
            case "Subtraction" -> resultOperator = "-";
        }

        return resultOperator;
    }
}
