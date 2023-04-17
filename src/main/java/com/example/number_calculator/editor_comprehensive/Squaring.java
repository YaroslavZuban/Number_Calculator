package com.example.number_calculator.editor_comprehensive;

public class Squaring {
    public static String squareNumber(String input) {
        if (input.contains("i")) { // Проверяем, является ли число комплексным
            String number = input.substring(0, input.length() - 1);
            double n = Double.valueOf(number) * (-1);

            double result = n * n * -1;
            return String.valueOf(result);
        } else {
            double number = Double.parseDouble(input);
            double square = Math.pow(number, 2);
            return Double.toString(square);
        }
    }
}
