package com.example.number_calculator.editor_number_complex;

import java.text.ParseException;

public class CalculatorComplexMemory {
    private PostfixConverter converter = null;
    private PostfixCalculator calc = null;
    private ComplexNumber result = null;
    private String value = "";

    public void memoryPlus(String addNumber) {
        if (value.equals("")) {
            value = addNumber;
        } else {
            value = value + "+" + addNumber;
        }

        converter = new PostfixConverter(value);

        try {
            calc = new PostfixCalculator(converter.convertToPostfix());
            result = new ComplexNumber(calc.calculate());
            value = result.toString();
        } catch (UnrecognizableElementException | IncorrectTypeException | IncorrectElementException |
                 ParseException e) {
            throw new RuntimeException(e);
        }


    }

    public void memorySave(String number) {
        value = number;
    }

    public String getValue() {
        return value;
    }

    public void deleteValue() {
        value = "";
    }


}
