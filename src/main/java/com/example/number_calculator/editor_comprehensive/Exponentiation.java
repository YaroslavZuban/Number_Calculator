package com.example.number_calculator.editor_comprehensive;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exponentiation {
    public static String squareLastNumber(String input) {
        // Поиск последнего числа в строке
        Matcher matcher = Pattern.compile("(\\-?\\d+\\.?\\d*|\\-?\\d*\\.?\\d+i)$").matcher(input);
        if (matcher.find()) {
            String number = matcher.group();

            // Проверка, является ли число комплексным
            if (number.contains("i")) {
                String[] parts = number.split("i");
                double realPart = Double.parseDouble(parts[0]);
                double imaginaryPart = Double.parseDouble(parts[1]);

                // Возведение в квадрат комплексного числа
                double realSquared = realPart * realPart - imaginaryPart * imaginaryPart;
                double imaginarySquared = 2 * realPart * imaginaryPart;

                // Создание нового комплексного числа и замена найденного числа в исходной строке
                ComplexNumber squared = null;

                try {
                    squared = new ComplexNumber(number);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                input = input.replace(number, squared.toString());

            } else {
                // Возведение в квадрат вещественного числа
                double squared = Math.pow(Double.parseDouble(number), 2);

                // Замена найденного числа в исходной строке
                input = input.replace(number, Double.toString(squared));
            }
        }

        return input;
    }

}
