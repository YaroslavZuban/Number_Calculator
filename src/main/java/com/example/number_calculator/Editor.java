package com.example.number_calculator;

import com.example.number_calculator.editor_number_systems.Calculator;
import com.example.number_calculator.editor_number_systems.Converter_10_p2;
import com.example.number_calculator.editor_number_systems.Converter_p1_10;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class Editor {
    public static String operator = " ";
    private static String tempOperator=" ";
    public static int numberSystem = 10;
    public static CalculatorMemory calculatorMemory = new CalculatorMemory();

    public static StringBuilder value = new StringBuilder();
    private static StringBuilder tempValue = new StringBuilder();
    private static Calculator calculator = new Calculator();

    public static StringBuilder leftOperand = new StringBuilder();
    private static boolean isOperationPerformed=false;
    private static int count = 0;

    public static void waterSymbol(MouseEvent event) {
        String temp = ((Button) event.getSource()).getId().replace("button_", "");
        String point = ".";

        if (value.toString().equals("0")) {
            if (temp.equals("Point")) {
                value = new StringBuilder("0" + point);
            } else {
                value = new StringBuilder(temp);
            }
        } else {
            StringBuilder line = new StringBuilder(value);

            if (temp.equals("Point") && line.indexOf(".") == -1) {
                line.append(point);
                value = line;
            } else if (!temp.equals("Point")) {
                line.append(temp);
                value = line;
            }
        }

    }

    public static void waterOperator(MouseEvent event) {
        String temp = ((Button) event.getSource()).getId().replace("button_", "");


        System.out.println(tempValue);
        System.out.println(tempOperator);

        if(!isOperationPerformed){
            tempValue=new StringBuilder(value.toString());
            tempOperator=operator;

            operationNumber();
            operator = " ";

        }

        if (temp.equals("Result")) {
            if (!isOperationPerformed) {

                operationNumber();
                leftOperand = new StringBuilder(value.toString());

                isOperationPerformed = true;
            } else {
                leftOperand=new StringBuilder(value);
                value=new StringBuilder(tempValue);

                operator=tempOperator;

                operationNumber();
                leftOperand = new StringBuilder(value.toString());
            }

        } else if (temp.equals("SignСhange")) {
            isOperationPerformed=false;

            if (value.toString().contains("-")) {
                value.deleteCharAt(0);
            } else {
                value.insert(0, "-");
            }

        } else {
            if (temp.equals("Multiplication")) {
                if (operator.equals("*")) {
                    tempValue = new StringBuilder(value);
                    operationNumber();
                } else {
                    operator = "*";
                    tempOperator=operator;

                }

                leftOperand = new StringBuilder(value);
            } else if (temp.equals("Division")) {
                if (operator.equals("/")) {
                    tempValue = new StringBuilder(value);
                    operationNumber();
                } else {
                    operator = "/";
                    tempOperator=operator;
                }

                leftOperand = new StringBuilder(value);
            } else if (temp.equals("Addition")) {
                if (operator.equals("+")) {
                    tempValue = new StringBuilder(value);
                    operationNumber();
                } else {
                    operator = "+";
                    tempOperator=operator;
                }

                leftOperand = new StringBuilder(value);
            } else if (temp.equals("Subtraction")) {
                if (operator.equals("-")) {
                    tempValue = new StringBuilder(value);
                    operationNumber();
                } else {
                    operator = "-";
                    tempOperator=operator;
                }

                leftOperand = new StringBuilder(value);
            }

            value = new StringBuilder();
            isOperationPerformed=false;
        }
    }

    public static void actionOperator(MouseEvent event) {
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

    }

    private static void operationNumber() {
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
    }

    public static void memory(MouseEvent event) {
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
    }

    public static void waterNumber(MouseEvent event) {
        int temp = Integer.parseInt(((Button) event.getSource()).getId().replace("button_", ""));

        if (value.toString().equals("0")) {
            value = new StringBuilder();
            value.append(temp);
        } else {
            StringBuilder line = new StringBuilder(value);
            line.append(temp);

            value = new StringBuilder(String.valueOf(line).toUpperCase());
        }
    }

    public static void onBaskSpace(MouseEvent event) {
        if (value.length() > 0) {
            value.deleteCharAt(value.length() - 1);

            if (value.length() == 0) {
                value.append("0");
            }
        }
    }


    public static void onCleanEntry(MouseEvent event) {
        value = new StringBuilder("0");
        tempValue = new StringBuilder();
        numberSystem = 10;
        operator = " ";
    }

    public static void onClean(MouseEvent event) {
        if (count % 2 == 0) {
            value = new StringBuilder("0");
            count++;
        } else {
            onCleanEntry(event);
            count = 0;
        }

    }
}
