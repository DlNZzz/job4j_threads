package ru.job4j.cache;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CacheTest {

    @Test
    public void testAdd() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        assertTrue(cache.add(base));
        assertFalse(cache.add(base));
    }

    @Test
    public void testUpdate() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        Base base1 = new Base(1, 1);
        cache.add(base);
        assertTrue(cache.update(base));
        assertTrue(cache.update(base1));
    }

    @Test(expected = OptimisticException.class)
    public void testUpdateException() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        cache.add(base);
        cache.add(base);
        assertTrue(cache.update(base));
        cache.update(base);
    }
}
