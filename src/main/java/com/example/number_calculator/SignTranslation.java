package com.example.number_calculator;

/**инвертирует знак переданного ему числа
 */
public class SignTranslation {
    public static String negate(String temp) {
        StringBuilder tempTime = new StringBuilder(temp);

        if (tempTime.toString().contains("-")) {
            tempTime.deleteCharAt(0);
        } else {
            tempTime.insert(0, "-");
        }

        return tempTime.toString();
    }
}
