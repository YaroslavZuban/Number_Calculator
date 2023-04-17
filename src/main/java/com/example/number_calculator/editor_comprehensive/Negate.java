package com.example.number_calculator.editor_comprehensive;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Negate {
    public static String negateLastNumber(String input) {
        // Поиск последнего числа в строке
        Matcher matcher = Pattern.compile("(\\-?\\d+\\.?\\d*|\\-?\\d*\\.?\\d+i)$").matcher(input);
        if (matcher.find()) {
            String number = matcher.group();

            // Проверка, является ли число комплексным
            if (number.contains("i")) {
                String[] parts = number.split("i");
                double realPart = Double.parseDouble(parts[0]);
                double imaginaryPart = Double.parseDouble(parts[1]);

                // Изменение знака мнимой части комплексного числа
                double negatedImaginary = -1 * imaginaryPart;

                // Создание нового комплексного числа с измененным знаком мнимой части
                String negated = Double.toString(realPart) + (negatedImaginary < 0 ? "" : "+") + negatedImaginary + "i";

                // Замена найденного числа в исходной строке
                input = input.replace(number, negated);

            } else {
                // Изменение знака вещественного числа
                double negated = -1 * Double.parseDouble(number);

                // Замена найденного числа в исходной строке
                input = input.replace(number, Double.toString(negated));
            }
        }
        return input;
    }
}
