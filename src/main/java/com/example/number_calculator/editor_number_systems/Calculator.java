package com.example.number_calculator.editor_number_systems;

public class Calculator {
    public double add(double a,double b) {
        return a + b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double divide(double a, double b) {
        return a / b;
    }

    public double power(double a) {
        return (int) Math.pow(a, 2);
    }

    public double reciprocalNumber(double a) {
        return 1 / a;
    }

}
