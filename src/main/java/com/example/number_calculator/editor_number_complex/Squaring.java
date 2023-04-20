package com.example.number_calculator.editor_number_complex;

public class Squaring {
    /**
     * Функция squareNumber(String input) принимает строковый аргумент input,
     * который может быть числом (действительным или комплексным).
     * Если число является комплексным, то функция возвращает квадрат этого числа в
     * комплексной форме в виде строки. Если число является действительным,
     * то функция возвращает квадрат этого числа в виде строки без десятичной части.
     */
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

    /***
     *принимает строковый аргумент str и удаляет десятичную часть
     * (то есть все символы после запятой), если в строке есть запятая.
     * Если запятой нет, то функция возвращает исходную строку без изменений.
     */
    public static String removeDecimalPart(String str) {
        int commaPos = str.indexOf(',');
        if (commaPos >= 0) {
            return str.substring(0, commaPos);
        }
        return str;
    }
}
