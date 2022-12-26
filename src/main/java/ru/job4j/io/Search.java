package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) throws IOException {
        valid(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void valid(String[] args) {
        if (args.length == 0 || args.length != 2) {
            throw new IllegalArgumentException("Arguments are null or invalid. Usage CLI_ARGUMENTS_TO_YOUR_APPLICATION.");
        }
        if (!Files.isDirectory(Path.of(args[0]))) {
            throw new IllegalArgumentException("Verify that the start folder is correct, check of args[0]");
        }
        if (!args[1].equals(".js")) {
            throw new IllegalArgumentException("Extension not specified correctly, check of args[1]");
        }
    }
}
