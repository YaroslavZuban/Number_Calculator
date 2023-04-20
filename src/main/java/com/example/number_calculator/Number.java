package com.example.number_calculator;

import com.example.number_calculator.editor_number_complex.ComplexNumber;

import java.util.ArrayList;

public interface Number<T> {
    T add(T value);
    T subtract(T value);
    T multiply(T value);
    T divide(T value);
}
