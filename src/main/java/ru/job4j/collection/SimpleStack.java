package ru.job4j.collection;

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<T>();

    private int size;

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public T pop() {
        var rsl = linked.deleteFirst();
        size--;
        return rsl;
    }

    public void push(T value) {
        linked.addFirst(value);
        size++;
    }
}