package com.example.number_calculator.editor_comprehensive;

public class LastDigitSignChanger {
    public static String changeSign(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        char lastChar = input.charAt(input.length() - 1);

        if (Character.isDigit(lastChar)) {
            int lastDigitIndex = input.length() - 1;

            while (lastDigitIndex > 0 && Character.isDigit(input.charAt(lastDigitIndex - 1))) {
                lastDigitIndex--;
            }

            StringBuilder builder = new StringBuilder(input);

            if (lastChar == '-') {
                builder.setCharAt(lastDigitIndex, '+');
            } else if (lastChar == '+') {
                builder.setCharAt(lastDigitIndex, '-');
            } else {
                builder.insert(input.length(), '-');
            }

            return builder.toString();
        } else {
            return input;
        }
    }
}
