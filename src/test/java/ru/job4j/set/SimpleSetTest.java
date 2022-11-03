package ru.job4j.set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.assertj.core.api.Assertions.assertThat;

class SimpleSetTest {

    @Test
    void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
    }

    @Test
    void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.add(null)).isFalse();
    }

    @Test
    void whenIteratorHasNext() {
        Set<Integer> set = new SimpleSet<>();
        Iterator<Integer> it = set.iterator();
        assertThat(set.add(3)).isTrue();
        assertThat(set.add(4)).isTrue();
        assertThat(set.add(null)).isTrue();
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(3);
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(4);
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(null);
        assertThat(it.hasNext()).isFalse();
    }

    @Test
    void whenGetIteratorFromEmptySetThenNextThrowException() {
        Set<Integer> set = new SimpleSet<>();
        Assertions.assertThatThrownBy(set.iterator()::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenGetIteratorTwiceThenStartAlwaysFromBeginning() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(3)).isTrue();
        assertThat(set.add(4)).isTrue();
        assertThat(set.iterator().next()).isEqualTo(3);
        assertThat(set.iterator().next()).isEqualTo(3);
    }
}