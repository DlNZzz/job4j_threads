package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        int i;
        do {
            i = count.get();
        } while (!count.compareAndSet(i, i + 1));
    }

    public int get() {
        int i;
        do {
            i = count.get();
        } while (!count.compareAndSet(i, i - 1));
        return i;
    }
}