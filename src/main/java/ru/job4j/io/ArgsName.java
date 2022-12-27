package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;

public class ArgsName {

    private final HashMap<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Parameters do not contain this key");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String arg : args) {
            valid(args);
            String[] strings = arg.split("=", 2);
            if (!(strings[0].length() > 1)) {
                throw new IllegalArgumentException("missing key");
            }
            if (strings[1].length() == 0) {
                throw new IllegalArgumentException("missing key value");
            }
            values.put(strings[0].substring(1), strings[1]);
        }
    }

    public void valid(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No arguments, enter arguments");
        }
        Arrays.stream(args).forEach(a -> {
            if (!a.startsWith("-") || !a.contains("=")) {
                throw new IllegalArgumentException("Incorrect argument");
            }
        });
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No arguments, enter arguments");
        }
        ArgsName name = new ArgsName();
        name.parse(args);
        return name;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}