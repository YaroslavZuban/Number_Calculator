package com.example.number_calculator.editor_number_complex;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * Данный класс представляет рабочую память калькулятора,
 * которая позволяет сохранять результаты вычислений и использовать их в дальнейших операциях.
 */
public class WorkingMemory {
    public static StringBuilder inputLine = new StringBuilder();
    public static StringBuilder resultLine = new StringBuilder();
    private CalculatorComplexMemory memory = new CalculatorComplexMemory();

    /**
     * обрабатывает клик на кнопке памяти, определяет тип операции и выполняет соответствующие действия:
     * memoryPlus - сохранение результата вычислений,
     * memorySave - сохранение значения в память,
     * deleteValue - удаление значения из памяти.
     * получения элемента из памяти
     * @param event
     */
    public void workingMemory(MouseEvent event) {
        String temp = ((Button) event.getSource()).getId().replace("button_", "");

        if (temp.contains("MPlus")) {
            memory.memoryPlus(resultLine.toString());
        } else if (temp.contains("MR")) {
            if (inputLine.toString().equals("")) {
                inputLine = new StringBuilder(memory.getValue());
            } else {
                addDataMemoryOutputLine();
            }
        } else if (temp.contains("MS")) {
            memory.memorySave(resultLine.toString());
        } else if (temp.contains("MC")) {
            memory.deleteValue();
        }
    }

    /**
     * добавляет значение из памяти к строке ввода операции,
     * если последний символ строки является оператором.
     */
    private void addDataMemoryOutputLine() {
        char elementOperation = inputLine.charAt(inputLine.length() - 1);

        if (elementOperation == '/' || elementOperation == '*' ||
                elementOperation == '+' || elementOperation == '-') {
            inputLine.append(memory.getValue());
        }
    }
}
