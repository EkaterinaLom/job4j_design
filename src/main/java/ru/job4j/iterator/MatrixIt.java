package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        var rsl = false;
        while (data.length != 0 && row < data.length) {
            if (data[row].length != 0 && column < data[row].length) {
                rsl = true;
                break;
            }
                row++;
                column = 0;
        }
        return rsl;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
            return data[row][column++];
    }
}
