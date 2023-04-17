/*
 * InfixChecker.java
 *
 * Created on 15 Май 2004 г., 16:00
 */

package com.example.number_calculator.editor_comprehensive;

import java.util.Vector;
import java.util.Iterator;
/**
 * Этот класс предназначен для использования в пакете CalcClasses.
 * Он выполняет поиск ошибок в инфиксном выражении. Например,
 * отсутсвие части скобок "(2+3)-(5", запись нескольких операторов
 * подряд "3/*+-2" и др.
 *
 * @author  Statsenko Vladimir
 */
public class InfixChecker {
    
    Vector elements;
    
    /** Создает новые экземпляры класса InfixChecker
     *
     * @param infixVector указатель на объект класса
     * <code>Vector</code>, содержащий анализируемую последовательность. */
    public InfixChecker(Vector infixVector) {
        elements = infixVector;
    }
    
    /**Выполняет проверку.
     * 
     * @exception IncorrectElementException если была найдена ошибка
     * 
     * @exception IncorrectTypeException если в процессе обработки
     * возникла ошибка при работе с объектами типа PostfixElement
     */
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
            
            //первый элемент может быть либо числом, либо открывающей скобкой
            if(firstElem)
            {
                if(curElem.isOperator() && (curElem.getOperatorType() !=
                                        PostfixElementType.LEFT_BRACKET))
                    throw new IncorrectElementException("Ошибка при анализе выражения",
                                                    curElem.getElementIndex());
                firstElem = false;
            }
            
            //считаем количество круглых скобок и количество последовательно
            //записанных операторов (скобки не учитываются) и чисел
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
            
            //числа и операторы должны чередоваться
            if(numbersCount > 1 || operatorsCount > 1)
                throw new IncorrectElementException("Ошибка при анализе выражения",
                                                    curElem.getElementIndex());
            
            if(prevElem != null)
            {
                //после открывающей скобки может идти либо число, либо другая скобка
                if(prevElem.isOperator() && (prevElem.getOperatorType() ==
                                            PostfixElementType.LEFT_BRACKET))
                {
                    if(curElem.isOperator())
                        if((curElem.getOperatorType() !=
                                    PostfixElementType.LEFT_BRACKET) &&
                                    curElem.getOperatorType() !=
                                    PostfixElementType.RIGHT_BRACKET)
                            throw new IncorrectElementException("Ошибка при анализе выражения",
                                                        curElem.getElementIndex());
                }

                //после закрывающей скобки может идти любой оператор, но не
                //может идти число и открывающая скобка
                if(prevElem.isOperator() && (prevElem.getOperatorType() ==
                                            PostfixElementType.RIGHT_BRACKET))
                {
                    if(curElem.isNumber())
                        throw new IncorrectElementException("Ошибка при анализе выражения",
                                                    curElem.getElementIndex());
                    else
                        if(curElem.getOperatorType() ==
                                            PostfixElementType.LEFT_BRACKET)
                            throw new IncorrectElementException("Ошибка при анализе выражения",
                                                    curElem.getElementIndex());
                }
            }
            
            prevElem = curElem;
        }
        
        //последний элемент может быть либо числом, либо закрывающей скобкой
            if(curElem.isOperator() && (curElem.getOperatorType() !=
                                        PostfixElementType.RIGHT_BRACKET))
                throw new IncorrectElementException("Ошибка при анализе выражения",
                                                    curElem.getElementIndex());
        
        //количество открывающих скобок должно быть равно количеству закрывающих
        if(leftBracketsCount > rightBracketsCount)
            throw new IncorrectElementException("Отсутствует \")\"", 0);
        if(leftBracketsCount < rightBracketsCount)
            throw new IncorrectElementException("Отсутствует \"(\"", 0);
    }
}
