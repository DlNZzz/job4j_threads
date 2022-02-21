package ru.job4j.pools;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Sums sums = (Sums) o;
            return rowSum == sums.rowSum && colSum == sums.colSum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowSum, colSum);
        }

        @Override
        public String toString() {
            return "Sums{"
                    + "rowSum=" + rowSum
                    + ", colSum=" + colSum
                    + '}';
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            sums[i] = new RolColSum.Sums();
            for (int num : matrix[i]) {
                sums[i].rowSum += num;
            }
            for (int j = 0; j < matrix.length; j++) {
                sums[i].colSum += matrix[j][i];
            }
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            sums[i] = new RolColSum.Sums();
            sums[i].rowSum = getRow(sums, i, matrix).get();
            sums[i].colSum = getCol(sums, i, matrix).get();
        }
        return sums;
    }

    public static CompletableFuture<Integer> getRow(Sums[] sums, int i, int[][] matrix) {
        return CompletableFuture.supplyAsync(
                () -> {
                    for (int j = 0; j < matrix.length; j++) {
                        sums[i].rowSum += matrix[i][j];
                    }
                    return sums[i].rowSum;
                }
        );
    }

    public static CompletableFuture<Integer> getCol(Sums[] sums, int i, int[][] matrix) {
        return CompletableFuture.supplyAsync(
                () -> {
                    for (int j = 0; j < matrix.length; j++) {
                        sums[i].colSum += matrix[j][i];
                    }
                    return sums[i].colSum;
                }
        );
    }
}