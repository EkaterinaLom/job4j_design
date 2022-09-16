package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .isNotEqualTo("Tetrahedron")
                .isNotEmpty();
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 4);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron")
                .isNotEqualTo("Cube")
                .contains("Tetra");
    }

    @Test
    void isThereFourVertices() {
        Box box = new Box(4, 4);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(4)
                .isNotZero()
                .isPositive();
    }

    @Test
    void isThereEyghtVertices() {
        Box box = new Box(8, 6);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(8)
                .isNotZero()
                .isGreaterThan(7);
    }

    @Test
    void isExist() {
        Box box = new Box(0, 10);
        boolean rsl = box.isExist();
        assertThat(rsl).isTrue()
                .isEqualTo(true);
    }

    @Test
    void isNotExist() {
        Box box = new Box(7, 6);
        boolean rsl = box.isExist();
        assertThat(rsl).isFalse()
                .isEqualTo(false);
    }

    @Test
    void theAreaOfSphere() {
        Box box = new Box(0, 10);
        double rsl = box.getArea();
        assertThat(rsl).isNotZero()
                .isGreaterThan(44.7)
                .isEqualTo(1256.6370614359173);
    }

    @Test
    void theAreaOfCube() {
        Box box = new Box(8, 6);
        double rsl = box.getArea();
        assertThat(rsl).isNotZero()
                .isLessThan(10000.6)
                .isEqualTo(216.0);
    }
}