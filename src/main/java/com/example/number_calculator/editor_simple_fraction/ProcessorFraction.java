package com.example.number_calculator.editor_simple_fraction;

import com.example.number_calculator.History;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ProcessorFraction {
    public static Fraction number_one = null;
    public static Fraction number_two = null;
    public static Fraction number_result = null;
    public static Fraction number_temp = null;
    public static String operation = "";

    public void onResultClicked() {
        if (number_two.getNumerator() != Integer.MAX_VALUE && number_two.getDenominator() == Integer.MIN_VALUE) {
            number_two.setDenominator(1);
        }

        if (number_one != null && !operation.equals("") && number_two.getNumerator() == Integer.MAX_VALUE) {
            if (number_temp == null) {
                number_temp = new Fraction(number_one);
            }

            number_two = new Fraction(number_temp);
            number_one = arithmetic(number_one, number_two);
            number_two = new Fraction(Integer.MAX_VALUE, Integer.MIN_VALUE);
        } else if (number_two != null && number_one != null && findMaxMin(number_one) && findMaxMin(number_one)) {
            number_result = arithmetic(number_one, number_two);

            if (number_result == null) {
                clear();
            } else {
                number_one = new Fraction(number_result);
                String result = "(" + number_one.toString() + ")" + operation +
                        "(" + number_two.toString() + ") = " + number_result.toString();
                History.data.add(result);
            }
        }
    }

    public void actionOperator(MouseEvent event) {
        String temp = ((Button) event.getSource()).getId().replace("button_", "");
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
