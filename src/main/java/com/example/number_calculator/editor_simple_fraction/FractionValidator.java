package com.example.number_calculator.editor_simple_fraction;

/**
 * содержит статический метод findMaxMin, который принимает объект типа Fraction
 * в качестве параметра и проверяет, является ли его числитель или знаменатель
 * равным Integer.MAX_VALUE или Integer.MIN_VALUE.
 */
public class FractionValidator {

    /**
     * используется для проверки того, что числитель и знаменатель
     * дроби не являются максимальным или минимальным
     * значением типа Integer, что может привести к ошибкам при арифметических операциях.
     */
    public static boolean findMaxMin(Fraction fraction_one) {
        if (fraction_one.getNumerator() != Integer.MAX_VALUE && fraction_one.getNumerator() != Integer.MIN_VALUE &&
                fraction_one.getDenominator() != Integer.MAX_VALUE && fraction_one.getDenominator() != Integer.MIN_VALUE)
            return true;

        return false;
    }
}
