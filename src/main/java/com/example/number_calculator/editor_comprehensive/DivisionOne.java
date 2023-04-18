package com.example.number_calculator.editor_comprehensive;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DivisionOne {
    public static String inverseLastNumber(String input) {
        // Поиск последнего числа в строке
        Matcher matcher = Pattern.compile("(\\-?\\d+\\.?\\d*|\\-?\\d*\\.?\\d+i)$").matcher(input);
        if (matcher.find()) {
            String number = matcher.group();

            // Проверка, является ли число комплексным
            if (number.contains("i")) {
                String[] parts = number.split("i");
                double realPart = Double.parseDouble(parts[0]);
                double imaginaryPart = Double.parseDouble(parts[1]);

                // Деление 1 на комплексное число
                double denominator = realPart * realPart + imaginaryPart * imaginaryPart;
                double realPartInverted = realPart / denominator;
                double imaginaryPartInverted = -imaginaryPart / denominator;

                // Создание нового комплексного числа и замена найденного числа в исходной строке
                ComplexNumber inverted = null;

                try {
                    inverted = new ComplexNumber(number);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                input = input.replace(number, inverted.toString());

            } else {
                // Деление 1 на вещественное число
                double inverted = 1 / Double.parseDouble(number);

                // Замена найденного числа в исходной строке
                input = input.replace(number, Double.toString(inverted));
            }
        }

        return input;
    }

}
