/*
 * IncorrectElementException.java
 *
 * Created on 15 Май 2004 г., 16:18
 */

package com.example.number_calculator.editor_number_complex;

/**
 * Этот класс предназначен для использования в пакете CalcClasses.
 * Используется для информирования о том, что в исходной строке
 * есть ошибки.
 *
 * @author  Statsenko Vladimir
 */
public class IncorrectElementException extends Exception {
    
    int index = 0;
    
    /** Создает новые экземпляры класса IncorrectElementException
     * @param mes описание ошибки
     * @param index индекс перврго символа недопустимого элемента
     * в исходной строке. */
    public IncorrectElementException(String mes, int index) {
        super(mes);
        this.index = index;
    }
    
    /**Возвращает индекс первого символа недопустимого элемента
     * в исходной строке. */
    public int getIndex() {
        return index;
    }
}
