package com.example.number_calculator.editor_number_systems;

import com.example.number_calculator.editor_number_systems.Converter;

import java.math.BigDecimal;

/**
 * Класс который позволяет перевести число из 10-ой СС в любую другую (2-16) СС
 */
public class Converter_10_p2 implements Converter {
    public String conv(String line, int system) {
        StringBuilder temp = new StringBuilder(fixFloatingPointString(line));
      /*  System.out.println("Converter_10_p2");
        System.out.println("Число без обработки равно temp: "+temp);*/


        if (temp.indexOf("-") != -1) {
            temp.delete(0, 1);
            temp = new StringBuilder("-" + decimalToBaseN(Double.parseDouble(temp.toString()), system));
        } else {
            temp = new StringBuilder(decimalToBaseN(Double.parseDouble(temp.toString()), system));
        }

        /* System.out.println("Число без обработки равно result: "+temp);*/

        temp=new StringBuilder(addNull(temp.toString()));

        return fixFloatingPointString(removeTrailingZeros(temp.toString()));
    }

    private String fixFloatingPointString(String input) {
        String output;

        if (input.startsWith("-")) {
            output = "-" + input.substring(1);
        } else if (input.startsWith(".")) {
            output = "0" + input;
        } else {
            output = input;
        }

        return output;
    }

    private String addNull(String number) {
        StringBuilder temp = new StringBuilder(number);
        if (temp.charAt(0) == '-' && temp.charAt(1) == '.') {
            temp.insert(1, "0");
        } else if (temp.charAt(0) == '.'){
            temp.insert(0, "0");
        }

        return temp.toString();
    }

    private String removeTrailingZeros(String numberStr) {
        if (numberStr.contains(".")) {
            String result = numberStr.replaceAll("0*$", "");
            result = result.replaceAll("\\.$", ""); // Удаление точки, если она осталась в конце
            return result;
        } else {
            return numberStr;
        }
    }

    public static String decimalToBaseN(double decimalNumber, int baseN) {
        if (decimalNumber == 0) {
            return "0";
        }

        StringBuilder result = new StringBuilder();
        int integerPart = (int) decimalNumber;
        double fractionalPart = decimalNumber - integerPart;

        // Перевод целой части числа в N систему счисления
        while (integerPart > 0) {
            int remainder = integerPart % baseN;
            if (remainder < 10) {
                result.insert(0, remainder);
            } else {
                result.insert(0, (char) ('A' + remainder - 10));
            }
            integerPart /= baseN;
        }

        // Добавление разделителя между целой и дробной частями
        if (fractionalPart > 0) {
            result.append('.');
        }

        // Перевод дробной части числа в N систему счисления
        int maxFractionalDigits = 10; // Максимальное количество знаков после запятой
        while (fractionalPart > 0 && maxFractionalDigits-- > 0) {
            fractionalPart *= baseN;
            int digit = (int) fractionalPart;
            if (digit < 10) {
                result.append(digit);
            } else {
                result.append((char) ('A' + digit - 10));
            }
            fractionalPart -= digit;
        }

        return result.toString();
    }
}
