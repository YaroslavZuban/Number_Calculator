package com.example.number_calculator;

import javafx.scene.input.MouseEvent;

/**
 * интерфейс Editor с методами, которые должны быть реализованы в любом классе, который реализует этот интерфейс.
 * Методы включают функциональность для взаимодействия с графическим интерфейсом пользователя,
 * такие как вставка символов и чисел, выбор операторов,
 * обработка нажатий на клавиши, очистка введенных данных и управление памятью.
 */
public interface Editor {
     void waterSymbol(MouseEvent event);
     void waterNumber(MouseEvent event);

     void waterOperator(MouseEvent event);

     void onResultClicked();
     void actionOperator(MouseEvent event);
     void onBaskSpace(MouseEvent event);

     void onClean(MouseEvent event);

     void onCleanEntry(MouseEvent event);
     void memory(MouseEvent event);

}
