/*
 * PostfixCalculator.java
 *
 * Created on 7 Май 2004 г., 10:15
 */

package com.example.number_calculator.editor_number_complex;

import java.util.Vector;
import java.util.Stack;

/** Этот класс вычисляет значение выражения, записанного в постфиксной
 * форме. Это выражение можно получить из обычной строки (записанной в
 * инфиксной форме) с помощью класса PostfixConverter.
 *
 * @author  Statsenko Vladimir
 */
public class PostfixCalculator {

    public Vector getPostfixVector() {
        return postfixVector;
    }

    private Vector postfixVector = null;
    private ComplexNumber result = null;
    
    /** Создает новые экземпляры класса PostfixCalculator
     *
     * @param postfixVector объект класса <code>java.util.Vector</code>,
     * содержащий массив исходных данных в постфиксной форме. */
    public PostfixCalculator(Vector postfixVector) {
        this.postfixVector = postfixVector;
    }
    
    /** Вычисляет значение выражения.
     * @return результат вычислений */
    public ComplexNumber calculate() throws java.text.ParseException,
            IncorrectTypeException, UnrecognizableElementException {
        result = new ComplexNumber("0");
        Stack stack = new Stack();
        PostfixElement temp = null;
        for(int i = 0; i < postfixVector.size(); i++)
        {
            temp = (PostfixElement)postfixVector.get(i);
            if(temp.isNumber())
                stack.push(temp);
            if(temp.isOperator())
            {
                ComplexNumber y = ((PostfixElement)stack.pop()).getNumber();
                ComplexNumber x = ((PostfixElement)stack.pop()).getNumber();
                ComplexNumber res = solveOperation(x, y,
                                                    temp.getOperatorType());
                stack.push(new PostfixElement(res.toString(), 0));
            }
        }
        result = ((PostfixElement)stack.pop()).getNumber();
        return result;
    }
    
    /* Вычисляет результат каждой операции */
    private ComplexNumber solveOperation(ComplexNumber x, ComplexNumber y,
                        int operatorType) throws java.text.ParseException{
        ComplexNumber res = new ComplexNumber("0");
        res = x;
        switch(operatorType)
        {
            case PostfixElementType.PLUS : res.add(y); break;
            case PostfixElementType.MINUS : res.sub(y); break;
            case PostfixElementType.MULTIPLICATION : res.mult(y); break;
            case PostfixElementType.DIVISION : res.div(y); break;
        }
        return res;
    }
}
