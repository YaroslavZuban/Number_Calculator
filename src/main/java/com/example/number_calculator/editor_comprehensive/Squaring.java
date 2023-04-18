package com.example.number_calculator.editor_comprehensive;

public class Squaring {
    public static String squareNumber(String input) {
        if (input.contains("i")) { // Проверяем, является ли число комплексным
            String number = input.substring(0, input.length() - 1);
            double n = Double.valueOf(number) * (-1);

            double result = n * n * -1;
            return "(" + removeDecimalPart(String.valueOf(result).replace('.', ',')) + ")";
        } else {
            double number = Double.parseDouble(input);
            double square = Math.pow(number, 2);

            return removeDecimalPart(String.valueOf(square).replace('.', ','));
        }
    }

    public static String removeDecimalPart(String str) {
        int commaPos = str.indexOf(',');
        if (commaPos >= 0) {
            return str.substring(0, commaPos);
        }
        return str;
    }
}
