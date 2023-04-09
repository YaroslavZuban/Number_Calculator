package com.example.number_calculator;

import com.example.number_calculator.editor_number_systems.Converter_10_p2;
import com.example.number_calculator.editor_number_systems.Converter_p1_10;
import com.example.number_calculator.editor_number_systems.NumberSystemChecker;

/**
 * @param memoryValue  - хранит значение в памяти, которое по умолчанию равно "0".
 *
 * @method memoryClear() очищает значение памяти, устанавливая memoryValue = "0".
 *
 *  @method memoryRead() возвращает текущее значение в памяти.
 *
 * @method  memorySave(String displayValue) сохраняет переданное значение displayValue в памяти.
 *
 * @method  memoryPlus(String displayValue, int numberSystem) складывает значение в памяти с переданным значением displayValue в указанной системе счисления numberSystem.
 * Перед выполнением операции происходит проверка на корректность введенных значений методом NumberSystemChecker.isNumberValidInBase().
 *
 * @method  getSystem() и setSystem() служат для получения и установки системы счисления, в которой работает память.
 */
public class CalculatorMemory {
    public String getMemoryValue() {
        return memoryValue;
    }

    private String memoryValue;
    private int system = 10;

    public CalculatorMemory() {
        memoryValue = "0";
    }

    public void memoryClear() {
        memoryValue = "0";
    }

    public String memoryRead() {
        return memoryValue;
    }

    public void memorySave(String displayValue) {
        memoryValue = displayValue;
    }

    public void memoryPlus(String displayValue, int numberSystem) {
        try {
            NumberSystemChecker.isNumberValidInBase(displayValue,numberSystem);
            double numberOne = Double.parseDouble(new Converter_p1_10().conv(displayValue, numberSystem));

            NumberSystemChecker.isNumberValidInBase(memoryValue,system);
            double numberTwo = Double.parseDouble(new Converter_p1_10().conv(memoryValue, system));

            numberTwo += numberOne;

            memoryValue=new Converter_10_p2().conv(String.valueOf(numberTwo),numberSystem);
            system=numberSystem;
        }catch (IllegalAccessError error){
            memoryValue="0";
            system=10;
        }
    }

    public int getSystem() {
        return system;
    }

    public void setSystem(int system) {
        this.system = system;
    }
}
