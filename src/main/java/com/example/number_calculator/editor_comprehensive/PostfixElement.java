/*
 * PostfixElement.java
 *
 * Created on 6 Май 2004 г., 14:17
 */

package com.example.number_calculator.editor_comprehensive;

/**
 * Этот класс предназначен для использования в пакете CalcClasses.
 * Он предназначен для хранения объектов, из которых формируется
 * постфиксная запись анализируемой строки. В нем содержится 
 * информация о типе элемента и его значении.
 *
 * @author  Statsenko Vladimir
 */
public class PostfixElement implements PostfixElementType {
    
    private int elementType = -1;
    private int operatorType = -1;
    private ComplexNumber number = null;
    private String value = "";
    private int index = 0; //индекс первого символа элемента в исходной строке
    
    /** Создает новые экземпляры класса PostfixElement
     * @param value строка, содержащая новый элемент 
     * (число или оператор) 
     * @param index индекс первого символа элемента в  
     * исходной строке 
     * @exception UnrecognizableElementException если невозможно
     * распознать новый элемент */
    public PostfixElement(String value, int index)
                throws UnrecognizableElementException {
        parseElement(value);
        this.value = value;
        this.index = index;
    }
    
    /* Определяет тип и параметры элемента, заданного строкой value */
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
    
    /** Возвращает текстовое описание данного элемента */
    public String toString() {
        String retValue = "";
        if(elementType == -1)
            retValue = "Значение не установлено";
        else
        {
            if(elementType == PostfixElementType.OPERATOR)
                retValue = this.value;
            if(elementType == PostfixElementType.NUMBER)
                retValue = number.toString();
        }
        return retValue;
    }
    
    /** Возвращает true если данный экземпляр класса равен
     * заданному (параметр elem), false - в противном случае.
     *@param elem заданный экземпляр класса */
    public boolean equals(PostfixElement elem) {
        boolean retValue = false;
        if((elementType == elem.elementType) &&
                (operatorType == elem.operatorType) &&
                (number.equal(elem.number)))
            retValue = true;
        return retValue;
    }
    
    /** Возвращает тип элемента */
    public int getElementType() {
        return elementType;
    }

    /** Если данный элемент является оператором - возвращает его тип.
     * @exception IncorrectTypeException если данный элемент не
     * является оператором */
    public int getOperatorType() throws IncorrectTypeException {
        if(elementType != PostfixElementType.OPERATOR)
            throw new IncorrectTypeException();
        return operatorType;
    }
    
    /** Если данный элемент является оператором - возвращает его приоретет.
     * @exception IncorrectTypeException если данный элемент не
     * является оператором */
    public int getOperatorPriority() throws IncorrectTypeException {
        if(elementType != PostfixElementType.OPERATOR)
            throw new IncorrectTypeException();
        return PostfixElementType.priorities[operatorType];
    }
    
    /** Если данный элемент является числом - возвращает его.
     * @exception IncorrectTypeException если данный элемент не
     * является числом */
    public ComplexNumber getNumber() throws IncorrectTypeException {
        if(elementType != PostfixElementType.NUMBER)
            throw new IncorrectTypeException();
        return number;
    }
    
    /** Возвращает true если данный элемент является
     * оператором, в противном случае - false */
    public boolean isOperator() {
        if(elementType == PostfixElementType.OPERATOR)
            return true;
        return false;
    }
    
    /** Возвращает true если данный элемент является
     * числом, в противном случае - false */
    public boolean isNumber() {
        if(elementType == PostfixElementType.NUMBER)
            return true;
        return false;
    }
    
    /**Возвращает индекс первого символа данного элемента
     * в исходной строке. Используется для сообщения об
     * ошибках. */
    public int getElementIndex() {
        return index;
    }
}
