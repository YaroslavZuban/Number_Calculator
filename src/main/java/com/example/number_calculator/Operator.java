package com.example.number_calculator;

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
