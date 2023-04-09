package com.example.number_calculator.editor_number_systems;

import com.example.number_calculator.SignTranslation;
import com.example.number_calculator.Operator;

public class Input {
    private static TPNumber number_one;
    private static TPNumber number_two;
    private static TPNumber number_result;
    private static TPNumber number_temp;
    private static String operation;
    private static int system;

    public Input(TPNumber number_one, TPNumber number_two, TPNumber number_result,
                 String operation, int system) {
        this.number_one = number_one;
        this.number_two = number_two;
        this.number_result = number_result;
        this.operation = operation;
        this.system = system;
    }

    public static TPNumber getNumber_one() {
        return number_one;
    }

    public static void setNumber_one(TPNumber number_one) {
        Input.number_one = number_one;
    }

    public static TPNumber getNumber_two() {
        return number_two;
    }

    public static void setNumber_two(TPNumber number_two) {
        Input.number_two = number_two;
    }

    public static TPNumber getNumber_result() {
        return number_result;
    }

    public static void setNumber_result(TPNumber number_result) {
        Input.number_result = number_result;
    }

    public static TPNumber getNumber_temp() {
        return number_temp;
    }

    public static void setNumber_temp(TPNumber number_temp) {
        Input.number_temp = number_temp;
    }

    public static String getOperation() {
        return operation;
    }

    public static void setOperation(String operation) {
        Input.operation = operation;
    }

    public static int getSystem() {
        return system;
    }

    public static void setSystem(int system) {
        Input.system = system;
    }

    public void inputNumberSymbol(String temp) {
        StringBuilder line = null;

        if (number_two == null) {
            if (number_one == null) {
                number_one = new TPNumber();
                line = new StringBuilder();
            } else {
                line = new StringBuilder(number_one.getNumber());
            }
        } else {
            line = new StringBuilder(number_two.getNumber());
            number_two.setSystem(system);
        }

        if (line.toString().equals("0") || line.toString().equals("")) {
            if (temp.equals("Point")) {
                if (line.toString().equals("0")) {
                    line.append(".");
                } else {
                    line.append("0.");
                }
            } else {
                line.append(temp);
            }
        } else {
            if (temp.equals("Point") && !line.toString().contains(".")) {
                line.append(".");
            } else {
                if (!temp.equals("Point")) {
                    line.append(temp);
                }
            }
        }

        addNumberSymbol(line.toString());
    }

    public void inputOperator(String temp, StringBuilder lineResult) {
        if (temp.equals("SignСhange")) {
            if (number_result != null) {
                number_one = new TPNumber(number_result);
                number_one.setNumber(SignTranslation.negate(number_one.getNumber()));

                number_result = null;
                number_two = null;

                lineResult.delete(0, lineResult.length() - 1);
                operation = "";
            } else {
                if (number_two != null) {
                    number_two.setNumber(SignTranslation.negate(number_two.getNumber()));
                } else {
                    if (number_one != null) {
                        number_one.setNumber(SignTranslation.negate(number_one.getNumber()));
                    }
                }
            }
        } else {
            String inputOperator = Operator.operatorDefinition(temp);

            if (number_one != null && !operation.equals("") && number_two != null && !number_two.getNumber().equals("")) {
                if (number_result == null) {
                    ProcessorConverter result = new ProcessorConverter(number_one, number_two, number_result, number_temp, operation, system);
                    result.onResultClicked(lineResult);
                    getResult();

                    number_one = new TPNumber(number_result);
                    number_result = null;
                } else {
                    number_one = new TPNumber(number_result);
                }

                number_two = new TPNumber();
                number_two.setSystem(system);

                number_result = null;

                operation = inputOperator;
            } else if (number_one != null && !operation.equals(inputOperator) &&
                    number_two != null && number_two.getNumber().equals("")) {
                operation = inputOperator;

                if (number_two == null) {
                    number_two = new TPNumber();
                    number_two.setSystem(system);
                }

            } else if (number_one == null) {
                operation = "";
            } else {
                if (number_two == null) {
                    number_two = new TPNumber();
                    number_two.setSystem(system);
                }

                if (!operation.equals(inputOperator)) {
                    number_temp = null;
                }

                operation = inputOperator;
            }
        }
    }

    private void setResult(){
        ProcessorConverter.setNumber_one(number_one);
        ProcessorConverter.setNumber_two(number_two);
        ProcessorConverter.setNumber_result(number_result);
        ProcessorConverter.setNumber_temp(number_temp);
        ProcessorConverter.setOperation(operation);
        ProcessorConverter.setSystem(system);
    }

    private void getResult(){
        number_one = ProcessorConverter.getNumber_one();
        number_two = ProcessorConverter.getNumber_two();
        number_result = ProcessorConverter.getNumber_result();
        number_temp = ProcessorConverter.getNumber_temp();
        system = ProcessorConverter.getSystem();
        operation = ProcessorConverter.getOperation();
    }


    private void addNumberSymbol(String line) {
        if (number_two == null) {
            if (number_one != null) {
                number_one.setNumber(line);
                number_one.setSystem(system);
            }
        } else {
            number_two.setNumber(line);
            number_two.setSystem(system);
        }
    }

}
