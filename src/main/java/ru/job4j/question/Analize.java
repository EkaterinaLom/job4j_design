package ru.job4j.question;

import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previos, Set<User> current) {

        int changet = 0;
        int deleted = 0;

        var currMap = current.stream().collect(Collectors.toMap(User::getId, User::getName));
        for (User prev : previos) {
            if (!currMap.containsKey(prev.getId())) {
                deleted++;
            }
            if (currMap.containsKey(prev.getId()) && !currMap.containsValue(prev.getName())) {
                changet++;
            }
        }
        return new Info(current.size() - previos.size() + deleted, changet, deleted);
    }
}