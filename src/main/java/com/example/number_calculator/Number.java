package com.example.number_calculator;
/**
 * Данный интерфейс реализует число и операции над ним
 */
public interface Number<T> {
    T add(T value);
    T subtract(T value);
    T multiply(T value);
    T divide(T value);
}
