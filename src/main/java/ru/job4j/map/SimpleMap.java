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
        var index = hash(entry.key);
        if (table[index] != null) {
            return false;
        } else {
            table[index] = entry;
            result = true;
            count++;
            modCount++;
        }
        if (count == (int) (capasity * LOAD_FACTOR)) {
            expand();
        }
        return result;
    }

    private int hash(Object key) {
        return (key == null) ? 0 : key.hashCode() % capasity;
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
        int index = hash(key);
        var entry = table[index];
        return entry == null ? null : entry.value;
    }

    @Override
    public boolean remove(K key) {
        boolean result;
        int index = hash(key);
        if (table[index] == null) {
            result = false;
        } else {
            table[index] = null;
            count--;
            modCount++;
            result = true;
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
            private List<K> keys;
            private int index = 0;

            {
                var keySet = new HashSet<K>();
                for (var entry : table) {
                    if (entry != null) {
                        keySet.add(entry.key);
                    }
                }
                keys = new ArrayList<>(keySet);
            }

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return !keys.isEmpty() && keys.size() > index;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return keys.get(index++);
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