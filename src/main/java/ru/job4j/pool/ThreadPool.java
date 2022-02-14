package ru.job4j.pool;

import ru.job4j.pattern.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(3);

    public void work(Runnable job) {
        int size = Runtime.getRuntime().availableProcessors();
        if (threads.size() <= size) {
            threads.add(new Thread(
                    () -> {
                        try {
                            tasks.offer(job);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            ));
        }
    }

    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}