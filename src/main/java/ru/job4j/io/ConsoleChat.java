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

    public void run() {
        var answers = readPhrases();
        var scanner = new Scanner(System.in);
        var log = new ArrayList<String>();
        var phrases = scanner.nextLine();
        var random = new Random();
        while (!OUT.equals(phrases)) {
            log.add(phrases);
            if (STOP.equals(phrases)) {
                phrases = scanner.nextLine();
                while (!CONTINUE.equals(phrases)) {
                    log.add(phrases);
                    phrases = scanner.nextLine();
                }
                log.add(CONTINUE);
            } else {
                var randomAnsw = answers.get(random.nextInt(answers.size()));
                System.out.println(randomAnsw);
                log.add(randomAnsw);
            }
            phrases = scanner.nextLine();
        }
        log.add(OUT);
        saveLog(log);
    }

    private List<String> readPhrases() {
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
