package com.github.mikephil.charting.utils;

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
    // Сколько соседних ячеек проверять на наличиие лейблов справа и слева
    private static final int X_NEIGHBORS = (int) (5 * X_ROUND);
    // Сколько соседних ячеек проверять на наличиие лейблов сверху и снизу
    private static final int Y_NEIGHBORS = (int) (8 * Y_ROUND);


    private Integer convertValue(Float value, float positionRound) {
        float round = value % 10;
        int result = Math.round(value / 10);
        return (result * 10) + (round > positionRound ? 5 : 0);
    }

    private int[] searchIntersection(int x, int y) {
        int startStepX = Math.max(x - X_NEIGHBORS, 0);
        // Ходим по оси X ищем похожих соседей
        for (int stepX = startStepX; stepX <= x + X_NEIGHBORS; stepX += X_ROUND) {
            HashSet<Integer> positionsY = labelsPosition.get(stepX);
            if (positionsY == null) {
                continue;
            }
            int startStepY = Math.max(y + Y_NEIGHBORS, 0);
            int stopStepY = Math.max(y - Y_NEIGHBORS, 0);
            // Если нашли соседа, выходим, т.к. теперь надо начинать поиск заново с новым значением
            for (int stepY = startStepY; stepY >= stopStepY; stepY -= Y_ROUND) {
                if (positionsY.contains(stepY)) {
                    return new int[]{0, Y_PARALLAX};
                }
            }
        }
        return new int[]{0, 0};
    }

    public float[] add(Float x, Float y) {
        Float originalValueY = y;
        int convertedValueY = convertValue(originalValueY, Y_ROUND);
        int convertedValueX = convertValue(x, X_ROUND);


        int[] intersectionGap = searchIntersection(convertedValueX, convertedValueY);
        while (intersectionGap[1] != 0) {
            convertedValueY += intersectionGap[1];
            originalValueY += intersectionGap[1];
//            System.out.println("==>>: " + Arrays.toString(new float[]{x, y}) + "=>>" + Arrays.toString(new float[]{x, originalValueY}));
            intersectionGap = searchIntersection(convertedValueX, convertedValueY);
        }

        HashSet<Integer> positionsY = labelsPosition.get(convertedValueX);
        // Добавим, если в этой позиции оси Х еще не было лейблов
        if (positionsY == null) {
            positionsY = new HashSet<>();
            labelsPosition.put(convertedValueX, positionsY);
        }

        // Добавляем новый лейбл
        positionsY.add(convertedValueY);
        return new float[]{x, originalValueY};
    }

    public HashMap<Integer, HashSet<Integer>> getData() {
        return labelsPosition;
    }
}