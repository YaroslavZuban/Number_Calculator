package com.example.number_calculator.editor_number_systems;

import com.example.number_calculator.CalculatorMemory;
import com.example.number_calculator.History;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;


public class Editor {
    public static NumberInBaseN number_one = null;
    public static NumberInBaseN number_two = null;
    public static NumberInBaseN tempNumber = null;

    public static StringBuilder lineInput = new StringBuilder();
    public static StringBuilder result = new StringBuilder();
    private Calculator calculator = new Calculator();
    private String op;

    public static int systemNumber = 10;
    private static int count = 0;
    private int countResult = 0;

    public void waterSymbol(MouseEvent event) {
        StringBuilder tempInput;

        if (number_one == null) {
            number_one = new NumberInBaseN();
            tempInput = new StringBuilder();
        } else {
            tempInput = new StringBuilder(number_one.getNumber());
        }

        String temp = ((Button) event.getSource()).getId().replace("button_", "");
        String point = ".";

        if (lineInput.toString().equals("0")) {
            if (temp.equals("Point")) {
                tempInput = new StringBuilder("0" + point);
                lineInput = new StringBuilder("0" + point);
            } else {
                tempInput = new StringBuilder(temp);
                lineInput = new StringBuilder(temp);
            }
        } else {
            StringBuilder line = new StringBuilder(lineInput);
            tempInput = new StringBuilder(number_one.getNumber());

            if (temp.equals("Point")) {
                tempInput.append(point);
                line.append(point);
            } else {
                tempInput.append(temp);
                line.append(temp);
            }

            lineInput = line;
        }

        number_one.setNumber(tempInput.toString());
        number_one.setSystem(systemNumber);
    }

    public void actionOperator(MouseEvent event) {
        StringBuilder result = new StringBuilder(lineInput.toString());
        String temp = ((Button) event.getSource()).getId().replace("button_", "");

        if (number_one == null) {
            number_one = new NumberInBaseN(result.toString(), systemNumber);
        }

        double numberOne = Double.parseDouble(new Converter_p1_10().conv(number_one.getNumber(), number_one.getSystem()));
        double number;

        if (temp.equals("Pow_Two")) {
            number = calculator.power(numberOne);
        } else {
            number = calculator.reciprocalNumber(numberOne);
        }

        lineInput.delete(lineInput.indexOf(op) + 1, lineInput.length());

        number_one.setNumber(String.valueOf(new Converter_10_p2().conv(String.valueOf(number), number_one.getSystem())));
        number_one.setSystem(systemNumber);
        lineInput.append(number_one.getNumber());
    }


    public void waterOperator(MouseEvent event) {
        String temp = ((Button) event.getSource()).getId().replace("button_", "");
        count = 0;

        if (temp.equals("SignСhange")) {
            if (number_two == null) {
                if (lineInput.indexOf("-") != -1)
                    lineInput.deleteCharAt(0);
                else {
                    lineInput.insert(0, "-");
                }

                number_one.setNumber(lineInput.toString());
            } else {
                StringBuilder tempSign = new StringBuilder(number_one.getNumber());

                if (number_one.getNumber().contains("-")) {
                    tempSign.deleteCharAt(0);
                    number_one.setNumber(tempSign.toString());
                    lineInput = new StringBuilder(number_two.getNumber() + op + number_one.getNumber());
                } else {
                    tempSign.insert(0, "-");
                    number_one.setNumber(tempSign.toString());
                    lineInput = new StringBuilder(number_two.getNumber() + op + "(" + number_one.getNumber() + ")");
                }
            }
        } else {
            if (temp.equals("Multiplication")) {
                op = "*";
            } else if (temp.equals("Division")) {
                op = "/";
            } else if (temp.equals("Addition")) {
                op = "+";
            } else if (temp.equals("Subtraction")) {
                op = "-";
            }

            if (!result.toString().equals("")) {
                lineInput = new StringBuilder(result);
                result = new StringBuilder();
            }

            lineInput.append(op);

            number_two = number_one;
            tempNumber = new NumberInBaseN(number_one);
            number_one = null;
        }

    }

   /* public static void actionOperator(MouseEvent event) {
        String result = value.toString();
        String temp = ((Button) event.getSource()).getId().replace("button_", "");
        double numberOne = Double.parseDouble(new Converter_p1_10().conv(value.toString(), numberSystem));

        if (temp.equals("Pow_Two")) {
            result += " ^2" + " (" + numberSystem + ") = ";
            value = new StringBuilder(String.valueOf(calculator.power(numberOne)));
            value = new StringBuilder(String.valueOf(new Converter_10_p2().conv(value.toString(), numberSystem)));
        } else {
            result = "1/" + value.toString() + " (" + numberSystem + ") = ";
            value = new StringBuilder(String.valueOf(calculator.reciprocalNumber(numberOne)));
            value = new StringBuilder(String.valueOf(new Converter_10_p2().conv(value.toString(), numberSystem)));
        }

        result = result + value;
        History.data.add(result);

        operationNumber();
        leftOperand = new StringBuilder(value);

    }*/

