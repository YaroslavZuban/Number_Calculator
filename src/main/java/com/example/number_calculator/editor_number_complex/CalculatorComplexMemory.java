package com.example.number_calculator.editor_number_complex;

import java.text.ParseException;

/**
 * Данный класс реализует функциональность сохранения и
 * вычисления значений комплексных чисел с использованием обратной польской записи.
 */
public class CalculatorComplexMemory {
    private PostfixConverter converter = null;
    private PostfixCalculator calc = null;
    private ComplexNumber result = null;
    private String value = "";

    /**
     * добавляет к строке текущего значения value переданное значение addNumber,
     * вычисляет результат и сохраняет его в value
     */
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

    /**
     * сохраняет переданное значение number в переменную value
     */
    public void memorySave(String number) {
        value = number;
    }

    public String getValue() {
        return value;
    }

    /**
     * удаляет текущее значение value.
     */
    public void deleteValue() {
        value = "";
    }
}
