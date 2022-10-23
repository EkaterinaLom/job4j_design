package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int modCount;
    private int size;
    private Node<E> head = null;

    private static class Node<E> {
        private E data;
        private Node<E> next;

        public Node(E data) {
            this.data = data;
        }
    }

    @Override
    public void add(E value) {
        Node<E> newNode = new Node<E>(value);
        if (size == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<E> first = head;
            while (first.next != null) {
                first = first.next;
            }
            first.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> found = head;
        for (int i = 0; i < index; i++) {
                found = found.next;
            }
        return found.data;
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {

            private Node<E> newNode = head;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return newNode != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> found = newNode;
                newNode = newNode.next;
                return found.data;
            }
        };
    }
}
