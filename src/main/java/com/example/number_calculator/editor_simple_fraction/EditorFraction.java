package com.example.number_calculator.editor_simple_fraction;

import com.example.number_calculator.CalculatorMemory;
import com.example.number_calculator.editor_number_systems.Calculator;
import com.example.number_calculator.editor_number_systems.Converter_10_p2;
import com.example.number_calculator.editor_number_systems.Converter_p1_10;
import com.example.number_calculator.editor_number_systems.NumberInBaseN;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class EditorFraction {
    public static Fraction number_one = null;
    public static Fraction number_two = null;
    public static Fraction number_result = null;
    private static Fraction number_temp = null;

    public static StringBuilder lineInput = new StringBuilder();
    public static StringBuilder lineResult = new StringBuilder();
    public static String operation = "";
    private static String operationTemp = "";

    private static CalculatorMemory calculatorMemory = new CalculatorMemory();

    public void waterNumber(MouseEvent event) {
        String temp = ((Button) event.getSource()).getId().replace("button_", "");
        inputNumber(temp);
    }

    public void waterOperator(MouseEvent event) {
        String temp = ((Button) event.getSource()).getId().replace("button_", "");

        if (temp.equals("SignСhange")) {
            if (number_result != null) {
                number_one = new Fraction(number_result);

                number_one = new Fraction(number_result);
                number_one.setNumerator(negate(number_one.getNumerator()));

                number_result = null;
                number_two = null;

                lineResult = new StringBuilder();
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
            String inputOperator = operatorDefinition(temp);

            if (number_one != null && !operation.equals("") && number_two != null) {
                if (number_result == null) {
                    onResultClicked();
                    number_one = new Fraction(number_result);
                    number_result = null;
                } else {
                    number_one = new Fraction(number_result);
                }

                number_two = new Fraction(Integer.MAX_VALUE, Integer.MIN_VALUE);

                number_result = null;
            } else {
                if (number_two == null) {
                    number_two = new Fraction(Integer.MAX_VALUE, Integer.MIN_VALUE);
                }

                if (!operation.equals(inputOperator)) {
                    number_temp = null;
                }
            }

            operation = inputOperator;
        }

        dataInput();
    }

    public void onResultClicked() {
        if (number_two != null && number_one != null && findMaxMin(number_one, number_two)) {
            number_result = arithmetic(number_one, number_two);

            number_one = new Fraction(number_result);
            lineResult = new StringBuilder(number_result.getNumerator() + "|" + number_result.getDenominator());
        } else if (number_one != null && !operation.equals("")) {
            if (number_temp == null) {
                number_temp = new Fraction(number_one);
            }

            number_two = new Fraction(number_temp);
            number_one = arithmetic(number_one, number_two);
            number_two = null;

            lineResult = new StringBuilder(number_result.getNumerator() + "|" + number_result.getDenominator());
            dataInput();
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

        dataInput();
    }

    public void onDivisionClicked() {
        if (number_two == null && number_one.getNumerator() != Integer.MIN_VALUE && number_one.getNumerator() != Integer.MAX_VALUE) {
            if (number_one.getDenominator() == Integer.MIN_VALUE) {
                number_one.setDenominator(Integer.MAX_VALUE);
            }
        } else if (number_two == null) {
            number_two = new Fraction(Integer.MAX_VALUE, Integer.MIN_VALUE);
        } else if (number_two != null && number_two.getNumerator() != Integer.MIN_VALUE && number_two.getNumerator() != Integer.MAX_VALUE) {
            if (number_two.getDenominator() == Integer.MIN_VALUE) {
                number_two.setDenominator(Integer.MAX_VALUE);
            }
        }
    }

  /*  public void onBaskSpace(MouseEvent event) {
        if (number_two != null) {
            deleteSymbol(number_two);
        } else if (!operation.equals("")) {
            operation = "";
        } else {
            deleteSymbol(number_one);
        }

        dataInput();
    }

    public void onClean(MouseEvent event) {
        lineInput = new StringBuilder("0");
        lineResult = new StringBuilder();

        number_one = null;
        number_two = null;
        number_result = null;

        system = 10;
        operation = "";
    }*/

   /* public void onCleanEntry(MouseEvent event) {
        if (number_two != null && !number_two.getNumber().equals("")) {
            number_two = new NumberInBaseN("", system);
        } else if (number_one != null && !number_one.getNumber().equals("")) {
            number_one = null;
        }

        dataInput();
    }

    public void memory(MouseEvent event) {
        String temp = ((Button) event.getSource()).getId().replace("button_", "");

        if (temp.contains("MPlus")) {
            if (number_result != null) {
                calculatorMemory.memoryPlus(number_result.getNumber(), number_result.getSystem());
            } else {
                if (number_one != null && operation.equals("")) {
                    calculatorMemory.memoryPlus(number_one.getNumber(), number_one.getSystem());
                }
            }
        } else if (temp.contains("MR")) {
            lineInput = new StringBuilder();
            lineResult = new StringBuilder();

            number_two = null;
            number_result = null;

            system = 10;
            operation = "";

            number_one = new NumberInBaseN(calculatorMemory.getMemoryValue(), calculatorMemory.getSystem());
            system = calculatorMemory.getSystem();
            dataInput();
        } else if (temp.contains("MS")) {

            if (number_result != null) {
                calculatorMemory.memorySave(number_result.getNumber());
                calculatorMemory.setSystem(number_result.getSystem());
            } else {
                if (number_one != null && operation.equals("")) {
                    calculatorMemory.memorySave(number_one.getNumber());
                    calculatorMemory.setSystem(number_one.getSystem());
                }
            }

        } else if (temp.contains("MC")) {
            calculatorMemory = new CalculatorMemory();
        }
    }*/

    private Fraction arithmetic(Fraction numberOne, Fraction numberTwo) {
        Fraction numberTemp = null;

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
    }

    private int negate(int temp) {
        return temp * (-1);
    }

    private void inputNumber(String temp) {
        StringBuilder line = null;

        if (number_one != null && number_two != null && number_result != null) {
            number_two = null;
            number_one = null;
            number_result = null;

            operation = "";

            lineInput = new StringBuilder();
            lineResult = new StringBuilder();
        }

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

        // System.out.println("line inputNumber: " + line);
        line.append(temp);
        // System.out.println("line.append inputNumber: " + line);
        enteringNumber(line);

        dataInput();

        // log();
        // System.out.println("----------------------------------------------------------");
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

       /* System.out.println(fraction.toString());
        System.out.println("line numberDefinitions: " + line);*/

        return line;
    }

    private boolean findMaxMin(Fraction fraction_one, Fraction fraction_two) {
        if (fraction_one.getNumerator() != Integer.MAX_VALUE && fraction_one.getNumerator() != Integer.MIN_VALUE &&
                fraction_one.getDenominator() != Integer.MAX_VALUE && fraction_one.getDenominator() != Integer.MIN_VALUE &&
                fraction_two.getNumerator() != Integer.MAX_VALUE && fraction_two.getNumerator() != Integer.MIN_VALUE &&
                fraction_two.getDenominator() != Integer.MAX_VALUE && fraction_two.getDenominator() != Integer.MIN_VALUE) {
            return true;
        }

        return false;
    }

    private void log() {
        if (number_one != null) {
            System.out.println("number_one: " + number_one.toString());
        } else {
            System.out.println("number_one: " + null);
        }

        if (number_two != null) {
            System.out.println("number_two: " + number_two.toString());
        } else {
            System.out.println("number_two: " + null);
        }

        if (number_result != null) {
            System.out.println("number_result: " + number_result.toString());
        } else {
            System.out.println("number_result: " + null);
        }

        if (lineResult != null) {
            System.out.println("lineResult: " + lineResult.toString());
        } else {
            System.out.println("lineResult: " + null);
        }

        if (lineInput != null) {
            System.out.println("lineInput: " + lineInput.toString());
        } else {
            System.out.println("lineInput: " + null);
        }
    }

    private void dataInput() {
        StringBuilder result = new StringBuilder();

        if (number_one != null) {
            if (number_one.getNumerator() != Integer.MAX_VALUE && number_one.getNumerator() != Integer.MIN_VALUE) {
                result.append(addNumberPrintf(number_one.getNumerator()));
            }

            if (number_one.getDenominator() != Integer.MAX_VALUE && number_one.getDenominator() != Integer.MIN_VALUE) {
                result.append("|");
                result.append(addNumberPrintf(number_one.getDenominator()));
            }
        }

        if (!operation.equals("")) {
            result.append(operation);
        }

        if (number_two != null) {
            if (number_two.getNumerator() != Integer.MAX_VALUE && number_two.getNumerator() != Integer.MIN_VALUE) {
                if (number_two.getNumerator() < 0) {
                    result.append("(");
                    result.append(addNumberPrintf(number_two.getNumerator()));
                    result.append(")");
                } else {
                    result.append(addNumberPrintf(number_two.getNumerator()));
                }
            }

            if (number_two.getDenominator() != Integer.MAX_VALUE && number_two.getDenominator() != Integer.MIN_VALUE) {
                result.append("|");
                result.append(addNumberPrintf(number_two.getDenominator()));
            }
        }

        lineInput = new StringBuilder(result);
    }

    public String addNumberPrintf(int number) {
        if (number != Integer.MAX_VALUE && number != Integer.MIN_VALUE) {
            return "" + number;
        }

        return "";
    }

    private String operatorDefinition(String temp) {
        String resultOperator = "";

        switch (temp) {
            case "Multiplication" -> resultOperator = "*";
            case "Division" -> resultOperator = "/";
            case "Addition" -> resultOperator = "+";
            case "Subtraction" -> resultOperator = "-";
        }

        return resultOperator;
    }
}
