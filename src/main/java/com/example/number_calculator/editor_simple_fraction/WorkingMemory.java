package com.example.number_calculator.editor_simple_fraction;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * отвечает за работу с памятью калькулятора для операций с дробями.
 */
public class WorkingMemory {
    public static Fraction number_one = null;
    public static Fraction number_two = null;
    public static Fraction number_result = null;
    public static Fraction number_temp = null;
    public static String operation = "";
    CalculatorFractionMemory calculatorMemory=new CalculatorFractionMemory();

    /**
     * функция, вызываемая при нажатии на кнопки, отвечающие за работу с памятью калькулятора.
     * Она получает идентификатор кнопки, убирает из него префикс "button_", и в
     * зависимости от полученного значения выполняет определенные действия:
     *
     * MPlus - если number_result не равен null и number_result содержит корректное значение,
     * то значение number_result добавляется в память калькулятора с помощью метода memoryPlus
     * объекта класса CalculatorFractionMemory. В противном случае, если number_one не равен null
     * и operation пустая строка, и number_one содержит корректное значение, то значение number_one
     * добавляется в память калькулятора.
     *
     * MR - если number_one не равен null и number_one не содержит корректное значение,
     * то в number_one записывается значение, полученное с помощью метода getValue объекта CalculatorFractionMemory.
     * Если number_two не равен null, operation не пустая строка и number_two не содержит корректное значение,
     * то в number_two записывается значение, полученное с помощью метода getValue объекта CalculatorFractionMemory.
     *
     * MS - если number_result не равен null и number_result содержит корректное значение,
     * то значение number_result сохраняется в памяти калькулятора с помощью метода memorySave
     * объекта CalculatorFractionMemory. В противном случае, если number_one не равен null и
     * operation пустая строка, и number_one содержит корректное значение, то значение number_one
     * сохраняется в памяти калькулятора.
     *
     * MC - создает новый объект CalculatorFractionMemory, тем самым очищая память калькулятора.
     */
    public void memory(MouseEvent event) {
        String temp = ((Button) event.getSource()).getId().replace("button_", "");
        if (temp.contains("MPlus")) {
            if (number_result != null && FractionValidator.findMaxMin(number_result)) {
                calculatorMemory.memoryPlus(number_result);
            } else {
                if (number_one != null && operation.equals("") && FractionValidator.findMaxMin(number_one)) {
                    calculatorMemory.memoryPlus(number_one);
                }
            }
        } else if (temp.contains("MR")) {
            if (number_one != null && !FractionValidator.findMaxMin(number_one)) {
                number_one = calculatorMemory.getValue();
            } else if (number_two != null && !operation.equals("") && !FractionValidator.findMaxMin(number_two)) {
                number_two = calculatorMemory.getValue();
            }
        } else if (temp.contains("MS")) {
            if (number_result != null && FractionValidator.findMaxMin(number_result)) {
                calculatorMemory.memorySave(number_result);
            } else {
                if (number_one != null && operation.equals("") && FractionValidator.findMaxMin(number_one)) {
                    calculatorMemory.memorySave(number_one);
                }
            }

        } else if (temp.contains("MC")) {
            calculatorMemory = new CalculatorFractionMemory();
        }
    }
}
