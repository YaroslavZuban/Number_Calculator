package com.example.number_calculator;

import com.example.number_calculator.editor_number_systems.Converter_10_p2;
import com.example.number_calculator.editor_number_systems.Converter_p1_10;

public class CalculatorMemory {
    public String getMemoryValue() {
        return memoryValue;
    }

    private String memoryValue;
    private int system = 10;

    public CalculatorMemory() {
        memoryValue = "0";
    }

    public void memoryClear() {
        memoryValue = "0";
    }

    public String memoryRead() {
        return memoryValue;
    }

    public void memorySave(String displayValue) {
        memoryValue = displayValue;
    }

    public void memoryPlus(String displayValue, int numberSystem) {
        double numberOne = Double.parseDouble(new Converter_p1_10().conv(displayValue, numberSystem));
        double numberTwo = Double.parseDouble(new Converter_p1_10().conv(memoryValue, system));

        numberTwo += numberOne;

        memoryValue=new Converter_10_p2().conv(String.valueOf(numberTwo),numberSystem);
        system=numberSystem;
    }

    public int getSystem() {
        return system;
    }

    public void setSystem(int system) {
        this.system = system;
    }
}
