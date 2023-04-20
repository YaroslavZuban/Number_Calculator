package com.example.number_calculator.editor_number_complex;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class WorkingMemory {
    public static StringBuilder inputLine = new StringBuilder();
    public static StringBuilder resultLine = new StringBuilder();
    private CalculatorComplexMemory memory = new CalculatorComplexMemory();

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

    private void addDataMemoryOutputLine() {
        char elementOperation = inputLine.charAt(inputLine.length() - 1);

        if (elementOperation == '/' || elementOperation == '*' ||
                elementOperation == '+' || elementOperation == '-') {
            inputLine.append(memory.getValue());
        }
    }
}
