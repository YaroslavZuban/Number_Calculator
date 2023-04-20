/*
 * IncorrectElementException.java
 *
 * Created on 15 ��� 2004 �., 16:18
 */

package com.example.number_calculator.editor_number_complex;


public class IncorrectElementException extends Exception {
    
    int index = 0;
    

    public IncorrectElementException(String mes, int index) {
        super(mes);
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
