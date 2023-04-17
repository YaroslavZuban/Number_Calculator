package com.example.number_calculator.editor_comprehensive;

import com.example.number_calculator.Operator;
import com.example.number_calculator.SignTranslation;
import com.example.number_calculator.editor_number_systems.ProcessorConverter;
import com.example.number_calculator.editor_number_systems.TPNumber;

import java.text.ParseException;
import java.util.Vector;

public class ProcessorComplex {
    private static StringBuilder inputLine = new StringBuilder();
    private static StringBuilder tempLine = new StringBuilder();
    private static StringBuilder resultLine = new StringBuilder();
    private static StringBuilder operation = new StringBuilder();

    private PostfixConverter converter = null;
    private PostfixCalculator calc = null;
    private ComplexNumber result = null;

    public ProcessorComplex(StringBuilder inputLine, StringBuilder resultLine, StringBuilder tempLine) {
        this.inputLine = inputLine;
        this.resultLine = resultLine;
        this.tempLine = tempLine;
    }

    public void inputSymbol(String temp){
        if(temp.equals("OpenBracket")){
            inputLine.append("(");
        }else{
            inputLine.append(")");
        }
    }
    public void result() throws IncorrectTypeException, UnrecognizableElementException, IncorrectElementException, ParseException {
        if (isSign(inputLine)) {
            if (tempLine.toString().equals("")) {
                tempLine = new StringBuilder(inputLine);
                tempLine.deleteCharAt(tempLine.length() - 1);
            }

            StringBuilder temp = new StringBuilder("(" + inputLine.deleteCharAt(inputLine.length() - 1) + ")");

            temp.append(operation);
            temp.append("(" + tempLine + ")");

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
        }
    }

    public void input(String temp) {
        if (!resultLine.toString().equals("")) {
            resultLine = new StringBuilder();
            inputLine = new StringBuilder();
            tempLine = new StringBuilder();
        }

        if (inputLine.toString().equals("") && temp.equals("Point")) {
            inputLine.append("0,");
        } else {
            if (!tempLine.toString().equals("")) {
                tempLine = new StringBuilder();
            }

            if (temp.equals("OpenBracket")) {
                inputLine.append("(");
            } else if (temp.equals("CloseBracket")) {
                inputLine.append(")");
            } else if (temp.equals("Point")) {
                if (isSign(inputLine)) {
                    inputLine.append("0,");
                } else {
                    inputLine.append(",");
                }
            } else {
                inputLine.append(temp);
            }
        }
    }

    public void inputOperation(String temp) throws IncorrectTypeException, UnrecognizableElementException, IncorrectElementException {
        if (temp.equals("SignChange")) {
            converter = new PostfixConverter(inputLine.toString());
            calc = new PostfixCalculator(converter.convertToPostfix());

            inputLine = new StringBuilder(Negate.negateLastNumber(inputLine.toString()));
        } else {
            String inputOperator = Operator.operatorDefinition(temp);
            operation = new StringBuilder(inputOperator);

            if (!tempLine.toString().equals("")) {
                tempLine = new StringBuilder();
            }

            if (isSign(inputLine)) {
                inputLine.deleteCharAt(inputLine.length() - 1);
                inputLine.append(inputOperator);
            } else {

                if (!resultLine.toString().equals("")) {
                    inputLine = new StringBuilder(resultLine);
                    resultLine = new StringBuilder();
                }

                inputLine.append(operation);
                tempLine = new StringBuilder();
            }
        }
    }

    public void actionOperator(String temp) throws IncorrectTypeException, UnrecognizableElementException, IncorrectElementException {
        converter = new PostfixConverter(inputLine.toString());
        calc = new PostfixCalculator(converter.convertToPostfix());
        Vector postfixVector = calc.getPostfixVector();
        String numberSquared="";
        int indexNumberSquared=0;

        for (int i = postfixVector.size() - 1; i >= 0; i --) {
            String operation = postfixVector.get(i).toString();

            if(!operation.equals("-") && !operation.equals("+") &&
                    !operation.equals("/") &&!operation.equals("*") ){
                numberSquared=operation;
                indexNumberSquared=i;
                break;
            }
        }

        if (temp.equals("Pow_Two")) {
            numberSquared=Squaring.squareNumber(numberSquared);
        } else{
            numberSquared=ComplexNumberOneDivide.divideOneByNumber(numberSquared);
        }

        postfixVector.set(indexNumberSquared,numberSquared);
         inputLine=new StringBuilder(MathExpressionParser.arrayToString(postfixVector));
    }


    public static StringBuilder getInputLine() {
        return inputLine;
    }

    public static void setInputLine(StringBuilder inputLine) {
        ProcessorComplex.inputLine = inputLine;
    }

    public static StringBuilder getTempLine() {
        return tempLine;
    }

    public static void setTempLine(StringBuilder tempLine) {
        ProcessorComplex.tempLine = tempLine;
    }

    public static StringBuilder getResultLine() {
        return resultLine;
    }

    public static void setResultLine(StringBuilder resultLine) {
        ProcessorComplex.resultLine = resultLine;
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
