package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenBlankLines() {
        String path = "./data/pair_blank_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.driver_class")).isEqualTo("org.postgresql.Driver");
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenTwoSignsEqualTo() {
        String path = "./data/pair_two_signs_equal_to.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev=");
    }

    @Test
    void whenPatternViolation() throws IllegalArgumentException {
        String path = "./data/pair_pattern_violation.properties";
        Config config = new Config(path);
        assertThrows(IllegalArgumentException.class, config::load);
    }

    @Test
    void whenPatternViolationNoValue() throws IllegalArgumentException {
        String path = "./data/pair_mistake_no_value.properties";
        Config config = new Config(path);
        assertThrows(IllegalArgumentException.class, config::load);
    }

    @Test
    void whenPatternViolationMissingAKey() throws IllegalArgumentException {
        String path = "./data/pair_mistake_no_key.properties";
        Config config = new Config(path);
        assertThrows(IllegalArgumentException.class, config::load);
    }
}