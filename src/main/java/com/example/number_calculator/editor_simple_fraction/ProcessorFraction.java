package com.example.number_calculator.editor_simple_fraction;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ProcessorFraction {
    private static Fraction number_one = null;
    private static Fraction number_two = null;
    private static Fraction number_result = null;
    private static Fraction number_temp = null;
    private static String operation = "";


    public ProcessorFraction(Fraction number_one, Fraction number_two, Fraction number_result,
                             Fraction number_temp, String operation) {
        this.number_one = number_one;
        this.number_two = number_two;
        this.number_result = number_result;
        this.number_temp = number_temp;
        this.operation = operation;
    }

    public void onResultClicked() {
        if (number_two.getNumerator() != Integer.MAX_VALUE && number_two.getDenominator()==Integer.MIN_VALUE) {
            number_two.setDenominator(1);
        }

        if (number_one != null && !operation.equals("") && number_two.getNumerator()==Integer.MAX_VALUE) {
            if (number_temp == null) {
                number_temp = new Fraction(number_one);
            }

            number_two = new Fraction(number_temp);
            number_one = arithmetic(number_one, number_two);
            number_two = new Fraction(Integer.MAX_VALUE,Integer.MIN_VALUE);
        } else if (number_two != null && number_one != null && findMaxMin(number_one) && findMaxMin(number_one)) {
            number_result = arithmetic(number_one, number_two);

            if (number_result == null) {
                clear();
            } else {
                number_one = new Fraction(number_result);
            }
        }
    }

    public void actionOperator(String temp) {
        Fraction lineTemp = null;

        if (number_result == null) {
            if (number_two == null) {
                if (number_one != null) {
                    lineTemp = new Fraction(number_one);
                }
            } else {
                lineTemp = new Fraction(number_two);
            }
        } else {
            number_one = new Fraction(number_result);
            number_two = null;
            number_result = null;

            lineTemp = new Fraction(number_one);
            operation = "";
        }

        try {
            if (temp.equals("Pow_Two")) {
                lineTemp = lineTemp.power(2);
            } else {
                lineTemp = lineTemp.reciprocalNumber(lineTemp);
            }


            if (number_two != null) {
                number_two = new Fraction(lineTemp);
            } else {
                if (number_one != null) {
                    number_one = new Fraction(lineTemp);
                }
            }

        } catch (Exception e) {
            clear();
        }

    }


    public static Fraction getNumber_one() {
        return number_one;
    }

    public static void setNumber_one(Fraction number_one) {
        ProcessorFraction.number_one = number_one;
    }

    public static Fraction getNumber_two() {
        return number_two;
    }

    public static void setNumber_two(Fraction number_two) {
        ProcessorFraction.number_two = number_two;
    }

    public static Fraction getNumber_result() {
        return number_result;
    }

    public static void setNumber_result(Fraction number_result) {
        ProcessorFraction.number_result = number_result;
    }

    public static Fraction getNumber_temp() {
        return number_temp;
    }

    public static void setNumber_temp(Fraction number_temp) {
        ProcessorFraction.number_temp = number_temp;
    }

    public static String getOperation() {
        return operation;
    }

    public static void setOperation(String operation) {
        ProcessorFraction.operation = operation;
    }

    private int negate(int temp) {
        return temp * (-1);
    }

    private Fraction arithmetic(Fraction numberOne, Fraction numberTwo) {
        Fraction numberTemp = null;

        try {
            if (operation.contains("*")) {
                numberTemp = numberOne.multiply(numberTwo);
            } else if (operation.contains("/")) {
                numberTemp = numberOne.divide(numberTwo);
            } else if (operation.contains("+")) {
                numberTemp = numberOne.add(numberTwo);
            } else if (operation.contains("-")) {
                numberTemp = numberOne.subtract(numberTwo);
            }

            return numberTemp;
        } catch (Exception e) {
            clear();
        }

        return null;
    }

    private void clear() {
        number_one = new Fraction(Integer.MAX_VALUE, Integer.MIN_VALUE);
        number_two = null;
        number_result = null;
        operation = "";
    }

    private boolean findMaxMin(Fraction fraction_one) {
        if (fraction_one.getNumerator() != Integer.MAX_VALUE && fraction_one.getNumerator() != Integer.MIN_VALUE &&
                fraction_one.getDenominator() != Integer.MAX_VALUE && fraction_one.getDenominator() != Integer.MIN_VALUE)
            return true;


        return false;
    }
}
