package com.example.number_calculator.editor_comprehensive;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComplexParser {
    private static final Pattern PATTERN = Pattern.compile("([-+]?[0-9]*\\.?[0-9]+i?)");

    public static Complex parse(String input) {
        double real = 0;
        double imaginary = 0;

        Matcher matcher = PATTERN.matcher(input);
        while (matcher.find()) {
            String token = matcher.group();
            if (token.contains("i")) {
                imaginary += parseImaginary(token);
            } else {
                real += Double.parseDouble(token);
            }
        }

        return new Complex(real, imaginary);
    }

    private static double parseImaginary(String input) {
        if (input.equals("i") || input.equals("+i")) {
            return 1;
        } else if (input.equals("-i")) {
            return -1;
        } else if (input.endsWith("i")) {
            return Double.parseDouble(input.substring(0, input.length() - 1));
        } else {
            return 0;
        }
    }
}
