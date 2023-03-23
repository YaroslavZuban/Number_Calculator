package com.example.number_calculator;

public class CalculatorMemory {
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

    public void memoryPlus(String displayValue) {
        double numberOne = Double.parseDouble(new Converter_10_p2().conv(displayValue, system));
        double numberTwo = Double.parseDouble(new Converter_10_p2().conv(memoryValue, system));

        numberTwo += numberOne;

        memoryValue=new Converter_10_p2().conv(String.valueOf(numberTwo),system);
    }

    public int getSystem() {
        return system;
    }

    public void setSystem(int system) {
        this.system = system;
    }
}
