package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.data.Index;

import java.util.List;
import java.util.Map;
import java.util.Set;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).first().isEqualTo("first");
        assertThat(list).hasSize(5)
                .containsExactlyInAnyOrder("second", "first", "five", "three", "four")
                .startsWith("first", "second")
                .endsWith("four", "five")
                .last().isEqualTo("five");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "five");
        assertThat(set).isNotNull()
                .allSatisfy(e -> {
                    assertThat(e.length()).isLessThan(9);
                })
                .containsExactlyInAnyOrder("second", "first", "five", "three", "four")
                .anyMatch(e -> e.length() == 4)
                .noneMatch(e -> e.length() < 1);
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(map).hasSize(5)
                .containsKeys("first", "second", "five")
                .containsValues(0, 1, 2, 3, 4)
                .doesNotContainValue(5)
                .containsEntry("first", 0);
    }
}