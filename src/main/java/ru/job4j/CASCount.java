package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        if (count.get() == null) {
            count.set(0);
        }
        int i;
        do {
            i = count.get();
        } while (!count.compareAndSet(i, i + 1));
    }

    public int get() {
        return count.get();
    }
}