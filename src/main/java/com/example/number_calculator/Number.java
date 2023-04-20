package com.example.number_calculator;
/**
 * Что делает данный класс, ответ пиши на русском
 */
public interface Number<T> {
    T add(T value);
    T subtract(T value);
    T multiply(T value);
    T divide(T value);
}
