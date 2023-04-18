/*
 * ComplexNumber.java
 *
 * Created on 5 Май 2004 г., 9:12
 */

package com.example.number_calculator.editor_comprehensive;

import java.util.StringTokenizer;
import java.text.NumberFormat;
/**
 * Этот класс содержит комплексное число и позволяет
 * выполнять математические операции над ним.
 * @author  Statsenko Vladimir
 */
public class ComplexNumber {
    
    private double Re = 0; //действительная часть числа
    private double Im = 0; //мнимая часть числа
    
    /**
     * Создает новые экземпляры ComplexNumber
     * @param value строка, содержащая комплексное число.
     * Например, "5,6 + 2,2i"; "-2"; "-0,3i"
     */
    public ComplexNumber(String value) throws java.text.ParseException {
        String val = removeSpaces(value);
        parseComplexNumber(val);
    }
    
    /**
     * Создает новые экземпляры ComplexNumber
     * @param value экземпляр класса ComplexNumber.
     */
    public ComplexNumber(ComplexNumber value) {
        Re = value.getRe();
        Im = value.getIm();
    }
    
    /** Возвращает действительную часть числа*/
    public double getRe() {
        return Re;
    }

    /** Возвращает мнимую часть числа */
    public double getIm() {
        return Im;
    }
    
    /** Преобразует комплексное число в строку */
    public String toString() {
        String retValue;
        NumberFormat nf = NumberFormat.getInstance();
        if(Re == 0)
            retValue = nf.format(Im) + "i";
        else
            if(Im > 0)
                retValue = nf.format(Re) + "+" + nf.format(Im) + "i";
            else
                retValue = nf.format(Re) + nf.format(Im) + "i";
        if(Im == 0)
            retValue = nf.format(Re);
        return retValue;
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
    
    /* Преобразует строку str в комплексное число 
       (строка не должна содержать пробелов)*/
    private void parseComplexNumber(String str) 
                    throws java.text.ParseException {
        StringTokenizer st = new StringTokenizer(str, "+-", true);
        String previousToken = "";
        String currentToken = "";
        String numb = "";
        NumberFormat nf = NumberFormat.getInstance();
        while(st.hasMoreTokens())
        {
            currentToken = st.nextToken();
            if(previousToken.equals("-"))
                numb = previousToken + currentToken;
            else
                numb = currentToken;
            if(numb.equals("+") == false && numb.equals("-") == false)
            {
                if(numb.indexOf('i') == -1)
                    Re = nf.parse(numb).doubleValue();
                else
                {
                    numb = numb.replace('i', ' ');
                    if(numb.equals(" "))
                        numb = "1";
                    if(numb.equals("- "))
                        numb = "-1";
                    Im = nf.parse(numb).doubleValue();
                }
            }
            previousToken = currentToken;
        }
    }

    /** Проверяет равно ли данное комплексное число заданному 
     * @param value заданное число 
     */
    public boolean equal(ComplexNumber value) {
        if(Re == value.Re && Im == value.Im)
            return true;
        return false;
    }
    
    /** Прибавляет к данному комплексному числу заданное
     * @param value заданное число
     */
    public void add(ComplexNumber value) {
        Re += value.Re;
        Im += value.Im;
    }
    
    /** Вычитает из данного комплексного числа заданное
     * @param value заданное число
     */
    public void sub(ComplexNumber value) {
        Re -= value.Re;
        Im -= value.Im;
    }
    
    /** Умножает данное комплексное число на заданное
     * @param value заданное число
     */
    public void mult(ComplexNumber value) {
        double tempRe = Re * value.Re - Im * value.Im;
        double tempIm = Re * value.Im + Im * value.Re;
        Re = tempRe;
        Im = tempIm;
    }

    /** Делит данное комплексное число на заданное
     * @param value заданное число
     */
    public void div(ComplexNumber value) {
        double denominator = value.Re * value.Re + value.Im * value.Im;
        if(denominator == 0)
        {
            Re = Im = 0;
            return;
        }
        double tempRe, tempIm;
        tempRe = (Re * value.Re + Im * value.Im) / denominator;
        tempIm = (Im * value.Re - Re * value.Im) / denominator;
        Re = tempRe;
        Im = tempIm;
    }
}
