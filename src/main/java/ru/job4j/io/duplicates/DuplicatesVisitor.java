package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Set<FileProperty> originalFile = new HashSet<>();
    private final List<FileProperty> duplicate = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty property = new FileProperty(file.toFile().length(), file.getFileName().toString());
        if (!originalFile.add(property)) {
            duplicate.add(property);
        }
        return super.visitFile(file, attrs);
    }

    public void printFile() {
        duplicate.forEach(duplicate -> System.out.print(duplicate.getName()));
    }
}
