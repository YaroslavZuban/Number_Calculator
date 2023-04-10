package com.example.number_calculator.editor_simple_fraction;

import com.example.number_calculator.Editor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class EditorFraction implements Editor {
    public static Fraction number_one = null;
    public static Fraction number_two = null;
    public static Fraction number_result = null;
    private static Fraction number_temp = null;
    public static StringBuilder lineInput = new StringBuilder();
    public static StringBuilder lineResult = new StringBuilder();
    public static String operation = "";
    private ProcessorFraction processorFraction = new ProcessorFraction(number_one, number_two, number_result,
            number_temp, operation);
    private InputFraction input = new InputFraction(number_one, number_two, number_result, number_temp, operation);
    private static CalculatorFractionMemory calculatorMemory = new CalculatorFractionMemory();

    @Override
    public void entrySymbol(MouseEvent event) {

    }

    @Override
    public void entryNumber(MouseEvent event) {
        String temp = ((Button) event.getSource()).getId().replace("button_", "");
        inputNumber(temp);
    }

    @Override
    public void entryOperator(MouseEvent event) {
        String temp = ((Button) event.getSource()).getId().replace("button_", "");

        input.entryOperator(temp);
        getInput();
        setResult();

        dataInput();
    }

    @Override
    public void onResultClicked() {
        getResult();

        Fraction temp = number_one;

        processorFraction.onResultClicked();
        getResult();

        if (number_result != null) {
            number_one = temp;
        }

        dataInput();
        setInput();

    }

    @Override
    public void actionOperator(MouseEvent event) {
        String temp = ((Button) event.getSource()).getId().replace("button_", "");
        processorFraction.actionOperator(temp);

        getResult();
        setInput();

        dataInput();
    }

    @Override
    public void onBackSpace(MouseEvent event) {
        String temp;

        if (number_two != null) {
            if (number_two.getDenominator() != Integer.MAX_VALUE && number_two.getDenominator() != Integer.MIN_VALUE) {
                temp = number_two.getDenominator().toString();
                temp = deleteSymbol(temp);

                if (temp.equals("")) {
                    number_two.setDenominator(Integer.MIN_VALUE);
                } else {
                    number_two.setDenominator(Integer.parseInt(temp));
                }

            } else if (number_two.getNumerator() != Integer.MAX_VALUE && number_two.getNumerator() != Integer.MIN_VALUE) {
                number_two.setDenominator(Integer.MIN_VALUE);

                temp = number_two.getNumerator().toString();
                temp = deleteSymbol(temp);

                if (temp.equals("")) {
                    number_two.setNumerator(Integer.MAX_VALUE);
                } else {
                    number_two.setNumerator(Integer.parseInt(temp));
                }
            }
        } else if (!operation.equals("")) {
            operation = "";
        } else {
            if (number_one.getDenominator() != Integer.MAX_VALUE && number_one.getDenominator() != Integer.MIN_VALUE) {
                temp = number_one.getDenominator().toString();
                temp = deleteSymbol(temp);

                if (temp.equals("")) {
                    number_one.setDenominator(Integer.MIN_VALUE);
                } else {
                    number_one.setDenominator(Integer.parseInt(temp));
                }

            } else if (number_one.getNumerator() != Integer.MAX_VALUE && number_one.getNumerator() != Integer.MIN_VALUE) {
                number_one.setDenominator(Integer.MIN_VALUE);

                temp = number_one.getNumerator().toString();
                temp = deleteSymbol(temp);

                if (temp.equals("")) {
                    number_one.setNumerator(Integer.MAX_VALUE);
                } else {
                    number_one.setNumerator(Integer.parseInt(temp));
                }
            }
        }

        setResult();
        setInput();

        dataInput();
    }

    @Override
    public void onClean(MouseEvent event) {
        lineInput = new StringBuilder("0");
        lineResult = new StringBuilder();

        number_one = null;
        number_two = null;
        number_result = null;

        operation = "";

        setResult();
        setInput();
    }

    @Override
    public void onCleanEntry(MouseEvent event) {
        if (number_two != null && number_two.getNumerator() != Integer.MAX_VALUE) {
            number_two.setNumerator(Integer.MAX_VALUE);
            number_two.setDenominator(Integer.MIN_VALUE);
        } else if (!operation.equals("")) {
            operation = "";
            number_two = null;
        } else if (number_one != null) {
            number_one = null;
        }

        setResult();
        setInput();

        dataInput();
    }

    @Override
    public void memory(MouseEvent event) {
        String temp = ((Button) event.getSource()).getId().replace("button_", "");

        if (temp.contains("MPlus")) {
            if (number_result != null && findMaxMin(number_result)) {
                calculatorMemory.memoryPlus(number_result);
            } else {
                if (number_one != null && operation.equals("") && findMaxMin(number_one)) {
                    calculatorMemory.memoryPlus(number_one);
                }
            }
        } else if (temp.contains("MR")) {
            lineInput = new StringBuilder();
            lineResult = new StringBuilder();

            number_two = null;
            number_result = null;

            operation = "";

            if (number_one != null && !findMaxMin(number_one)) {
                number_one = calculatorMemory.getValue();
            } else if (number_two != null && !operation.equals("") && !findMaxMin(number_two)) {
                number_two = calculatorMemory.getValue();
            }

            dataInput();
        } else if (temp.contains("MS")) {

            if (number_result != null && findMaxMin(number_result)) {
                calculatorMemory.memorySave(number_result);
            } else {
                if (number_one != null && operation.equals("") && findMaxMin(number_one)) {
                    calculatorMemory.memorySave(number_one);
                }
            }

        } else if (temp.contains("MC")) {
            calculatorMemory = new CalculatorFractionMemory();
        }
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

    private void inputNumber(String temp) {
        if (number_one != null && number_two != null && number_result != null) {
            number_two = null;
            number_one = null;
            number_result = null;

            operation = "";

            lineInput = new StringBuilder();
            lineResult = new StringBuilder();

            setResult();
            setInput();
        }

        input.inputNumber(temp);

        getInput();
        setResult();

        dataInput();
    }

    private boolean findMaxMin(Fraction fraction_one) {
        if (fraction_one.getNumerator() != Integer.MAX_VALUE && fraction_one.getNumerator() != Integer.MIN_VALUE &&
                fraction_one.getDenominator() != Integer.MAX_VALUE && fraction_one.getDenominator() != Integer.MIN_VALUE)
            return true;


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
        result.delete(0, result.length());

        if (number_result != null) {
            if (number_result.getNumerator() != Integer.MAX_VALUE && number_result.getNumerator() != Integer.MIN_VALUE) {
                result.append(addNumberPrintf(number_result.getNumerator()));
            }

            if (number_result.getDenominator() != Integer.MAX_VALUE && number_result.getDenominator() != Integer.MIN_VALUE) {
                result.append("|");
                result.append(addNumberPrintf(number_result.getDenominator()));
            }
        }

        lineResult = new StringBuilder(result);
    }

    public String addNumberPrintf(int number) {
        if (number != Integer.MAX_VALUE && number != Integer.MIN_VALUE) {
            return "" + number;
        }

        return "";
    }

    private String deleteSymbol(String line) {
        if (line.length() != 0) {
            StringBuilder temp = new StringBuilder(line);
            temp.deleteCharAt(temp.length() - 1);
            line = String.valueOf(temp);
        }

        return line;
    }

    private void setResult() {
        ProcessorFraction.setNumber_one(number_one);
        ProcessorFraction.setNumber_two(number_two);
        ProcessorFraction.setNumber_result(number_result);
        ProcessorFraction.setNumber_temp(number_temp);
        ProcessorFraction.setOperation(operation);
    }

    private void getResult() {
        number_one = ProcessorFraction.getNumber_one();
        number_two = ProcessorFraction.getNumber_two();
        number_result = ProcessorFraction.getNumber_result();
        number_temp = ProcessorFraction.getNumber_temp();
        operation = ProcessorFraction.getOperation();
    }

    private void setInput() {
        InputFraction.setNumber_one(number_one);
        InputFraction.setNumber_two(number_two);
        InputFraction.setNumber_result(number_result);
        InputFraction.setNumber_temp(number_temp);
        InputFraction.setOperation(operation);
    }

    private void getInput() {
        number_one = InputFraction.getNumber_one();
        number_two = InputFraction.getNumber_two();
        number_result = InputFraction.getNumber_result();
        number_temp = InputFraction.getNumber_temp();
        operation = InputFraction.getOperation();
    }
}
