package com.example.number_calculator;

/**
 * интрефейс который в дальнейшем будет наследоваться и реализовыввать метод
 * conv перевода числа в другую систему счисления
 */
public interface Converter {
    String conv(String line, int system);
}
