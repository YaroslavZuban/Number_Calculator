package com.example.number_calculator;

import javafx.scene.input.MouseEvent;

/**
 * интерфейс Editor с методами, которые должны быть реализованы в любом классе, который реализует этот интерфейс.
 * Методы включают функциональность для взаимодействия с графическим интерфейсом пользователя,
 * такие как вставка символов и чисел, выбор операторов,
 * очистка введенных данных .
 */
public interface Editor {
     void entrySymbol(MouseEvent event);
     void entryNumber(MouseEvent event);
     void entryOperator(MouseEvent event);
}
