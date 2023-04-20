package com.example.number_calculator.editor_simple_fraction;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class WorkingMemory {
    public static Fraction number_one = null;
    public static Fraction number_two = null;
    public static Fraction number_result = null;
    public static Fraction number_temp = null;
    public static String operation = "";
    CalculatorFractionMemory calculatorMemory=new CalculatorFractionMemory();
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
