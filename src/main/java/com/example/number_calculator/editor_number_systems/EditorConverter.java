package com.example.number_calculator.editor_number_systems;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class EditorConverter {
    public static NumberInBaseN number_one = null;
    public static NumberInBaseN number_two = null;
    public static NumberInBaseN number_result = null;
    private static NumberInBaseN number_temp = null;

    public static StringBuilder lineInput = new StringBuilder("0");
    public static StringBuilder lineResult = new StringBuilder();
    public static int system = 10;
    public static String operation = "";
    private static String operationTemp = "";
    private Calculator calculator = new Calculator();

    public void waterSymbol(MouseEvent event) {
        String temp = ((Button) event.getSource()).getId().replace("button_", "");
        inputNumberSymbol(temp);

    }

    public void waterNumber(MouseEvent event) {
        String temp = ((Button) event.getSource()).getId().replace("button_", "");
        inputNumberSymbol(temp);
    }

    public void waterOperator(MouseEvent event) {
        String temp = ((Button) event.getSource()).getId().replace("button_", "");

        if (temp.equals("SignСhange")) {
            if (number_result != null) {
                number_one = new NumberInBaseN(number_result);
                number_one.setNumber(negate(number_one.getNumber()));

                number_result = null;
                number_two = null;

                lineResult = new StringBuilder();
                operation = "";
            } else {
                if (number_two != null) {
                    number_two.setNumber(negate(number_two.getNumber()));
                } else {
                    if (number_one != null) {
                        number_one.setNumber(negate(number_one.getNumber()));
                    }
                }
            }
        } else {
            String inputOperator = operatorDefinition(temp);

            if (number_one != null && !operation.equals("") && number_two != null) {
                if (number_result == null) {
                    onResultClicked();
                    number_one = new NumberInBaseN(number_result);
                    number_result = null;
                } else {
                    number_one = new NumberInBaseN(number_result);
                }

                number_two = new NumberInBaseN();
                number_two.setSystem(system);

                number_result = null;
                operation = inputOperator;
            } else if (number_one == null) {
                operation = "";
            } else {
                if (number_two == null) {
                    number_two = new NumberInBaseN();
                    number_two.setSystem(system);
                }

                if (!operation.equals(inputOperator)) {
                    number_temp = null;
                }
                operation = inputOperator;
            }
        }

        dataInput();
    }

    public void onResultClicked() {
        if (number_two != null && number_one != null && !number_two.getNumber().equals("")) {
            number_result = new NumberInBaseN("0", number_two.getSystem());

            double numberOne;
            double numberTwo = 0;

            if (number_two.getSystem() != Integer.MAX_VALUE) {
                numberTwo = Double.parseDouble(new Converter_p1_10().conv(number_two.getNumber(), number_two.getSystem()));
            }

            numberOne = Double.parseDouble(new Converter_p1_10().conv(number_one.getNumber(), number_one.getSystem()));

            double numberTemp = arithmetic(numberOne, numberTwo);


            if (!String.valueOf(numberTemp).equals("0.0")) {
                number_result.setNumber(String.valueOf(new Converter_10_p2().conv(numberTemp + "", number_result.getSystem())));
            } else {
                number_result.setNumber("0");
            }

            number_one = new NumberInBaseN(number_result);
            lineResult = new StringBuilder(number_result.getNumber());
        } else if (number_one != null && !operation.equals("")) {
            if (number_temp == null) {
                number_temp = new NumberInBaseN(number_one);
            }

            double numberOne = Double.parseDouble(new Converter_p1_10().conv(number_one.getNumber(), number_one.getSystem()));
            number_two = new NumberInBaseN(number_temp);

            double numberTwo = Double.parseDouble(new Converter_p1_10().conv(number_two.getNumber(), number_two.getSystem()));
            double numberTemp = arithmetic(numberOne, numberTwo);

            StringBuilder temp = new StringBuilder(String.valueOf(new Converter_10_p2().conv(String.valueOf(numberTemp), number_one.getSystem())));

            number_one = new NumberInBaseN(temp.toString(), number_one.getSystem());
            number_two = null;

            dataInput();
        }
    }

    public void actionOperator(MouseEvent event) {
        String temp = ((Button) event.getSource()).getId().replace("button_", "");

        String lineTemp = "";
        int systemNumber = 0;

        if (number_result == null) {
            if (number_two == null) {
                if (number_one != null) {
                    lineTemp = number_one.getNumber();
                    systemNumber = number_one.getSystem();
                }
            } else {
                lineTemp = number_two.getNumber();
                systemNumber = number_two.getSystem();
            }
        } else {
            number_one = new NumberInBaseN(number_result);
            number_two = null;
            number_result = null;

            lineTemp = number_one.getNumber();
            systemNumber = number_one.getSystem();
            operation = "";
        }


        double numberOne = Double.parseDouble(new Converter_p1_10().conv(lineTemp, systemNumber));
        double number;

        if (temp.equals("Pow_Two")) {
            number = calculator.power(numberOne);
        } else {
            number = calculator.reciprocalNumber(numberOne);
        }

        StringBuilder resultTemp = new StringBuilder(String.valueOf(new Converter_10_p2().conv(String.valueOf(number), systemNumber)));

        if (number_two != null) {
            number_two.setNumber(resultTemp.toString());
        } else {
            if (number_one != null) {
                number_one.setNumber(resultTemp.toString());
            }
        }

        dataInput();
    }

    public void onBaskSpace(MouseEvent event) {
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
    }

    public void onCleanEntry(MouseEvent event) {
        if (number_two != null && !number_two.getNumber().equals("")) {
            number_two = new NumberInBaseN("",system);
        } else if (number_one != null && !number_one.getNumber().equals("")) {
            number_one = null;
        }

        dataInput();
    }

    private void deleteSymbol(NumberInBaseN number) {
        if (number.getNumber().length() != 0) {
            StringBuilder temp = new StringBuilder(number.getNumber());
            temp.deleteCharAt(temp.length() - 1);
            number.setNumber(temp.toString());
        }

        if (number.getNumber().equals("")) {
            number = null;
        }
    }

    private double arithmetic(double numberOne, double numberTwo) {
        double numberTemp = 0;

        if (operation.contains("*")) {
            numberTemp = calculator.multiply(numberOne, numberTwo);
        } else if (operation.contains("/")) {
            numberTemp = calculator.divide(numberOne, numberTwo);
        } else if (operation.contains("+")) {
            numberTemp = calculator.add(numberOne, numberTwo);
        } else if (operation.contains("-")) {
            numberTemp = calculator.subtract(numberOne, numberTwo);
        }

        return numberTemp;
    }

    private String negate(String temp) {
        StringBuilder tempTime = new StringBuilder(temp);

        if (tempTime.toString().contains("-")) {
            tempTime.deleteCharAt(0);
        } else {
            tempTime.insert(0, "-");
        }

        return tempTime.toString();
    }

    private void inputNumberSymbol(String temp) {
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
                number_one = new NumberInBaseN();
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
        dataInput();

        log();
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

    private void dataInput() {
        StringBuilder result = new StringBuilder();

        if (number_one != null) {
            result.append(number_one.getNumber());
        }

        if (!operation.equals("")) {
            result.append(operation);
        }

        if (number_two != null) {
            if (number_two.getNumber().contains("-")) {
                result.append("(" + number_two.getNumber() + ")");
            } else {
                result.append(number_two.getNumber());
            }
        }

        lineInput = new StringBuilder(result);
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
