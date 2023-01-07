package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() throws FileNotFoundException {
        var answers = readPhrases();
        var scanner = new Scanner(System.in);
        var log = new ArrayList<String>();
        var phrases = scanner.nextLine();
        while (!phrases.equals(OUT)) {
            log.add(phrases);
            if (phrases.equals(STOP)) {
                phrases = scanner.nextLine();
                while (!phrases.equals(CONTINUE)) {
                    log.add(phrases);
                    phrases = scanner.nextLine();
                }
                log.add(CONTINUE);
            } else {
                var answ = answers.get(new Random().nextInt(answers.size()));
                System.out.println(answ);
                log.add(answ);
            }
            phrases = scanner.nextLine();
        }
        log.add(OUT);
        saveLog(log);
    }

    private List<String> readPhrases() throws FileNotFoundException {
        List<String> answer = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
            answer = in.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(path)) {
            log.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        ConsoleChat cc = new ConsoleChat("/home/kate/Downloads/projects/job4j_design/log.txt",
                "/home/kate/Downloads/projects/job4j_design/dialogue.txt");
        cc.run();
    }
}
