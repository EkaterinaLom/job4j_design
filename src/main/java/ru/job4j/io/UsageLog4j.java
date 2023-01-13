package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        byte capSize = 61;
        short areaHome = 1500;
        char nicknameFirst = 'P';
        long livedDays = 11979;
        float favoritNumber = 3.14f;
        boolean result = true;
        double bactFavoritKefir = 786868768878543.56d;
        LOG.debug("User info name : {}, age : {}, capSize : {}, areaHome : {}, nicknameFirst : {},"
                + " livedDays : {}, favoritNumber : {}, result : {}, bactFavoritKefir : {}", name, age, capSize,
                areaHome, nicknameFirst, livedDays, favoritNumber, result, bactFavoritKefir);
    }
}
