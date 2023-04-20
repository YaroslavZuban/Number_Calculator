package com.example.number_calculator.editor_simple_fraction;

import com.example.number_calculator.Operator;

public class InputFraction {
    public static Fraction number_one = null;
    public static Fraction number_two = null;
    public static Fraction number_result = null;
    public static Fraction number_temp = null;
    public static String operation = "";

    public InputFraction(Fraction number_one,Fraction number_two,Fraction number_result,
                             Fraction number_temp,String operation) {
        this.number_one=number_one;
        this.number_two=number_two;
        this.number_result=number_result;
        this.number_temp=number_temp;
        this.operation=operation;
    }

    public void inputNumber(String temp) {
        System.out.println(temp);
        StringBuilder line ;

        if (number_two == null) {
            if (number_one == null) {
                number_one = new Fraction(Integer.MAX_VALUE, Integer.MIN_VALUE);
                line = new StringBuilder();
            } else {
                line = new StringBuilder(numberDefinitions(number_one));
            }
        } else {
            line = new StringBuilder(numberDefinitions(number_two));
        }

        line.append(temp);
        enteringNumber(line);
    }

    public void onDivisionClicked() {
        if (number_two == null && number_one.getNumerator() != Integer.MIN_VALUE && number_one.getNumerator() != Integer.MAX_VALUE) {
            if (number_one.getDenominator() == Integer.MIN_VALUE) {
                number_one.setDenominator(Integer.MAX_VALUE);
            }
        } else if (number_two == null && number_one.getDenominator() != Integer.MAX_VALUE) {
            number_two = new Fraction(Integer.MAX_VALUE, Integer.MIN_VALUE);
        } else if (number_two != null && number_two.getNumerator() != Integer.MIN_VALUE && number_two.getNumerator() != Integer.MAX_VALUE) {
            if (number_two.getDenominator() == Integer.MIN_VALUE) {
                number_two.setDenominator(Integer.MAX_VALUE);
            }
        }
    }

    public void entryOperator(String temp) {
        if (temp.equals("SignСhange")) {
            if (number_result != null) {
                number_one = new Fraction(number_result);

                number_one = new Fraction(number_result);
                number_one.setNumerator(negate(number_one.getNumerator()));

                number_result = null;
                number_two = null;

                operation = "";
            } else {
                if (number_two != null) {
                    number_two.setNumerator(negate(number_two.getNumerator()));
                } else {
                    if (number_one != null) {
                        number_one.setNumerator(negate(number_one.getNumerator()));
                    }
                }
            }
        } else {
            String inputOperator = Operator.operatorDefinition(temp);

            if (number_one != null && !operation.equals("") && number_two != null && number_two.getNumerator()!=Integer.MAX_VALUE) {
                if (number_result == null) {
                    ProcessorFraction result=new ProcessorFraction();
                    result.onResultClicked();
                    number_result=ProcessorFraction.number_result;
                    number_one = new Fraction(number_result);
                    number_result = null;
                } else {
                    number_one = new Fraction(number_result);
                }

                number_two = new Fraction(Integer.MAX_VALUE, Integer.MIN_VALUE);

                number_result = null;
            } else {
                if (number_one.getDenominator() == Integer.MIN_VALUE || number_one.getDenominator() == Integer.MAX_VALUE) {
                    number_one.setDenominator(1);
                }

                if (number_two == null) {
                    number_two = new Fraction(Integer.MAX_VALUE, Integer.MIN_VALUE);
                }

                if (!operation.equals(inputOperator)) {
                    number_temp = null;
                }
            }

            operation = inputOperator;
        }
    }
    private int negate(int temp) {
        return temp * (-1);
    }

    private void enteringNumber(StringBuilder line) {
        if (number_two == null) {
            if (number_one.getDenominator() == Integer.MAX_VALUE || number_one.getDenominator() != Integer.MIN_VALUE) {
                number_one.setDenominator(Integer.parseInt(line.toString()));
            } else if (number_one.getNumerator() == Integer.MAX_VALUE || number_one.getNumerator() != Integer.MIN_VALUE) {
                number_one.setNumerator(Integer.parseInt(line.toString()));
            }
        } else {
            if (number_two.getDenominator() == Integer.MAX_VALUE || number_two.getDenominator() != Integer.MIN_VALUE) {
                number_two.setDenominator(Integer.parseInt(line.toString()));
            } else if (number_two.getNumerator() == Integer.MAX_VALUE || number_two.getNumerator() != Integer.MIN_VALUE) {
                number_two.setNumerator(Integer.parseInt(line.toString()));
            }
        }
    }

    private StringBuilder numberDefinitions(Fraction fraction) {
        StringBuilder line = new StringBuilder();

        if (fraction.getDenominator() == Integer.MAX_VALUE) {
            // System.out.println("условие 1");
        } else if (fraction.getDenominator() != Integer.MIN_VALUE) {
            line.append(fraction.getDenominator());
            //  System.out.println("условие 2");
        } else if (fraction.getNumerator() == Integer.MAX_VALUE) {
            // System.out.println("условие 3");
        } else if (fraction.getNumerator() != Integer.MIN_VALUE) {
            line.append(fraction.getNumerator());
            // System.out.println("условие 4");
        }

        return line;
    }
}
