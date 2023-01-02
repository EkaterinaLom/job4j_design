package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArgsName validate(String[] args) {
        if (args.length < 3) {
            throw new IllegalArgumentException("Not all arguments are present");
        }
        ArgsName aname = ArgsName.of(args);
        if (!aname.get("e").startsWith(".")) {
            throw new IllegalArgumentException(String.format("Value for this argument key- '%s' not start with '.' .",
                    aname.get("e")));
        }
        if (!Path.of(aname.get("d")).toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("Directory - '%s' not exist", aname.get("d")));
        }
        return aname;
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        ArgsName name = validate(args);
        List<Path> list = Search.search(Paths.get(name.get("d")),
                path -> !path.toFile().getName().endsWith(name.get("e")));
        zip.packFiles(list, new File(name.get("o")));
    }
}