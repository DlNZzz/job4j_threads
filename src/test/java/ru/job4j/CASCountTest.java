package ru.job4j;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CASCountTest {

    @Test
    public void test() throws InterruptedException {
        CASCount casCount = new CASCount();
        Thread first = new Thread(
                () -> {
                    for (int i = 0; i < 6; i++) {
                        casCount.increment();
                    }
                }
        );
        first.start();
        Thread second = new Thread(
                () -> {
                    for (int i = 0; i < 4; i++) {
                        casCount.increment();
                    }
                }
        );
        second.start();
        first.join();
        second.join();
        assertThat(casCount.get(), is(10));
    }

    @Test
    public void testTwo() throws InterruptedException {
        CASCount casCount = new CASCount();
        Thread first = new Thread(
                () -> {
                    for (int i = 0; i < 6; i++) {
                        casCount.increment();
                    }
                }
        );
        first.start();
        Thread second = new Thread(
                () -> {
                    for (int i = 0; i < 42; i++) {
                        casCount.increment();
                    }
                }
        );
        second.start();
        first.join();
        second.join();
        assertThat(casCount.get(), is(48));
    }
}