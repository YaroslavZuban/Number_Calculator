package com.example.number_calculator.editor_simple_fraction;

public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero");
        }

        this.numerator = numerator;
        this.denominator = denominator;

        simplify();
    }

    private void simplify() {
        int gcd = gcd(numerator, denominator);

        numerator /= gcd;
        denominator /= gcd;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public Fraction add(Fraction other) {
        int commonDenominator = denominator * other.denominator;
        int sumNumerator = numerator * other.denominator + other.numerator * denominator;

        return new Fraction(sumNumerator, commonDenominator);
    }

    public Fraction subtract(Fraction other) {
        int commonDenominator = denominator * other.denominator;
        int diffNumerator = numerator * other.denominator - other.numerator * denominator;

        return new Fraction(diffNumerator, commonDenominator);
    }

    public Fraction multiply(Fraction other) {
        int productNumerator = numerator * other.numerator;
        int productDenominator = denominator * other.denominator;

        return new Fraction(productNumerator, productDenominator);
    }

    public Fraction divide(Fraction other) {
        if (other.numerator == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }

        int quotientNumerator = numerator * other.denominator;
        int quotientDenominator = denominator * other.numerator;
        return new Fraction(quotientNumerator, quotientDenominator);
    }

    public Fraction power(int exponent) {
        if (exponent < 0) {
            // для отрицательных степеней дроби нужно инвертировать
            // и возведенная в степень дробь станет обратной дробью, а затем упростить
            numerator = denominator - numerator;
            int temp = numerator;
            numerator = denominator;
            denominator = temp;
            exponent = -exponent;
            simplify();
        }

        if (exponent == 0) {
            return new Fraction(1, 1);
        }

        Fraction result = new Fraction(numerator, denominator);

        for (int i = 1; i < exponent; i++) {
            result = result.multiply(this);
        }

        return result;
    }

    public String toString() {
        return numerator + "/" + denominator;
    }
}