  /*  private static void operationNumber() {
        String result;

        double numberOne = Double.parseDouble(new Converter_p1_10().conv(leftOperand.toString(), numberSystem));
        double numberTwo = Double.parseDouble(new Converter_p1_10().conv(value.toString(), numberSystem));

        double numberTemp = 0;

        System.out.println(operator);
        if (!operator.equals(" ")) {
            result = leftOperand.toString() + " (" + numberSystem + ") " + operator + " " + value.toString() + " (" + numberSystem + ") =";

            if (operator.contains("*")) {
                numberTemp = calculator.multiply(numberOne, numberTwo);
            } else if (operator.contains("/")) {
                numberTemp = calculator.divide(numberOne, numberTwo);
            } else if (operator.contains("+")) {
                numberTemp = calculator.add(numberOne, numberTwo);
            } else if (operator.contains("-")) {
                numberTemp = calculator.subtract(numberOne, numberTwo);
            }


            value = new StringBuilder(String.valueOf(numberTemp));
            value = new StringBuilder(String.valueOf(new Converter_10_p2().conv(value.toString(), numberSystem)));

            result += value;
            History.data.add(result);

            tempOperator=operator;
            operator = " ";
        }
    }*/

   /* public static void memory(MouseEvent event) {
        String temp = ((Button) event.getSource()).getId().replace("button_", "");

        if (temp.contains("MPlus")) {
            calculatorMemory.memoryPlus(String.valueOf(value));
        } else if (temp.contains("MR")) {
            leftOperand = new StringBuilder();
            value = new StringBuilder(calculatorMemory.memoryRead());
            numberSystem = calculatorMemory.getSystem();
        } else if (temp.contains("MS")) {
            calculatorMemory.memorySave(value.toString());
            calculatorMemory.setSystem(numberSystem);
        } else if (temp.contains("MC")) {
            calculatorMemory = new CalculatorMemory();
        }
    }*/

    public void onResultClicked() {
        if (number_one != null) {
            operationNumber(op);
        } else {
            double numberOne = Double.parseDouble(new Converter_p1_10().conv(number_two.getNumber(), number_two.getSystem()));
            double result = calculator.power(numberOne);

            StringBuilder temp = new StringBuilder(String.valueOf(new Converter_10_p2().conv(String.valueOf(result), systemNumber)));

            number_two.setNumber(temp.toString());
            number_two.setSystem(systemNumber);
            lineInput = new StringBuilder(number_two.getNumber());

            tempNumber = new NumberInBaseN(number_two);
            number_one = number_two;
            number_two = null;
            count++;

        }
    }

    private void operationNumber(String operator) {
        if (number_two == null) {
            number_two = number_one;
            number_one = tempNumber;
        }

        double numberOne;
        double numberTwo = 0;

        if (number_one.getSystem() != Integer.MAX_VALUE) {
            numberTwo = Double.parseDouble(new Converter_p1_10().conv(number_one.getNumber(), number_one.getSystem()));
        }

        numberOne = Double.parseDouble(new Converter_p1_10().conv(number_two.getNumber(), number_two.getSystem()));
        double numberTemp = 0;

        if (operator.contains("*")) {
            numberTemp = calculator.multiply(numberOne, numberTwo);
        } else if (operator.contains("/")) {
            numberTemp = calculator.divide(numberOne, numberTwo);
        } else if (operator.contains("+")) {
            numberTemp = calculator.add(numberOne, numberTwo);
        } else if (operator.contains("-")) {
            numberTemp = calculator.subtract(numberOne, numberTwo);
        }

        tempNumber = new NumberInBaseN(number_one);
        result = new StringBuilder(String.valueOf(numberTemp));
        number_two = null;

        StringBuilder temp = new StringBuilder(String.valueOf(new Converter_10_p2().conv(result.toString(), systemNumber)));
        number_one.setNumber(temp.toString());
        number_one.setSystem(systemNumber);
        result = temp;

    }


    public void waterNumber(MouseEvent event) {
        StringBuilder tempInput;
        if (number_one == null) {
            number_one = new NumberInBaseN();
            tempInput = new StringBuilder();
        } else {
            tempInput = new StringBuilder(number_one.getNumber());
        }

        int temp = Integer.parseInt(((Button) event.getSource()).getId().replace("button_", ""));

        if (lineInput.toString().equals("0")) {
            lineInput = new StringBuilder();
            tempInput.append(temp);
            lineInput.append(temp);
        } else {
            StringBuilder line = new StringBuilder(lineInput);
            line.append(temp);
            tempInput.append(temp);

            lineInput = new StringBuilder(String.valueOf(line).toUpperCase());
        }

        number_one.setNumber(tempInput.toString());
        number_one.setSystem(systemNumber);
    }

    public void onBaskSpace(MouseEvent event) {
        if (lineInput.length() > 0) {
            lineInput.deleteCharAt(lineInput.length() - 1);

            if (lineInput.length() == 0) {
                lineInput.append("0");
            }
        }
    }


    public void onCleanEntry(MouseEvent event) {
        lineInput = new StringBuilder("0");
        result = new StringBuilder();
        systemNumber = 10;
        number_one = null;
        number_two = null;
        op = " ";
    }

    public void onClean(MouseEvent event) {
        if (count % 2 == 0) {
            lineInput = new StringBuilder("0");
            count++;
        } else {
            onCleanEntry(event);
            count = 0;
        }
    }
}
