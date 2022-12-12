package ru.job4j.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) throws FileNotFoundException {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String lin : lines) {
                int num = Integer.parseInt(lin);
                boolean rsl = num % 2 == 0;
                if (rsl) {
                    System.out.println(num + " - чётное число");
                } else {
                    System.out.println(num + " - нечётное число");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
