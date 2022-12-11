package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int r = 1; r < 10; r++) {
                for (int c = 1; c < 10; c++) {
                    var rsl = Integer.toString(r * c);
                    out.write(rsl.getBytes());
                    out.write(System.lineSeparator().getBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}