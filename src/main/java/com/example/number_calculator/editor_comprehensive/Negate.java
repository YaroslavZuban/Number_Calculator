package com.example.number_calculator.editor_comprehensive;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Negate {
    public static String negateLastNumber(String number) {
        StringBuilder temp=new StringBuilder(number);

        if (number.contains("-")) {
            temp.deleteCharAt(0);
        } else {
            temp.insert(0,"-");
        }

        return temp.toString();
    }
}
