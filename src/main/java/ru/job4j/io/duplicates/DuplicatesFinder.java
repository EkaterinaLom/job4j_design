package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), duplicatesVisitor);
        Map<FileProperty, Set<Path>> result = duplicatesVisitor.getDupl();
        for (FileProperty fileKey : result.keySet()) {
            if (result.get(fileKey).size() > 1) {
                System.out.printf("%s - %s%n", fileKey.getName(), fileKey.getSize());
                for (Path  valSet : result.get(fileKey)) {
                    System.out.println(valSet);
                }
            }
        }
    }
}
