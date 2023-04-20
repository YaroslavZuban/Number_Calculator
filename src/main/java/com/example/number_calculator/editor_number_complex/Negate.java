package com.example.number_calculator.editor_number_complex;

import com.example.number_calculator.SignTranslation;

public class Negate {
    public static String negateLastNumber(String number) {
        StringBuilder temp = new StringBuilder(SignTranslation.negate(number));

        if (temp.indexOf("-") != -1) {
            temp.insert(0, "(");
            temp.append(")");
        }

        return temp.toString();
    }
}
