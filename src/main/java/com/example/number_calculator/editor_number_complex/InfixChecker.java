/*
 * InfixChecker.java
 *
 * Created on 15 ��� 2004 �., 16:00
 */

package com.example.number_calculator.editor_number_complex;

import java.util.Vector;
import java.util.Iterator;

public class InfixChecker {
    
    Vector elements;
    

    public InfixChecker(Vector infixVector) {
        elements = infixVector;
    }
    

    public void check() throws IncorrectElementException,
                                IncorrectTypeException {
        if(elements == null)
            return;
        int rightBracketsCount = 0;
        int leftBracketsCount = 0;
        int operatorsCount = 0;
        int numbersCount = 0;
        PostfixElement curElem = null;
        PostfixElement prevElem = null;
        boolean firstElem = true;
        
        Iterator iterator = elements.iterator();
        if(iterator == null)
            return;
        
        while(iterator.hasNext())
        {
            curElem = (PostfixElement)iterator.next();
            
            //������ ������� ����� ���� ���� ������, ���� ����������� �������
            if(firstElem)
            {
                if(curElem.isOperator() && (curElem.getOperatorType() !=
                                        PostfixElementType.LEFT_BRACKET))
                    throw new IncorrectElementException("������ ��� ������� ���������",
                                                    curElem.getElementIndex());
                firstElem = false;
            }
            
            //������� ���������� ������� ������ � ���������� ���������������
            //���������� ���������� (������ �� �����������) � �����
            if(curElem.getElementType() == PostfixElementType.OPERATOR)
            {
                numbersCount = 0;
                if(curElem.getOperatorType() == PostfixElementType.RIGHT_BRACKET)
                    rightBracketsCount++;
                else 
                    if(curElem.getOperatorType() ==
                                PostfixElementType.LEFT_BRACKET)
                        leftBracketsCount++;
                    else
                        operatorsCount++;
            }
            if(curElem.getElementType() == PostfixElementType.NUMBER)
            {
                numbersCount++;
                operatorsCount = 0;
            }
            
            //����� � ��������� ������ ������������
            if(numbersCount > 1 || operatorsCount > 1)
                throw new IncorrectElementException("������ ��� ������� ���������",
                                                    curElem.getElementIndex());
            
            if(prevElem != null)
            {
                //����� ����������� ������ ����� ���� ���� �����, ���� ������ ������
                if(prevElem.isOperator() && (prevElem.getOperatorType() ==
                                            PostfixElementType.LEFT_BRACKET))
                {
                    if(curElem.isOperator())
                        if((curElem.getOperatorType() !=
                                    PostfixElementType.LEFT_BRACKET) &&
                                    curElem.getOperatorType() !=
                                    PostfixElementType.RIGHT_BRACKET)
                            throw new IncorrectElementException("������ ��� ������� ���������",
                                                        curElem.getElementIndex());
                }

                //����� ����������� ������ ����� ���� ����� ��������, �� ��
                //����� ���� ����� � ����������� ������
                if(prevElem.isOperator() && (prevElem.getOperatorType() ==
                                            PostfixElementType.RIGHT_BRACKET))
                {
                    if(curElem.isNumber())
                        throw new IncorrectElementException("������ ��� ������� ���������",
                                                    curElem.getElementIndex());
                    else
                        if(curElem.getOperatorType() ==
                                            PostfixElementType.LEFT_BRACKET)
                            throw new IncorrectElementException("������ ��� ������� ���������",
                                                    curElem.getElementIndex());
                }
            }
            
            prevElem = curElem;
        }
        
        //��������� ������� ����� ���� ���� ������, ���� ����������� �������
            if(curElem.isOperator() && (curElem.getOperatorType() !=
                                        PostfixElementType.RIGHT_BRACKET))
                throw new IncorrectElementException("������ ��� ������� ���������",
                                                    curElem.getElementIndex());
        
        //���������� ����������� ������ ������ ���� ����� ���������� �����������
        if(leftBracketsCount > rightBracketsCount)
            throw new IncorrectElementException("����������� \")\"", 0);
        if(leftBracketsCount < rightBracketsCount)
            throw new IncorrectElementException("����������� \"(\"", 0);
    }
}
