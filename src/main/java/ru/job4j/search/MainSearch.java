package ru.job4j.search;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MainSearch {

    public static List<Path> found(ArgsName argsName) throws IOException {
        String typeFound = argsName.get("t");
        List<Path> foundPath = new ArrayList<>();
        var startFound = Paths.get(argsName.get("d"));
        if ("mask".equals(typeFound)) {
            foundPath = Search.search(startFound, path -> path.toFile().getName()
                    .matches(argsName.get("n").replace(".", "[.]")
                    .replace("*", ".+")
                    .replace("?", ".")));
        }
        if ("name".equals(typeFound)) {
            foundPath = Search.search(startFound, path -> path.toFile().getName()
                    .endsWith(argsName.get("n")));
        }
        if ("regex".equals(typeFound)) {
            foundPath = Search.search(startFound, path -> path.toFile().getName()
                    .matches(argsName.get("n")));
        }
        return foundPath;
    }

    public static void writeIn(List<Path> found, String path) {
        try (PrintStream out = new PrintStream(new FileOutputStream(path))) {
            for (Path f : found) {
                found.forEach(out::println);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void validArg(ArgsName argsName) {
        File path = Paths.get(argsName.get("d")).toFile();
        if (!path.exists()) {
            throw new IllegalArgumentException(String.format("%s - is not exist", argsName.get("d")));
        }
        if (!path.isDirectory()) {
            throw new IllegalArgumentException(String.format("%s - is not directory", argsName.get("d")));
        }
        if (argsName.get("n").isEmpty()) {
            throw new IllegalArgumentException(String.format("%s - file have not a name, or mask, or regex",
                    argsName.get("n")));
        }
        if (argsName.get("t").isEmpty()) {
            throw new IllegalArgumentException(String.format("%s - search type not correctly specified",
                    argsName.get("t")));
        }
        if (argsName.get("o").isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("%s - file for the search result record is not correctly specified",
                            argsName.get("o")));
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("Check the number of arguments specified. There must be 4 of them.");
        }
        ArgsName arg = ArgsName.of(args);
        validArg(arg);
        writeIn(found(arg), arg.get("o"));
    }
}
