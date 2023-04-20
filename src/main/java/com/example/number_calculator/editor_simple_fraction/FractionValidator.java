package com.example.number_calculator.editor_simple_fraction;

public class FractionValidator {
    public static boolean findMaxMin(Fraction fraction_one) {
        if (fraction_one.getNumerator() != Integer.MAX_VALUE && fraction_one.getNumerator() != Integer.MIN_VALUE &&
                fraction_one.getDenominator() != Integer.MAX_VALUE && fraction_one.getDenominator() != Integer.MIN_VALUE)
            return true;

        return false;
    }
}
