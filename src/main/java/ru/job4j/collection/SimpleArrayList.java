package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            container = enlarge(container);
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T setOld = get(index);
        container[index] = newValue;
        return setOld;
    }

    @Override
    public T remove(int index) {
        T rem = get(index);
        if ((container.length - 1) > index) {
            System.arraycopy(container, index + 1, container, index, container.length - index - 1);
            container[size - 1] = null;
            size--;
            modCount++;
        }
        return rem;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            final int expectedModCount = modCount;
            int index;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];
            }
        };
    }

    public T[] enlarge(T[] container) {
        return container.length == 0 ? Arrays.copyOf(container, 2)
                : Arrays.copyOf(container, (container.length - 1) * 2);
    }
}
