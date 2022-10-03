package ru.job4j.generics;

public class Animal {
    private String habitat;
    private String food;

    public Animal(String habitat, String food) {
        this.habitat = habitat;
        this.food = food;
    }

    public String getHabitat() {
        return habitat;
    }

    public String getFood() {
        return food;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }
}
