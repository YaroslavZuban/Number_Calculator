/*
 * UnrecognizableElementException.java
 *
 * Created on 6 ��� 2004 �., 17:59
 */

package com.example.number_calculator.editor_number_complex;

/**
 * ���� ����� ������������ ��� ������������� � ������ CalcClasses.
 * ������������ ��� �������������� � ���, ��� ������� ������ �����
 * ����������� ������.
 * @author  Statsenko Vladimir
 */
public class UnrecognizableElementException extends Exception {
    
    /** ������� ���������� ������ UnrecognizableElementException */
    public UnrecognizableElementException() {
        super("�� ���� ���������� �������");
    }

    /** ������� ���������� ������ UnrecognizableElementException,
     * ���������� ���������� �� ��������, ���������� ������� ��
     * �������.
     * @param element ������, ���������� ��������, ������� �� 
     * ���������� ����������
     */
    public UnrecognizableElementException(String element) {
        super("�� ���� ���������� ������� \"" + element + "\"");
    }
    
}
