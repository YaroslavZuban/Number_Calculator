/*
 * PostfixConverter.java
 *
 * Created on 7 Май 2004 г., 10:15
 */

package com.example.number_calculator.editor_comprehensive;

import java.util.Vector;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Iterator;
/**
 * Этот класс преобразует строку, записанную в инфиксной форме
 * в массив объектов PostfixElement в постфиксной форме.
 *
 * @author  Statsenko Vladimir
 */
public class PostfixConverter {
    
    private Vector postfixVector = null;
    private Vector infixVector = null;
    String infixStr;
    
    /** Создает новые экземпляры класса PostfixConverter
     *
     * @param str строка, содержащая выражение в инфиксной форме */ 
    public PostfixConverter(String str) {
        infixStr = str;
    }
    
    /** Преобразует строку, записанную в инфиксной форме
     * в массив объектов PostfixElement в постфиксной форме. 
     *
     * @return ссылку на объект класса <code>java.util.Vector</code>,
     * содежащий массив объектов PostfixElement в постфиксной форме.
     *
     * @exception IllegalArgumentException если была попытка
     * создать массив объектов с отрицательной длиной 
     *
     * @exception UnrecognizableElementException если невозможно
     * распознать новый элемент 
     *
     * @exception IncorrectTypeException если была попытка вызвать
     * один из методов класса PostfixElement, который недопустим для 
     * данного экземпляра класса. Например, к экземпляру класса PostfixElement
     * не может быть применена функция getNumber(), если он содержит оператор.*/
    public Vector convertToPostfix() throws IllegalArgumentException,
                UnrecognizableElementException, IncorrectTypeException,
                IncorrectElementException {
        infixVector = new Vector(10, 5);
        parseStr(infixStr);
        InfixChecker checker = new InfixChecker(infixVector);
        checker.check();
        convert();
        return postfixVector;
    }
    
    /* Преобразует строку, записанную в инфиксной форме в массив
       объектов PostfixElement в инфиксной форме */
    private void parseStr(String str) throws UnrecognizableElementException,
                IncorrectTypeException {
        infixStr = removeSpaces(str);
        StringTokenizer st = new StringTokenizer(infixStr, "()+-*/", true);
        PostfixElement currentElement = null;
        PostfixElement previousElement = null;
        int tokenIndex = 0;
        int curIndex = 0;
        boolean firstElement = true;
        while(st.hasMoreTokens())
        {
            String currentToken = st.nextToken();
            tokenIndex = str.indexOf(currentToken, curIndex);
            currentElement = new PostfixElement(currentToken, tokenIndex);
            if(previousElement != null)
            {
                if(previousElement.isOperator() && 
                        (previousElement.getOperatorType() ==
                             PostfixElementType.LEFT_BRACKET)
                        && currentElement.isOperator())
                    if((currentElement.getOperatorType() ==
                                        PostfixElementType.MINUS) ||
                            (currentElement.getOperatorType() ==
                                        PostfixElementType.PLUS))
                    {
                        currentToken += st.nextToken();
                        tokenIndex = str.indexOf(currentToken, curIndex);
                        currentElement = new PostfixElement(currentToken,
                                                            tokenIndex);
                    }
            }
            if(firstElement)
            {
                if(currentElement.isOperator() &&
                      ((currentElement.getOperatorType() == PostfixElementType.MINUS) ||
                       (currentElement.getOperatorType() == PostfixElementType.PLUS)))
                    currentToken += st.nextToken();
                    tokenIndex = str.indexOf(currentToken, curIndex);
                    currentElement = new PostfixElement(currentToken,
                                                            tokenIndex);
                firstElement = false;    
            }
            
            infixVector.add(currentElement);
            curIndex = tokenIndex + currentToken.length();
            previousElement = currentElement;
        }
    }
    
    /* Преобразует массив объектов PostfixElement, записанный 
     в инфиксной форме в массив объектов PostfixElement
     в постфиксной форме.*/ 
    private void convert() throws IllegalArgumentException,
                UnrecognizableElementException, IncorrectTypeException {
        postfixVector = new Vector(infixVector.size());
        int curIndex = 0;
        PostfixElement curElement = null;
        Stack s = new Stack();
        s.push(new PostfixElement("(", 0));
        infixVector.add(new PostfixElement(")", 0));
        while(s.isEmpty() == false)
        {
            curElement = (PostfixElement)infixVector.get(curIndex);
            curIndex++;
            if(curElement.isNumber())
            {
                postfixVector.add(curElement);
                continue;
            }
            if(curElement.isOperator())
                if(curElement.getOperatorType() ==
                        PostfixElementType.LEFT_BRACKET)
                {
                    s.push(curElement);
                    continue;
                }
            if(curElement.isOperator())
                if(curElement.getOperatorType() !=
                            PostfixElementType.LEFT_BRACKET &&
                   curElement.getOperatorType() != 
                            PostfixElementType.RIGHT_BRACKET)
                {
                    while(true)
                    {
                        PostfixElement peekOperator = (PostfixElement)s.peek();
                        if(peekOperator.getOperatorType() ==
                                PostfixElementType.LEFT_BRACKET)
                            break;
                        if(peekOperator.getOperatorPriority() >=
                                            curElement.getOperatorPriority())
                            postfixVector.add(s.pop());
                        else
                            break;
                    }
                    s.push(curElement);
                    continue;
                }
            if(curElement.isOperator())
                if(curElement.getOperatorType() ==
                        PostfixElementType.RIGHT_BRACKET)
                    while(true)
                    {
                        PostfixElement peekOperator = (PostfixElement)s.peek();
                        if(peekOperator.getOperatorType() ==
                                PostfixElementType.LEFT_BRACKET)
                        {
                            s.pop();
                            break;
                        }
                        postfixVector.add(s.pop());
                    }
        }
    }

    /* Удаляет пробелы из строки str. Возвращает строку без пробелов */
    private String removeSpaces(String str) {
        StringTokenizer st = new StringTokenizer(str);
        StringBuffer temp = new StringBuffer(str.length());
        while(st.hasMoreTokens())
        {
            temp.append(st.nextToken());
        }
        return temp.toString();
    }
    
    /*Используется для отладки. На работу программы не влияет.*/
    public String VectorDump(String name) {
        StringBuffer retValue = new StringBuffer();
        Iterator vectorIterator = null;
        if(name.equals("infix"))
            vectorIterator = infixVector.iterator();
        if(name.equals("postfix"))
           vectorIterator = postfixVector.iterator();
        if(vectorIterator != null)
            while(vectorIterator.hasNext())
            {
                retValue.append(vectorIterator.next() + " ");
            }
        return retValue.toString();
    }
}
