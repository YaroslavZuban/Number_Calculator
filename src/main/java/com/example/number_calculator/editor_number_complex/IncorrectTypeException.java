/*
 * IncorrectTypeException.java
 *
 * Created on 6 ��� 2004 �., 20:01
 */

package com.example.number_calculator.editor_number_complex;

/**
 * ���� ����� ������������ ��� ������������� � ������ CalcClasses.
 * ������������ ��� �������������� � ���, ��� �����-�� ���������
 * �� ����� ���� ��������� � �������� ������� ����.
 * ��������, � ���������� ������ PostfixElement �� ����� ���� ���������
 * ������� getNumber(), ���� �� �������� ��������.
 *
 * @author  Statsenko Vladimir
 */
public class IncorrectTypeException extends Exception {
    
    /** ������� ����� ���������� ������ IncorrectTypeException */
    public IncorrectTypeException() {
        super("�� ���������� ��� ��������");
    }
    
}
