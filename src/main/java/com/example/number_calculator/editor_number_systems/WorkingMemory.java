package com.example.number_calculator.editor_number_systems;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * отвечает за работу с памятью калькулятора
 */
public class WorkingMemory {
    public static TPNumber number_one;
    public static TPNumber number_two;
    public static TPNumber number_result;
    public static TPNumber number_temp;
    public static String operation;
    public static int system;

    /**
     * который вызывается при нажатии на одну из кнопок памяти (MPlus, MR, MS или MC) и выполняет соответствующие действия.
     *
     * Метод memoryPlus добавляет значение, которое нужно сохранить в памяти, в память калькулятора. Если результат (number_result) уже существует,
     * он добавляется в память, в противном случае, если есть только number_one и операция не задана (operation пустая строка), number_one добавляется в память.
     *
     * Метод memorySave сохраняет значение, которое нужно сохранить в памяти, в память калькулятора. Если результат (number_result) уже существует,
     * он сохраняется в память, в противном случае, если есть только number_one и операция не задана (operation пустая строка), number_one сохраняется в память.
     *
     * Метод getValue возвращает текущее значение в памяти калькулятора.
     *
     * Метод getSystem возвращает текущую систему счисления, которая была использована для сохранения значения в памяти калькулятора.
     *
     * Метод memoryClear очищает память калькулятора.
     */
    private static CalculatorTPNumberMemory calculatorMemory = new CalculatorTPNumberMemory();
    public void memory(MouseEvent event) {
        String temp = ((Button) event.getSource()).getId().replace("button_", "");

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
}
