/*
 * IncorrectTypeException.java
 *
 * Created on 6 Май 2004 г., 20:01
 */

package com.example.number_calculator.editor_number_complex;

/**
 * Этот класс предназначен для использования в пакете CalcClasses.
 * Используется для информирования о том, что какие-то опрерации
 * не могут быть применены к элементу данного типа.
 * Например, к экземпляру класса PostfixElement не может быть применена
 * функция getNumber(), если он содержит оператор.
 *
 * @author  Statsenko Vladimir
 */
public class IncorrectTypeException extends Exception {
    
    /** Создает новые экземпляры класса IncorrectTypeException */
    public IncorrectTypeException() {
        super("Не правильный тип элемента");
    }
    
}
