package com.example.number_calculator.editor_number_systems;

public class NumberInBaseN {
    private String number;
    private int system=Integer.MIN_VALUE;
    public NumberInBaseN(){
        number="";
    }

    public NumberInBaseN(String number, int system) {
        this.number = number;
        this.system = system;
    }

    public NumberInBaseN(NumberInBaseN numberInBaseN){
        this.number=numberInBaseN.getNumber();
        this.system=numberInBaseN.getSystem();
    }


    public int getSystem() {
        return system;
    }

    public void setSystem(int system) {
        this.system = system;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString(){
        return number+"("+system+")";
    }
}
