/*
 * UnrecognizableElementException.java
 *
 * Created on 6 Май 2004 г., 17:59
 */

package com.example.number_calculator.editor_comprehensive;

/**
 * Этот класс предназначен для использования в пакете CalcClasses.
 * Используется для информирования о том, что элемент строки имеет
 * неизвестный формат.
 * @author  Statsenko Vladimir
 */
public class UnrecognizableElementException extends Exception {
    
    /** Создает экземпляры класса UnrecognizableElementException */
    public UnrecognizableElementException() {
        super("Не могу распознать элемент");
    }

    /** Создает экземпляры класса UnrecognizableElementException,
     * содержащий информацию об элементе, распознать который не
     * удалось.
     * @param element строка, содержащая параметр, который не 
     * получилось распознать
     */
    public UnrecognizableElementException(String element) {
        super("Не могу распознать элемент \"" + element + "\"");
    }
    
}
