package com.example.number_calculator.editor_number_systems;

public class CalculatorMemory {
    private TPNumber value=null;


    public CalculatorMemory() {
        value=new TPNumber("0",10);
    }

    public void memoryClear() {
        value = new TPNumber("0",10);
    }

    public TPNumber memoryRead() {
        return value;
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
