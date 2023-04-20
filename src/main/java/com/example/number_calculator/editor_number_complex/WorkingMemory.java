package com.example.number_calculator.editor_number_complex;

public class WorkingMemory {
    private static StringBuilder inputLine = new StringBuilder();
    private static StringBuilder resultLine = new StringBuilder();
    private CalculatorComplexMemory memory = new CalculatorComplexMemory();

    public void workingMemory(String temp) {
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
