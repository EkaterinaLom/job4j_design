package ru.job4j.io;

import java.io.*;

public class Analysis {

    public static void unavailable(String sourse, String target) {
        boolean work = true;
        try (BufferedReader read = new BufferedReader(new FileReader(sourse));
             PrintWriter writer = new PrintWriter(target)) {
            while (read.ready()) {
                String[] strings = read.readLine().split(" ");
                if (work && (strings[0].contains("400") || strings[0].contains("500"))) {
                    work = false;
                    writer.printf("%s;", strings[1]);
                } else if (!work && (strings[0].contains("200") || strings[0].contains("300"))) {
                    work = true;
                    writer.printf("%s;\n", strings[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        unavailable("server.log", "target.csv");
    }
}