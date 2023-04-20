/*
 * PostfixElementType.java
 *
 * Created on 6 ��� 2004 �., 14:17
 */

package com.example.number_calculator.editor_number_complex;

/**
 *
 * @author  Statsenko Vladimir
 */
public interface PostfixElementType {
    //���� ���������
    final int NUMBER = 100;
    final int OPERATOR = 101;
    //���������� ������ ����������
    final int PLUS = 0;
    final int MINUS = 1;
    final int DIVISION = 2;
    final int MULTIPLICATION = 3;
    final int LEFT_BRACKET = 4;
    final int RIGHT_BRACKET = 5;
    //���������� ���������� (������ ������� ������������� �����������
    //������ ���������). ��� ������ ����� - ��� ���� ���������.
    //��������, �������� MULTIPLICATION ����� ��������� = 1.
    final int[] priorities = {0, 0, 1, 1, 2, 2};
}
