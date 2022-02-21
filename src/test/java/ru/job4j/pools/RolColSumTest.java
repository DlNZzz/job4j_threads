package ru.job4j.pools;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RolColSumTest {

    @Test
    public void testSum() {
        int[][] array = new int[][] {{12, 31, 5}, {34, 8, 32}, {4, 8, 3}};
        RolColSum.Sums[] sumsExpected = new RolColSum.Sums[3];
        for (int i = 0; i < sumsExpected.length; i++) {
            sumsExpected[i] = new RolColSum.Sums();
        }
        sumsExpected[0].setColSum(50);
        sumsExpected[0].setRowSum(48);
        sumsExpected[1].setColSum(47);
        sumsExpected[1].setRowSum(74);
        sumsExpected[2].setColSum(40);
        sumsExpected[2].setRowSum(15);
        RolColSum.Sums[] sumsReceived = RolColSum.sum(array);
        assertThat(sumsReceived, is(sumsExpected));
    }

    @Test
    public void testAsyncSum() throws ExecutionException, InterruptedException {
        int[][] array = new int[][] {{12, 31, 5}, {34, 8, 32}, {4, 8, 3}};
        RolColSum.Sums[] sumsExpected = new RolColSum.Sums[3];
        for (int i = 0; i < sumsExpected.length; i++) {
            sumsExpected[i] = new RolColSum.Sums();
        }
        sumsExpected[0].setColSum(50);
        sumsExpected[0].setRowSum(48);
        sumsExpected[1].setColSum(47);
        sumsExpected[1].setRowSum(74);
        sumsExpected[2].setColSum(40);
        sumsExpected[2].setRowSum(15);
        RolColSum.Sums[] sumsReceived = RolColSum.asyncSum(array);
        assertThat(sumsReceived, is(sumsExpected));
    }
}