package com.example.number_calculator.editor_simple_fraction;

import com.example.number_calculator.Editor;
import com.example.number_calculator.TextEditingListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * реализует интерфейсы TextEditingListener и Editor и представляет
 *  * собой редактор для работы с числами в дробных числах.
 */
public class EditorFraction implements TextEditingListener,Editor {
    public static Fraction number_one = null;
    public static Fraction number_two = null;
    public static Fraction number_result = null;
    public static Fraction number_temp = null;
    public static StringBuilder lineInput = new StringBuilder();
    public static StringBuilder lineResult = new StringBuilder();
    public static String operation = "";
    InputFraction input = new InputFraction(number_one, number_two, number_result, number_temp, operation);
    @Override
    public void entrySymbol(MouseEvent event) {}

    /**
     * обрабатывают пользовательский ввод и выполняют соответствующие операции с числами, например, ввод нового символа или оператора.
     * @param event
     */
    @Override
    public void entryNumber(MouseEvent event) {
        String temp = ((Button) event.getSource()).getId().replace("button_", "");
        inputNumber(temp);
    }

    @Override
    public void entryOperator(MouseEvent event) {
        setInput();
        String temp = ((Button) event.getSource()).getId().replace("button_", "");

        input.entryOperator(temp);
        getInput();

        dataInput();
    }

    public void onDivisionClicked(){
       input.onDivisionClicked();

       getInput();
       dataInput();
    }
    /**
     * удаляет последний введенный символ или оператор
     * @param event
     */
    @Override
    public void onBackSpace(MouseEvent event) {
        String temp;

        if (number_two != null) {
            if (number_two.getDenominator() != Integer.MAX_VALUE && number_two.getDenominator() != Integer.MIN_VALUE) {
                temp = number_two.getDenominator().toString();
                temp = deleteSymbol(temp);

                if (temp.equals("")) {
                    number_two.setDenominator(Integer.MIN_VALUE);
                } else {
                    number_two.setDenominator(Integer.parseInt(temp));
                }

            } else if (number_two.getNumerator() != Integer.MAX_VALUE && number_two.getNumerator() != Integer.MIN_VALUE) {
                number_two.setDenominator(Integer.MIN_VALUE);

                temp = number_two.getNumerator().toString();
                temp = deleteSymbol(temp);

                if (temp.equals("")) {
                    number_two.setNumerator(Integer.MAX_VALUE);
                } else {
                    number_two.setNumerator(Integer.parseInt(temp));
                }
            }
        } else if (!operation.equals("")) {
            operation = "";
        } else {
            if (number_one.getDenominator() != Integer.MAX_VALUE && number_one.getDenominator() != Integer.MIN_VALUE) {
                temp = number_one.getDenominator().toString();
                temp = deleteSymbol(temp);

                if (temp.equals("")) {
                    number_one.setDenominator(Integer.MIN_VALUE);
                } else {
                    number_one.setDenominator(Integer.parseInt(temp));
                }

            } else if (number_one.getNumerator() != Integer.MAX_VALUE && number_one.getNumerator() != Integer.MIN_VALUE) {
                number_one.setDenominator(Integer.MIN_VALUE);

                temp = number_one.getNumerator().toString();
                temp = deleteSymbol(temp);

                if (temp.equals("")) {
                    number_one.setNumerator(Integer.MAX_VALUE);
                } else {
                    number_one.setNumerator(Integer.parseInt(temp));
                }
            }
        }

        setInput();
        dataInput();
    }
    /**
     * очищает всю введенную информацию
     * @param event
     */
    @Override
    public void onClean(MouseEvent event) {
        lineInput = new StringBuilder("0");
        lineResult = new StringBuilder();

        number_one = null;
        number_two = null;
        number_result = null;

        operation = "";

        setInput();
    }

    /**
     * очищает последнее введенное число или оператор.
     * @param event
     */
    @Override
    public void onCleanEntry(MouseEvent event) {
        if (number_two != null && number_two.getNumerator() != Integer.MAX_VALUE) {
            number_two.setNumerator(Integer.MAX_VALUE);
            number_two.setDenominator(Integer.MIN_VALUE);
        } else if (!operation.equals("")) {
            operation = "";
            number_two = null;
        } else if (number_one != null) {
            number_one = null;
        }

        setInput();

        dataInput();
    }

    /**
     * обрабатывает ввод цифры.
     * @param temp
     */
    private void inputNumber(String temp) {
        if (number_one != null && number_two != null && number_result != null) {
            number_two = null;
            number_one = null;
            number_result = null;

            operation = "";

            lineInput = new StringBuilder();
            lineResult = new StringBuilder();

            setInput();
        }

        input.inputNumber(temp);

        getInput();
        dataInput();
    }

    public void dataInput() {
        StringBuilder result = new StringBuilder();

        if (number_one != null) {
            if (number_one.getNumerator() != Integer.MAX_VALUE && number_one.getNumerator() != Integer.MIN_VALUE) {
                result.append(addNumberPrintf(number_one.getNumerator()));
            }

            if (number_one.getDenominator() != Integer.MAX_VALUE && number_one.getDenominator() != Integer.MIN_VALUE) {
                result.append("|");
                result.append(addNumberPrintf(number_one.getDenominator()));
            }
        }

        if (!operation.equals("")) {
            result.append(operation);
        }

        if (number_two != null) {
            if (number_two.getNumerator() != Integer.MAX_VALUE && number_two.getNumerator() != Integer.MIN_VALUE) {
                if (number_two.getNumerator() < 0) {
                    result.append("(");
                    result.append(addNumberPrintf(number_two.getNumerator()));
                    result.append(")");
                } else {
                    result.append(addNumberPrintf(number_two.getNumerator()));
                }
            }

            if (number_two.getDenominator() != Integer.MAX_VALUE && number_two.getDenominator() != Integer.MIN_VALUE) {
                result.append("|");
                result.append(addNumberPrintf(number_two.getDenominator()));
            }
        }

        lineInput = new StringBuilder(result);
    }

    public void dataResult(){
        StringBuilder result = new StringBuilder();

        if (number_result != null) {
            if (number_result.getNumerator() != Integer.MAX_VALUE && number_result.getNumerator() != Integer.MIN_VALUE) {
                result.append(addNumberPrintf(number_result.getNumerator()));
            }

            if (number_result.getDenominator() != Integer.MAX_VALUE && number_result.getDenominator() != Integer.MIN_VALUE) {
                result.append("|");
                result.append(addNumberPrintf(number_result.getDenominator()));
            }
        }

        lineResult = new StringBuilder(result);
    }

    public String addNumberPrintf(int number) {
        if (number != Integer.MAX_VALUE && number != Integer.MIN_VALUE) {
            return "" + number;
        }

        return "";
    }

    private String deleteSymbol(String line) {
        if (line.length() != 0) {
            StringBuilder temp = new StringBuilder(line);
            temp.deleteCharAt(temp.length() - 1);
            line = String.valueOf(temp);
        }

        return line;
    }

    private void setInput() {
        InputFraction.number_one = number_one;
        InputFraction.number_two = number_two;
        InputFraction.number_result = number_result;
        InputFraction.number_temp = number_temp;
        InputFraction.operation = operation;
    }

    private void getInput() {
        number_one = InputFraction.number_one;
        number_two = InputFraction.number_two;
        number_result = InputFraction.number_result;
        number_temp = InputFraction.number_temp;
        operation = InputFraction.operation;
    }
}
