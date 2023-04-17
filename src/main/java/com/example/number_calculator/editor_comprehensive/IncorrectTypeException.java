/*
 * IncorrectTypeException.java
 *
 * Created on 6 ��� 2004 �., 20:01
 */

package com.example.number_calculator.editor_comprehensive;

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
