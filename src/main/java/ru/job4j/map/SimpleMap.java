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
        return putEntry(new MapEntry<>(key, value));
    }

    private boolean putEntry(MapEntry<K, V> entry) {
        boolean result;
        var index = indexFor(hash(hashCodeOf(entry.key)));
        if (table[index] != null) {
            result = false;
        } else {
            table[index] = entry;
            count++;
            modCount++;
            result = true;
        }
        if (count == (int) (capasity * LOAD_FACTOR)) {
            expand();
            modCount++;
        }
        return result;
    }

    private int hashCodeOf(Object o) {
        return o == null ? 0 : o.hashCode();
    }

    private int hash(int hashCode) {
        return hashCode;
    }

    private int indexFor(int hash) {
        return hash % capasity;
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
                putEntry(entry);
            }
        }
    }

    @Override
    public V get(K key) {
        V result = null;
        int index = indexFor(hash(hashCodeOf(key)));
        var entry = table[index];
        if (entry != null && keysEqual(key, entry.key)) {
                result = entry.value;
        }
        return result;
    }

    private boolean keysEqual(K key1, K key2) {
        boolean result = false;
        if (key1 == null && key2 == null) {
            result = true;
        } else if (key1 != null && key2 != null) {
            result = key1.equals(key2);
        }
        return result;
    }


    @Override
    public boolean remove(K key) {
        boolean result = true;
        int index = indexFor(hash(hashCodeOf(key)));
        if (table[index] == null) {
            result = false;
        } else if (keysEqual(key, table[index].key)) {
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
            int result = key != null ? key.hashCode() : 0;
            result = 31 * result + (value != null ? value.hashCode() : 0);
            return result;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return  false;
            }
            MapEntry<?, ?> mapEntry = (MapEntry<?, ?>) o;
            if (!Objects.equals(key, mapEntry.key)) {
                return false;
            }
            return Objects.equals(value, mapEntry.value);
        }
    }
}