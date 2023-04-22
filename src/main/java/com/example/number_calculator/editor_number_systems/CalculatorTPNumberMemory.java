package com.example.number_calculator.editor_number_systems;

/**
 * представляет собой класс для хранения значения числа типа TPNumber и выполнения операций с ним
 */
public class CalculatorTPNumberMemory {
    private TPNumber value=null;
    public CalculatorTPNumberMemory() {
        value=new TPNumber("0",10);
    }

    public void memorySave(TPNumber number) {
        value=new TPNumber(number);
    }

    public void memoryPlus(TPNumber number) {
        value=value.add(number);
    }

    public int getSystem() {
        return this.value.getSystem();
    }

    public void setSystem(int system) {
        this.value.setSystem(system);
    }

    public String getNumber(){
        return this.value.getNumber();
    }

    public void setNumber(String line){
        this.value.setNumber(line);
    }

    public TPNumber getValue(){
        return value;
    }
}
