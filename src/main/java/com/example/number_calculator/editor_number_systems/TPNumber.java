package com.example.number_calculator.editor_number_systems;

/**
 * number - поле типа String, содержащее значение числа в данной системе счисления.
 * system - поле типа int, представляющее систему счисления данного числа.
 * NumberInBaseN() - конструктор по умолчанию, устанавливающий значение number в пустую строку и system в Integer.MIN_VALUE.
 * NumberInBaseN(String number, int system) - конструктор, устанавливающий значение number и system в переданные аргументы.
 * NumberInBaseN(NumberInBaseN numberInBaseN) - конструктор, создающий новый объект NumberInBaseN на основе переданного объекта NumberInBaseN.
 * getSystem() - метод, возвращающий значение поля system.
 * setSystem(int system) - метод, устанавливающий значение поля system.
 * getNumber() - метод, возвращающий значение поля number.
 * setNumber(String number) - метод, устанавливающий значение поля number.
 * toString() - метод, возвращающий строковое представление объекта в формате "number(system)".
 */
public class TPNumber {
    private String number;
    private int system = Integer.MIN_VALUE;

    public TPNumber() {
        number = "";
    }

    Converter converter = null;

    public TPNumber(String number, int system) {
        this.number = number;
        this.system = system;
    }

    public TPNumber(TPNumber numberInBaseN) {
        this.number = numberInBaseN.getNumber();
        this.system = numberInBaseN.getSystem();
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
    public String toString() {
        return number + "(" + system + ")";
    }


    public TPNumber add(TPNumber n) {
        converter = new Converter_p1_10();

        double one = Double.parseDouble(converter.conv(number, system));
        double two = Double.parseDouble(converter.conv(n.getNumber(), n.getSystem()));
        double result = one + two;

        converter = new Converter_10_p2();

        return new TPNumber(converter.conv(String.valueOf(result), n.getSystem()), n.getSystem());
    }

    public TPNumber multiply(TPNumber n) {
        converter = new Converter_p1_10();

        double one = Double.parseDouble(converter.conv(number, system));
        double two = Double.parseDouble(converter.conv(n.getNumber(), n.getSystem()));
        double result = one * two;

        converter = new Converter_10_p2();

        return new TPNumber(converter.conv(String.valueOf(result), n.getSystem()), n.getSystem());
    }

    public TPNumber subtract(TPNumber n) {
        converter = new Converter_p1_10();

        double one = Double.parseDouble(converter.conv(number, system));
        double two = Double.parseDouble(converter.conv(n.getNumber(), n.getSystem()));
        double result = one - two;

        converter = new Converter_10_p2();

        return new TPNumber(converter.conv(String.valueOf(result), n.getSystem()), n.getSystem());
    }

    public TPNumber divide(TPNumber n) {
        converter = new Converter_p1_10();

        double one = Double.parseDouble(converter.conv(number, system));
        double two = Double.parseDouble(converter.conv(n.getNumber(), n.getSystem()));
        double result = one / two;

        converter = new Converter_10_p2();

        return new TPNumber(converter.conv(String.valueOf(result), n.getSystem()), n.getSystem());
    }

    public TPNumber power(double a) {
        converter = new Converter_p1_10();

        double one = Double.parseDouble(converter.conv(number, system));
        double result = Math.pow(one, a);

        converter = new Converter_10_p2();

        return new TPNumber(converter.conv(String.valueOf(result), system), system);
    }

    public TPNumber reciprocalNumber() {
        converter = new Converter_p1_10();

        double one = Double.parseDouble(converter.conv(number, system));
        double result = 1 / one;

        converter = new Converter_10_p2();

        return new TPNumber(converter.conv(String.valueOf(result), system), system);
    }
}
