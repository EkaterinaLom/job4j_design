package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {

    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    /**
     * deQueue
     * @return, должен возвращать первое значение и удалять его из коллекции
     */
    public T poll() {
        if (in.isEmpty()) {
            throw new NoSuchElementException();
        }
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    /**
     * enQueue
     * @param value, помещает значение в конец
     */
    public void push(T value) {
        in.push(value);
    }
}