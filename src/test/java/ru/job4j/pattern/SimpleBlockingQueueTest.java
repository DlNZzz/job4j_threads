package ru.job4j.pattern;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleBlockingQueueTest {

    @Test
    public void test() throws InterruptedException {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>(3);
        List<Integer> list = new ArrayList<>();
        Thread producer = new Thread(
                () -> {
                    try {
                        simpleBlockingQueue.offer(2);
                        simpleBlockingQueue.offer(3);
                        simpleBlockingQueue.offer(4);
                        simpleBlockingQueue.offer(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                },
                "Producer"
        );
        Thread consumer = new Thread(
                () -> {
                    try {
                        list.add(simpleBlockingQueue.poll());
                        list.add(simpleBlockingQueue.poll());
                        list.add(simpleBlockingQueue.poll());
                        list.add(simpleBlockingQueue.poll());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                },
                "Consumer"
        );
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(list.get(0), is(2));
        assertThat(list.get(1), is(3));
        assertThat(list.get(2), is(4));
        assertThat(list.get(3), is(5));
    }
}