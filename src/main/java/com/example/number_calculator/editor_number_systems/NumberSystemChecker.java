package com.example.number_calculator.editor_number_systems;

public class NumberSystemChecker {
    /**
     * Проверяет, что число number принадлежит системе счисления с основанием base
     *
     * @param number - число для проверки
     * @param base   - основание системы счисления
     * @throws IllegalArgumentException если число не принадлежит системе счисления
     */
    public static void isNumberValidInBase(String number, int base) {
        String digits = "0123456789ABCDEF";
        String tempLine = digits.substring(0, (int) base);

        //данный метод позволяет найти обнаружить в строке лишние элементы
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) != '-' && number.charAt(i) != '.' && tempLine.indexOf(number.charAt(i)) == -1) {
                throw new IllegalArgumentException();
            }
        }
    }
}
