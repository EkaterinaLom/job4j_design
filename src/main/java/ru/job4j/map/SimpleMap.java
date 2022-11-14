package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capasity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capasity];

    @Override
    public boolean put(K key, V value) {
        boolean result = true;
        var index = indexBasket(key);
        if (table[index] != null) {
            result = false;
        } else {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        if (count == (int) (capasity * LOAD_FACTOR)) {
            expand();
            modCount++;
        }
        return result;
    }

    private int indexBasket(K key) {
        return indexFor(hash(key == null ? 0 : key.hashCode()));
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capasity - 1);
    }

    /**
     * расширение
     * @return
     */
    private void expand() {
        var oldTable = table;
        capasity *= 2;
        count = 0;
        table = new MapEntry[capasity];
        for (var entry : oldTable) {
            if (entry != null) {
                put(entry.key, entry.value);
            }
        }
    }

    @Override
    public V get(K key) {
        V result = null;
        int index = indexBasket(key);
        var entry = table[index];
        if (entry != null && Objects.hashCode(key) == Objects.hashCode(entry.key) && Objects.equals(key, entry.key)) {
                result = entry.value;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = true;
        int index = indexBasket(key);
        if (table[index] == null) {
            result = false;
        } else if (table[index] != null && Objects.hashCode(key) == Objects.hashCode(table[index].key) && Objects.equals(key, table[index].key)) {
            table[index] = null;
            count--;
            modCount++;
        }
        return result;
    }

    public int size() {
        return count;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {

            private final int expectedModCount = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(this);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            MapEntry<?, ?> entry = (MapEntry<?, ?>) o;
            return Objects.equals(key, entry.key) && Objects.equals(value, entry.value);
        }
    }
}