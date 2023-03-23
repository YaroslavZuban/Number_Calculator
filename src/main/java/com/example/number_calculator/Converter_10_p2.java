package com.example.number_calculator;

/**
 * Класс который позволяет перевести число из 10-ой СС в любую другую (2-16) СС
 */
public class Converter_10_p2 implements Converter {
    public String conv(String line, int system) {
        StringBuilder temp = new StringBuilder(line);

        if (temp.indexOf("-") != -1) {
            temp.delete(0, 1);
            return String.valueOf(-1 * baseNToDecimal(temp.toString(), system));
        }

        return String.valueOf(baseNToDecimal(line, system));
    }

    public static double baseNToDecimal(String baseNNumber, int baseN) {
        String[] parts = baseNNumber.split("\\."); // Разделяем целую и дробную части
        String integerPart = parts[0];
        String fractionalPart = parts.length > 1 ? parts[1] : "";

        double result = 0;

        // Переводим целую часть в 10-ую систему счисления
        for (int i = 0; i < integerPart.length(); i++) {
            int digit = Character.digit(integerPart.charAt(i), baseN);
            result = result * baseN + digit;
        }

        // Переводим дробную часть в 10-ую систему счисления
        for (int i = 0; i < fractionalPart.length(); i++) {
            int digit = Character.digit(fractionalPart.charAt(i), baseN);
            result += digit / Math.pow(baseN, i + 1);
        }

        return result;
    }


}
