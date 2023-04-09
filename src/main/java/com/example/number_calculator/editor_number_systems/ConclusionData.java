package com.example.number_calculator.editor_number_systems;

public class ConclusionData {
    public static StringBuilder dataInput(TPNumber number_one,TPNumber number_two,String operation) {
        StringBuilder result = new StringBuilder();

        if (number_one != null) {
            result.append(number_one.getNumber());
        }

        if (!operation.equals("")) {
            result.append(operation);
        }

        if (number_two != null) {
            if (number_two.getNumber().contains("-")) {
                result.append("(" + number_two.getNumber() + ")");
            } else {
                result.append(number_two.getNumber());
            }
        }

        return result;
    }
}
