package com.example.number_calculator.editor_number_complex;

import com.example.number_calculator.History;
import com.example.number_calculator.Operator;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Vector;

public class ProcessorComplex {
    public static StringBuilder inputLine = new StringBuilder();
    public static StringBuilder tempLine = new StringBuilder();
    public static StringBuilder resultLine = new StringBuilder();
    public static StringBuilder operation = new StringBuilder();
    public static StringBuilder functionResult = new StringBuilder();
    private CalculatorComplexMemory memory = new CalculatorComplexMemory();
    private PostfixConverter converter = null;
    private PostfixCalculator calc = null;
    private ComplexNumber result = null;

    public void result() throws IncorrectTypeException, UnrecognizableElementException, IncorrectElementException, ParseException {
        if (isSign(inputLine)) {
            if (tempLine.toString().equals("")) {
                tempLine = new StringBuilder(inputLine);
                tempLine.deleteCharAt(tempLine.length() - 1);
            }

            StringBuilder temp = new StringBuilder("(" + inputLine.deleteCharAt(inputLine.length() - 1) + ")");

            temp.append(operation);
            temp.append(tempLine);

            converter = new PostfixConverter(temp.toString());
            calc = new PostfixCalculator(converter.convertToPostfix());
            result = new ComplexNumber(calc.calculate());

            inputLine = new StringBuilder(result.toString());
            inputLine.append(operation);
        } else {
            converter = new PostfixConverter(inputLine.toString());
            calc = new PostfixCalculator(converter.convertToPostfix());
            result = new ComplexNumber(calc.calculate());
            resultLine = new StringBuilder(result.toString());
            History.data.add(inputLine + " = " + resultLine);
        }
    }

    public void actionOperator(MouseEvent event) throws IncorrectTypeException, UnrecognizableElementException, IncorrectElementException {
        String temp = ((Button) event.getSource()).getId().replace("button_", "");
        converter = new PostfixConverter(inputLine.toString());

        calc = new PostfixCalculator(converter.convertToPostfix());
        Vector postfixVector = calc.getPostfixVector();

        StringBuilder number = new StringBuilder();
        StringBuilder initialValue = new StringBuilder();

        for (int i = postfixVector.size() - 1; i >= 0; i--) {
            String operation = postfixVector.get(i).toString();

            if (!operation.equals("-") && !operation.equals("+") &&
                    !operation.equals("/") && !operation.equals("*")) {
                initialValue = new StringBuilder(operation);
                break;
            }
        }

        if (temp.equals("Pow_Two")) {
            number = new StringBuilder(Squaring.squareNumber(initialValue.toString()));
        } else if (temp.equals("OneDivisionX")) {
            number = new StringBuilder(ComplexNumberOneDivide.divideOneByNumber(initialValue.toString()));
        } else {
            number = new StringBuilder(Negate.negateLastNumber(initialValue.toString()));
        }

        if (initialValue.charAt(0) == '-') {
            initialValue.insert(0, "(");
            initialValue.append(")");
        }

        int index = inputLine.lastIndexOf(initialValue.toString());

        if (index != -1) {
            inputLine.delete(index, index + initialValue.length());
            inputLine.insert(index, number);
        }
    }

    public void mdl() throws ParseException {
        if (!resultLine.toString().equals("")) {
            ComplexNumber complexNumber = new ComplexNumber(resultLine.toString());
            functionResult = new StringBuilder(String.valueOf(complexNumber.Mdl()));
        }
    }

    public void cnd() throws ParseException {
        if (!resultLine.toString().equals("")) {
            ComplexNumber complexNumber = new ComplexNumber(resultLine.toString());
            functionResult = new StringBuilder(String.valueOf(complexNumber.cnd()));
        }
    }

    public void cnr() throws ParseException {
        if (!resultLine.toString().equals("")) {
            ComplexNumber complexNumber = new ComplexNumber(resultLine.toString());
            functionResult = new StringBuilder(String.valueOf(complexNumber.cnr()));
        }
    }

    public void root(int n) throws ParseException {
        if (!resultLine.toString().equals("")) {
            ComplexNumber complexNumber = new ComplexNumber(resultLine.toString());
            functionResult = new StringBuilder(Arrays.toString(complexNumber.Root(n)));
        }
    }

    public boolean isSign(StringBuilder line) {
        int end = line.length() - 1;

        if (line.charAt(end) == '+' || line.charAt(end) == '*' ||
                line.charAt(end) == '-' || line.charAt(end) == '/') {
            return true;
        }

        return false;
    }
}
