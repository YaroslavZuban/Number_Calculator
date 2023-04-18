package com.example.number_calculator.editor_comprehensive;

import java.util.Stack;
import java.util.Vector;

public class MathExpressionParser {
    public static String arrayToString(Vector<Object> arr) {
        Stack<String> stack = new Stack<>();

        for (Object obj : arr) {
            String str = obj.toString();

            switch (str) {
                case "+", "-", "*", "/" -> stack.push(str);
                default -> {
                    stack.push(str);
                    while (stack.size() >= 3) {
                        String temp = stack.pop(); // удаляем закрывающую скобку

                        String op = stack.pop();
                        String right = stack.pop();
                        String left = stack.pop();
                        stack.push("(" + left + op + right + ")");
                        stack.push(temp);
                    }
                }
            }
        }

         while (stack.size() == 3) { // обработка оставшихся элементов в стеке
            String op = stack.pop();
            String right = stack.pop();

            if(right.charAt(0)=='-'){
                right="("+right+")";
            }

            String left = stack.pop();

             if(left.charAt(0)=='-'){
                 left="("+left+")";
             }

            stack.push("(" + left + op + right + ")");
        }

        return stack.pop();
    }

}
