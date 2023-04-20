/*
 * ComplexNumber.java
 *
 * Created on 5 ��� 2004 �., 9:12
 */

package com.example.number_calculator.editor_number_complex;

import com.example.number_calculator.Number;

import java.util.StringTokenizer;
import java.text.NumberFormat;

/**
 * ���� ����� �������� ����������� ����� � ���������
 * ��������� �������������� �������� ��� ���.
 *
 * @author Statsenko Vladimir
 */
public class ComplexNumber  {

    private double Re = 0; //�������������� ����� �����
    private double Im = 0; //������ ����� �����


    public ComplexNumber(double re, double im) {
        this.Re = re;
        this.Im = im;
    }

    /**
     * ������� ����� ���������� ComplexNumber
     *
     * @param value ������, ���������� ����������� �����.
     *              ��������, "5,6 + 2,2i"; "-2"; "-0,3i"
     */
    public ComplexNumber(String value) throws java.text.ParseException {
        String val = removeSpaces(value);
        parseComplexNumber(val);
    }

    /**
     * ������� ����� ���������� ComplexNumber
     *
     * @param value ��������� ������ ComplexNumber.
     */
    public ComplexNumber(ComplexNumber value) {
        Re = value.getRe();
        Im = value.getIm();
    }

    /**
     * ���������� �������������� ����� �����
     */
    public double getRe() {
        return Re;
    }

    /**
     * ���������� ������ ����� �����
     */
    public double getIm() {
        return Im;
    }

    /* ������� ������� �� ������ str. ���������� ������ ��� �������� */
    private String removeSpaces(String str) {
        StringTokenizer st = new StringTokenizer(str);
        StringBuffer temp = new StringBuffer(str.length());
        while (st.hasMoreTokens()) {
            temp.append(st.nextToken());
        }
        return temp.toString();
    }

    /* ����������� ������ str � ����������� ����� 
       (������ �� ������ ��������� ��������)*/
    private void parseComplexNumber(String str)
            throws java.text.ParseException {
        StringTokenizer st = new StringTokenizer(str, "+-", true);
        String previousToken = "";
        String currentToken = "";
        String numb = "";
        NumberFormat nf = NumberFormat.getInstance();
        while (st.hasMoreTokens()) {
            currentToken = st.nextToken();
            if (previousToken.equals("-"))
                numb = previousToken + currentToken;
            else
                numb = currentToken;
            if (numb.equals("+") == false && numb.equals("-") == false) {
                if (numb.indexOf('i') == -1)
                    Re = nf.parse(numb).doubleValue();
                else {
                    numb = numb.replace('i', ' ');
                    if (numb.equals(" "))
                        numb = "1";
                    if (numb.equals("- "))
                        numb = "-1";
                    Im = nf.parse(numb).doubleValue();
                }
            }
            previousToken = currentToken;
        }
    }

    /**
     * ��������� ����� �� ������ ����������� ����� ���������
     *
     * @param value �������� �����
     */
    public boolean equal(ComplexNumber value) {
        if (Re == value.Re && Im == value.Im)
            return true;
        return false;
    }

    /**
     * ���������� � ������� ������������ ����� ��������
     *
     * @param value �������� �����
     */


    public void add(ComplexNumber value) {
        Re += value.Re;
        Im += value.Im;
    }

    /**
     * �������� �� ������� ������������ ����� ��������
     *
     * @param value �������� �����
     */

    public void subtract(ComplexNumber value) {
        Re -= value.Re;
        Im -= value.Im;
    }

    /**
     * �������� ������ ����������� ����� �� ��������
     *
     * @param value �������� �����
     */

    public void multiply(ComplexNumber value) {
        double tempRe = Re * value.Re - Im * value.Im;
        double tempIm = Re * value.Im + Im * value.Re;
        Re = tempRe;
        Im = tempIm;
    }

    /**
     * ����� ������ ����������� ����� �� ��������
     *
     * @param value �������� �����
     */

    public void divide(ComplexNumber value) {
        double denominator = value.Re * value.Re + value.Im * value.Im;
        if (denominator == 0) {
            Re = Im = 0;
            return;
        }
        double tempRe, tempIm;
        tempRe = (Re * value.Re + Im * value.Im) / denominator;
        tempIm = (Im * value.Re - Re * value.Im) / denominator;
        Re = tempRe;
        Im = tempIm;
    }


    public double Mdl() {
        return Math.sqrt(Re * Re + Im * Im);
    }

    // ���������� ��������� ������������ ����� � ��������
    public double cnd() {
        return Math.toDegrees(Math.atan2(Im, Re));
    }

    // ���������� ��������� ������������ ����� � ��������
    public double cnr() {
        return Math.atan2(Im, Re);
    }

    /**
     * ����������� ����������� ����� � ������
     */
    @Override
    public String toString() {
        String negative = String.valueOf(Im);

        if (negative.charAt(0) == '-') {
            negative = "-" + negative;
        } else {
            negative = "+" + negative;
        }

        return Re + Im + "i";

    }

    // ���������� ����� ������������ �����
    public ComplexNumber[] Root(int n) {
        ComplexNumber[] roots = new ComplexNumber[n];
        double r = Math.pow(Mdl(), 1.0 / n);
        double theta = cnr() / n;
        for (int i = 0; i < n; i++) {
            double realPart = r * Math.cos(theta + 2 * Math.PI * i / n);
            double imagPart = r * Math.sin(theta + 2 * Math.PI * i / n);
            roots[i] = new ComplexNumber(realPart, imagPart);
        }

        return roots;
    }
}
