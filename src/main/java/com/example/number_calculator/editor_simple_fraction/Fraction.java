package com.example.number_calculator.editor_simple_fraction;

public class Fraction {
    @Override
    public String toString() {
        return  numerator + "/" + denominator ;
    }

    private Integer numerator;
    private Integer denominator;

    public Fraction(Fraction fraction) {
        this.numerator = fraction.getNumerator();
        this.denominator = fraction.getDenominator();

        transferringSign();
    }

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero");
        }

        this.numerator = numerator;
        this.denominator = denominator;

        transferringSign();
    }

    public Integer getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public Integer getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    public void simplify() {
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

        Fraction temp = new Fraction(new Fraction(sumNumerator, commonDenominator));
        temp.simplify();

        return temp;
    }

    public Fraction subtract(Fraction other) {
        int commonDenominator = denominator * other.denominator;
        int diffNumerator = numerator * other.denominator - other.numerator * denominator;

        Fraction temp = new Fraction(diffNumerator, commonDenominator);
        temp.simplify();

        return temp;
    }

    public Fraction multiply(Fraction other) {
        int productNumerator = numerator * other.numerator;
        int productDenominator = denominator * other.denominator;

        if ((numerator < 0 && other.denominator < 0) || (denominator < 0 && other.numerator < 0)) {
            productNumerator = Math.abs(productNumerator);
            productDenominator = Math.abs(productDenominator);
        } else if ((numerator < 0 && other.numerator < 0) || (denominator < 0 && other.denominator < 0)) {
            productNumerator = Math.abs(productNumerator);
            productDenominator = Math.abs(productDenominator);
        }

        Fraction temp = new Fraction(productNumerator, productDenominator);
        temp.simplify();

        return temp;
    }

    public Fraction divide(Fraction other) {
        if (other.numerator == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }

        int quotientNumerator = numerator * other.denominator;
        int quotientDenominator = denominator * other.numerator;

        if ((numerator < 0 && other.denominator < 0) || (denominator < 0 && other.numerator < 0)) {
            quotientNumerator = Math.abs(quotientNumerator);
            quotientDenominator = Math.abs(quotientDenominator);
        } else if ((numerator < 0 && other.numerator < 0) || (denominator < 0 && other.denominator < 0)) {
            quotientNumerator = Math.abs(quotientNumerator);
            quotientDenominator = Math.abs(quotientDenominator);
        }

        Fraction temp = new Fraction(quotientNumerator, quotientDenominator);
        temp.simplify();

        return temp;
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

    private void transferringSign(){
        if (numerator != Integer.MAX_VALUE && numerator != Integer.MIN_VALUE &&
                denominator != Integer.MAX_VALUE && denominator != Integer.MIN_VALUE ) {
            simplify();
        }
    }
    public Fraction reciprocalNumber(Fraction fraction) {
        return new Fraction(fraction.getDenominator(), fraction.getNumerator());
    }

}
