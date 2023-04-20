package com.example.number_calculator.editor_number_complex;

import com.example.number_calculator.SignTranslation;

public class Negate {
    /**
     * Эта функция принимает строку, представляющую число,
     * и инвертирует ее знак, то есть если число положительное,
     * то функция возвращает отрицательное число, и наоборот.
     * @param number
     * @return
     */
    public static String negateLastNumber(String number) {
        StringBuilder temp = new StringBuilder(SignTranslation.negate(number));

        if (temp.indexOf("-") != -1) {
            temp.insert(0, "(");
            temp.append(")");
        }

        return temp.toString();
    }
}
