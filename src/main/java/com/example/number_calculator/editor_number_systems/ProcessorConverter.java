package com.example.number_calculator.editor_number_systems;

import com.example.number_calculator.History;

public class ProcessorConverter {

    private static TPNumber number_one;
    private static TPNumber number_two;
    private static TPNumber number_result;
    private static TPNumber number_temp;
    private static String operation;
    private static int system;

    private static CalculatorTPNumberMemory calculatorMemory = new CalculatorTPNumberMemory();

    public ProcessorConverter(TPNumber number_one, TPNumber number_two, TPNumber number_result,
                              TPNumber number_temp, String operation, int system) {
        this.number_one = number_one;
        this.number_two = number_two;
        this.number_result = number_result;
        this.number_temp = number_temp;
        this.operation = operation;
        this.system = system;
    }

    public void onResultClicked(StringBuilder lineResult) {

        if (number_two != null && number_one != null && !number_two.getNumber().equals("")) {

            number_result = new TPNumber("0", number_two.getSystem());

            if (number_two.getSystem() != Integer.MAX_VALUE) {
                NumberSystemChecker.isNumberValidInBase(number_two.getNumber(), number_two.getSystem());
            }

            NumberSystemChecker.isNumberValidInBase(number_one.getNumber(), number_one.getSystem());

            number_result = Calculator.arithmetic(number_one, number_two, operation);


                /*if (String.valueOf(number_result.getNumber()).equals("0.0")) {
                    number_result.setNumber(String.valueOf(new Converter_10_p2().conv(numberTemp + "", number_result.getSystem())));
                } else {
                    number_result.setNumber("0");
                }*/

            String history = number_one.getNumber() + "(" + number_one.getSystem() + ")" + operation +
                    number_two.getNumber() + "(" + number_two.getSystem() + ") =";

            number_one = new TPNumber(number_result);
            lineResult = new StringBuilder(number_result.getNumber());

            History.data.add(history +
                    number_result.getNumber() + "(" + number_result.getSystem() + ")");

        } else if (number_one != null && !operation.equals("")) {
            if (number_temp == null) {
                number_temp = new TPNumber(number_one);
            }

            NumberSystemChecker.isNumberValidInBase(number_one.getNumber(), number_one.getSystem());
            number_two = new TPNumber(number_temp);

            NumberSystemChecker.isNumberValidInBase(number_two.getNumber(), number_two.getSystem());

            number_one = Calculator.arithmetic(number_one, number_two, operation);

            number_two = null;
        }

    }

    public void memory(String temp) {
        if (temp.contains("MPlus")) {
            if (number_result != null) {
                calculatorMemory.memoryPlus(number_result);
            } else {
                if (number_one != null && operation.equals("")) {
                    calculatorMemory.memoryPlus(number_one);
                }
            }
        } else if (temp.contains("MR")) {
            if (number_one != null && !operation.equals("")) {
                number_two = new TPNumber(calculatorMemory.getValue());
                system = calculatorMemory.getSystem();
            } else if (number_one == null) {
                number_one = new TPNumber(calculatorMemory.getValue());
                system = calculatorMemory.getSystem();
            }
        } else if (temp.contains("MS")) {
            if (number_result != null) {
                calculatorMemory.memorySave(number_result);
            } else {
                if (number_one != null && operation.equals("")) {
                    calculatorMemory.memorySave(number_one);
                }
            }
        } else if (temp.contains("MC")) {
            calculatorMemory = new CalculatorTPNumberMemory();
        }
    }

    public void actionOperator(String temp) {
        TPNumber tempNumber = null;

        if (number_result == null) {
            if (number_two == null) {
                if (number_one != null) {
                    tempNumber = number_one;
                }
            } else {
                tempNumber = number_two;
            }
        } else {
            number_one = new TPNumber(number_result);
            number_two = null;
            number_result = null;

            tempNumber = number_one;
            operation = "";
        }

        if (temp.equals("Pow_Two")) {
            tempNumber = tempNumber.power(2);
        } else {
            tempNumber = tempNumber.reciprocalNumber();
        }


        if (number_two != null) {
            number_two = tempNumber;
        } else {
            if (number_one != null) {
                number_one = tempNumber;
            }
        }
    }

    public static TPNumber getNumber_one() {
        return number_one;
    }

    public static void setNumber_one(TPNumber number_one) {
        ProcessorConverter.number_one = number_one;
    }

    public static TPNumber getNumber_two() {
        return number_two;
    }

    public static void setNumber_two(TPNumber number_two) {
        ProcessorConverter.number_two = number_two;
    }

    public static TPNumber getNumber_result() {
        return number_result;
    }

    public static void setNumber_result(TPNumber number_result) {
        ProcessorConverter.number_result = number_result;
    }

    public static TPNumber getNumber_temp() {
        return number_temp;
    }

    public static void setNumber_temp(TPNumber number_temp) {
        ProcessorConverter.number_temp = number_temp;
    }

    public static String getOperation() {
        return operation;
    }

    public static void setOperation(String operation) {
        ProcessorConverter.operation = operation;
    }

    public static int getSystem() {
        return system;
    }

    public static void setSystem(int system) {
        ProcessorConverter.system = system;
    }

}
