/*
 * PostfixElementType.java
 *
 * Created on 6 Май 2004 г., 14:17
 */

package com.example.number_calculator.editor_comprehensive;

/**
 *
 * @author  Statsenko Vladimir
 */
public interface PostfixElementType {
    //типы элементов
    final int NUMBER = 100;
    final int OPERATOR = 101;
    //порядковые номера операторов
    final int PLUS = 0;
    final int MINUS = 1;
    final int DIVISION = 2;
    final int MULTIPLICATION = 3;
    final int LEFT_BRACKET = 4;
    final int RIGHT_BRACKET = 5;
    //приоритеты операторов (индекс массива соответствует порядковому
    //номеру оператора). Чем больше число - тем выше приоретет.
    //Например, оператор MULTIPLICATION имеет приоретет = 1.
    final int[] priorities = {0, 0, 1, 1, 2, 2};
}
