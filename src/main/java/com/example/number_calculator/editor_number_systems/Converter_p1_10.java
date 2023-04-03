package com.example.number_calculator.editor_number_systems;

import com.example.number_calculator.editor_number_systems.Converter;

import java.math.BigDecimal;

/**
 * Класс который позволяет перевести число из одной СС в 10-ную
 */
public class Converter_p1_10 implements Converter {
    private final String digits = "0123456789ABCDEF";

    public String conv(String line, int system) {
        StringBuilder temp = new StringBuilder(roundNumber(fixFloatingPointString(line),5));
       /* System.out.println("Converter_p1_10");
        System.out.println("Число без обработки равно temp: "+temp);*/
        StringBuilder result;

        if (temp.indexOf("-") != -1) {
            temp.delete(0, 1);
            result=new StringBuilder("-"+parseNumber(temp.toString(), system));
        }else{
            result=new StringBuilder(String.valueOf(parseNumber(temp.toString(), system)));
        }
       /* System.out.println("Число без обработки равно result: "+result);*/

        return fixFloatingPointString(removeTrailingZeros(result.toString()));
    }

    private  String roundNumber(String input, int n) {
        int decimalIndex = input.indexOf(".");
        if (decimalIndex == -1) {
            // Число целое, добавляем десятичную часть
            input += ".";
            for (int i = 0; i < n; i++) {
                input += "0";
            }
        } else {
            // Число с дробной частью
            int numDecimalPlaces = input.length() - decimalIndex - 1;
            if (numDecimalPlaces < n) {
                // Дополняем число нулями до 3 знаков после запятой
                for (int i = 0; i < n - numDecimalPlaces; i++) {
                    input += "0";
                }
            } else if (numDecimalPlaces > n) {
                // Обрезаем число до 3 знаков после запятой
                input = input.substring(0, decimalIndex + n + 1);
            }
        }
        return input;
    }

    public static String removeTrailingZeros(String numberStr) {
        double number = Double.parseDouble(numberStr);
        if (number == (long) number) {
            return String.format("%d", (long) number);
        } else {
            return String.format("%s", number).replaceAll("\\.?0*$", "");
        }
    }


    private String fixFloatingPointString(String input) {
        String output;

        if (input.startsWith("-")) {
            output = "-"  + input.substring(1);
        } else if (input.startsWith(".")) {
            output = "0" + input;
        } else {
            output = input;
        }

        return output;
    }

    private double parseNumber(String num, int base) {
        num = num.toUpperCase(); // digits are in UPPER_CASE
        double val = 0;
        int i = 0;

        while (i < num.length()) {// пока не кончилась строка
            char c = num.charAt(i);

            if (c == '.') { // нашли точку '.'
                i++; // Переместить на следующий символ и выйти из цикла.
                break;
            }

            int d = digits.indexOf(c); // Индексы совпадают с числами из [0..15]

            if (d == -1 || d >= base)
                return Double.NaN;

            val = base * val + d;
            i++;
        }

        int power = 1; // вычислить лишний порядок.

        while (i < num.length()) {
            char c = num.charAt(i);
            int d = digits.indexOf(c);

            if (d == -1 || d >= base)
                return Double.NaN;

            power *= base; // увеличиваем степень порядка на единицу
            val = base * val + d;
            i++;
        }

        return val / power;
    }

}
