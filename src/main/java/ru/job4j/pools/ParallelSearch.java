package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearch<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final int from;
    private final int to;
    private final T objectSearch;

    public ParallelSearch(T[] array, int from, int to, T objectSearch) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.objectSearch = objectSearch;
    }

    @Override
    protected Integer compute() {
        if (array[from].equals(objectSearch)) {
            return from;
        }
        if (array[to].equals(objectSearch)) {
            return to;
        }
        int mid = (from + to) / 2;
        ParallelSearch<T> leftSort = new ParallelSearch<>(array, from, mid, objectSearch);
        ParallelSearch<T> rightSort = new ParallelSearch<>(array, mid + 1, to, objectSearch);
        leftSort.fork();
        rightSort.fork();
        return leftSort.join() + rightSort.join();
    }

    public int search(T[] array, T objectSearch) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return (int) forkJoinPool.invoke(new ParallelSearch<>(array, 0, array.length - 1, objectSearch));
    }
}
