/*
 * UnrecognizableElementException.java
 *
 * Created on 6 ��� 2004 �., 17:59
 */

package com.example.number_calculator.editor_number_complex;

public class UnrecognizableElementException extends Exception {

    public UnrecognizableElementException() {
        super("�� ���� ���������� �������");
    }

    public UnrecognizableElementException(String element) {
        super("�� ���� ���������� ������� \"" + element + "\"");
    }
    
}
