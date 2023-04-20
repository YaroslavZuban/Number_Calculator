package com.example.number_calculator;

import javafx.scene.input.MouseEvent;

/**
 * Данный класс представляет собой интерфейс слушателя для текстового редактора
 */
public interface TextEditingListener {
     void onBackSpace(MouseEvent event);
     void onClean(MouseEvent event);
     void onCleanEntry(MouseEvent event);
}
