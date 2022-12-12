package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            int count = 0;
            for (int r = 1; r <= 9; r++) {
                for (int c = 1; c <= 9; c++) {
                    var rsl = Integer.toString(r * c);
                    out.write(rsl.getBytes());
                    count++;
                    if (count == 9) {
                        out.write(System.lineSeparator().getBytes());
                        count = 0;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}