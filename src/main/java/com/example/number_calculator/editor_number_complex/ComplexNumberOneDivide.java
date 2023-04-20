package com.example.number_calculator.editor_number_complex;

/**
 * Данный класс содержит метод divideOneByNumber, который принимает на вход строку,
 * содержащую действительное число или комплексное число в виде строки.
 * Метод вычисляет результат деления 1 на это число и возвращает результат
 * также в виде строки. Если переданное число является комплексным,
 * то метод проводит необходимые вычисления с учетом формата комплексного числа.
 * Результат округляется до 5 знаков после запятой.
 */
public class ComplexNumberOneDivide {
    public static String divideOneByNumber(String input) {
        String number = input.trim().replaceAll(" ", "");
        double realPart = 0;
        double imaginaryPart = 0;
        if (number.contains("i")) {
            imaginaryPart = Double.parseDouble(number.replaceAll("[^i-]", ""));
            if (number.startsWith("-")) {
                imaginaryPart = -imaginaryPart;
            }
        } else {
            realPart = Double.parseDouble(number);
        }
        double result = 1 / (realPart + imaginaryPart);
        String output;
        if (imaginaryPart == 0) {
            output = String.format("%.5f", result);
        } else {
            output = String.format("%.5f", realPart * result) + (imaginaryPart >= 0 ? "+" : "") +
                    String.format("%.5fi", imaginaryPart * result);
        }
        return output;
    }
}
