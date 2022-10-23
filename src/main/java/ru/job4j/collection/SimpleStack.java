package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<T>();

    private Node<T> head = null;

    public void addFirst(T value) {
        Node<T> newFirst = new Node<T>(value);
        newFirst.next = head;
        head = newFirst;
    }

    public T deleteFirst() {
        Node<T> oldFirst = head;
        if (head != null) {
            head = head.next;
        } else {
            throw new NoSuchElementException();
        }
        return oldFirst.value;
    }

    public T pop() {
        return deleteFirst();
    }

    public void push(T value) {
        addFirst(value);
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
        }

    }
}