package ru.job4j.pools;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ParallelSearchTest<T> {
    @Test
    public void test() {
        int objectSearch = 34;
        Integer[] array = new Integer[] {123, 312, 54, 34, 8, 32, 4, 8, 3, 56, 324, 435, 54, 6};
        ParallelSearch parallelSearch = new ParallelSearch(array, 0, 0, objectSearch);
        int received = parallelSearch.search(array, objectSearch);
        assertThat(received, is(3));
    }

    @Test
    public void testTwo() {
        int objectSearch = 56;
        Integer[] array = new Integer[] {123, 312, 54, 34, 8, 32, 4, 8, 3, 56};
        ParallelSearch parallelSearch = new ParallelSearch(array, 0, 0, objectSearch);
        int received = parallelSearch.search(array, objectSearch);
        assertThat(received, is(9));
    }

    @Test
    public void testThree() {
        int objectSearch = 5555555;
        Integer[] array = new Integer[] {123, 312, 54, 34, 8, 32, 4, 8, 3, 56};
        ParallelSearch parallelSearch = new ParallelSearch(array, 0, 0, objectSearch);
        int received = parallelSearch.search(array, objectSearch);
        assertThat(received, is(-1));
    }
}