package com.github.ElfenLiedGH.charting.utils;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Запоминаем уже нарисованные лейблы, что бы смешать при пересечении
 */
public class DrawnLabels {
    private final HashMap<Integer, HashSet<Integer>> labelsPosition = new HashMap<Integer, HashSet<Integer>>();
    // На сколько выше сместим лейбл на графике
    private static final int Y_PARALLAX = -10;
    private static final int X_PARALLAX = 5;
    // Сколько пикселей будем считать за единицу, например если 5, то позиции 103 и 104 приравняем к 105 и будем считать равными
    private static final float Y_ROUND = 5;
    private static final float X_ROUND = 5;


    private Integer convertValue(Float value, float positionRound) {
        float round = value % 10;
        int result = Math.round(value / 10);
        return (result * 10) + (round > positionRound ? 5 : 0);
    }

    public float[] add(Float x, Float y) {
        Float originalValueY = y;
        int convertedValueY = convertValue(originalValueY, Y_ROUND);
        int convertedValueX = convertValue(x, X_ROUND);

        HashSet<Integer> positionsY = labelsPosition.get(convertedValueX);
        // Добавим, если в этой позиции оси Х еще не было лейблов
        if (positionsY == null) {
            positionsY = new HashSet<>();
            labelsPosition.put(convertedValueX, positionsY);
        }

        // Смещаем лейбл вверх пока встречаем пересечения
        while (positionsY.contains(convertedValueY)) {
            convertedValueY += Y_PARALLAX;
            originalValueY += Y_PARALLAX;
        }

        // Добавляем новый лейбл
        positionsY.add(convertedValueY);
        return new float[]{x, originalValueY};
    }

    public HashMap<Integer, HashSet<Integer>> getData() {
        return labelsPosition;
    }
}