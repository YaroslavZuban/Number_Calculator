package com.example.number_calculator.editor_simple_fraction;

import com.example.number_calculator.editor_number_systems.TPNumber;

public class CalculatorFractionMemory {
    private Fraction value=null;

    public CalculatorFractionMemory() {
        value=new Fraction(Integer.MIN_VALUE,Integer.MIN_VALUE);
    }

    public void memoryClear() {
        value = new Fraction(Integer.MIN_VALUE,Integer.MIN_VALUE);
    }

    public Fraction memoryRead() {
        return value;
    }

    public void memorySave(Fraction number) {
        value = new Fraction(number);
    }

    public void memoryPlus(Fraction number) {
        value=value.add(number);
    }
    public Fraction getValue(){
        return value;
    }
}
