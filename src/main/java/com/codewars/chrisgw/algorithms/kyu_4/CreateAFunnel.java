package com.codewars.chrisgw.algorithms.kyu_4;

import java.util.Arrays;

/**
 * <h2>Create a funnel</h2>
 * <a href="https://www.codewars.com/kata/585b373ce08bae41b800006e">https://www.codewars.com/kata/585b373ce08bae41b800006e</a>
 */
public class CreateAFunnel {

    private final char[][] funnel;


    public CreateAFunnel() {
        this(5);
    }

    public CreateAFunnel(int height) {
        funnel = new char[height][];
        for (int row = 0; row < height(); row++) {
            funnel[row] = new char[row + 1];
            Arrays.fill(funnel[row], ' ');
        }
    }


    public void fill(Character... args) {
        for (char value : args) {
            fill(value);
        }
    }

    public void fill(char value) {
        for (int row = 0; row < height(); row++) {
            char[] funnelRow = funnel[row];
            for (int column = 0; column < funnelRow.length; column++) {
                if (funnelRow[column] == ' ') {
                    funnelRow[column] = value;
                    return;
                }
            }
        }
    }


    public Character drip() {
        char drippedValue = funnel[0][0];
        fillPlace(0, 0);
        return drippedValue;
    }

    private void fillPlace(int row, int column) {
        funnel[row][column] = ' ';
        System.out.println(this);
        int rowAbove = row + 1;
        int leftWeight = weight(rowAbove, column, column);
        int rightWeight = weight(rowAbove, column + 1, column + 1);
        if (rightWeight > 0 && leftWeight >= rightWeight) {
            funnel[row][column] = funnel[rowAbove][column];
            fillPlace(rowAbove, column);
        } else if (rightWeight > 0) {
            funnel[row][column] = funnel[rowAbove][column + 1];
            fillPlace(rowAbove, column + 1);
        }
    }

    private int weight(int row, int from, int to) {
        if (row >= height()) {
            return 0;
        }
        int weight = 0;
        char[] funnelRow = funnel[row];
        for (int column = from; column <= to; column++) {
            char value = funnelRow[column];
            if (value != ' ') {
                weight++;
            }
        }
        weight += weight(row + 1, from, to + 1);
        return weight;
    }


    public int height() {
        return funnel.length;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = height() - 1; row >= 0; row--) {
            sb.append(" ".repeat(height() - row - 1));
            sb.append("\\");
            for (int column = 0; column < funnel[row].length; column++) {
                char value = funnel[row][column];
                sb.append(value).append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append('/').append('\n');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

}
