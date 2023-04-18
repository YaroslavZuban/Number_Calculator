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

    public int getDenominator() {
        return this.value.getDenominator();
    }

    public int getNumerator() {
        return this.value.getNumerator();
    }

    public void setDenominator(int denominator) {
        value.setDenominator(denominator);
    }

    public void setNumerator(int numerator) {
        value.setNumerator(numerator);
    }

    public Fraction getValue(){
        return value;
    }
}