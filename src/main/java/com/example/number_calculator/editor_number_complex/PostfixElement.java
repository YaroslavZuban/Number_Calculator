/*
 * PostfixElement.java
 *
 * Created on 6 ��� 2004 �., 14:17
 */

package com.example.number_calculator.editor_number_complex;


public class PostfixElement implements PostfixElementType {
    
    private int elementType = -1;
    private int operatorType = -1;
    private ComplexNumber number = null;
    private String value = "";
    private int index = 0; //������ ������� ������� �������� � �������� ������
    

    public PostfixElement(String value, int index)
                throws UnrecognizableElementException {
        parseElement(value);
        this.value = value;
        this.index = index;
    }
    
    /* ���������� ��� � ��������� ��������, ��������� ������� value */
    private void parseElement(String value)
                throws UnrecognizableElementException {
        if(value.equals("("))
        {
            elementType = PostfixElementType.OPERATOR;
            operatorType = PostfixElementType.LEFT_BRACKET;
            return;
        }
        if(value.equals(")"))
        {
            elementType = PostfixElementType.OPERATOR;
            operatorType = PostfixElementType.RIGHT_BRACKET;
            return;
        }
        if(value.equals("+"))
        {
            elementType = PostfixElementType.OPERATOR;
            operatorType = PostfixElementType.PLUS;
            return;
        }
        if(value.equals("-"))
        {
            elementType = PostfixElementType.OPERATOR;
            operatorType = PostfixElementType.MINUS;
            return;
        }
        if(value.equals("*"))
        {
            elementType = PostfixElementType.OPERATOR;
            operatorType = PostfixElementType.MULTIPLICATION;
            return;
        }
        if(value.equals("/"))
        {
            elementType = PostfixElementType.OPERATOR;
            operatorType = PostfixElementType.DIVISION;
            return;
        }
        
        try
        {
            number = new ComplexNumber(value);
            elementType = PostfixElementType.NUMBER;
        }
        catch(java.text.ParseException pe)
        {
            throw new UnrecognizableElementException(value);
        }
    }
    

    public String toString() {
        String retValue = "";
        if(elementType == -1)
            retValue = "�������� �� �����������";
        else
        {
            if(elementType == PostfixElementType.OPERATOR)
                retValue = this.value;
            if(elementType == PostfixElementType.NUMBER)
                retValue = number.toString();
        }
        return retValue;
    }
    

    public boolean equals(PostfixElement elem) {
        boolean retValue = false;
        if((elementType == elem.elementType) &&
                (operatorType == elem.operatorType) &&
                (number.equal(elem.number)))
            retValue = true;
        return retValue;
    }
    

    public int getElementType() {
        return elementType;
    }


    public int getOperatorType() throws IncorrectTypeException {
        if(elementType != PostfixElementType.OPERATOR)
            throw new IncorrectTypeException();
        return operatorType;
    }

    public int getOperatorPriority() throws IncorrectTypeException {
        if(elementType != PostfixElementType.OPERATOR)
            throw new IncorrectTypeException();
        return PostfixElementType.priorities[operatorType];
    }

    public ComplexNumber getNumber() throws IncorrectTypeException {
        if(elementType != PostfixElementType.NUMBER)
            throw new IncorrectTypeException();
        return number;
    }

    public boolean isOperator() {
        if(elementType == PostfixElementType.OPERATOR)
            return true;
        return false;
    }

    public boolean isNumber() {
        if(elementType == PostfixElementType.NUMBER)
            return true;
        return false;
    }
    

    public int getElementIndex() {
        return index;
    }
}
