package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(), (k, v) -> {
                    if (v.getVersion() != model.getVersion()) {
                        throw new OptimisticException("Versions are not equal");
                    }
                    memory.put(k, new Base(model.getId(), model.getVersion() + 1));
                    return v;
                }
        ) != null;
    }

    public void delete(Base model) {
        memory.computeIfPresent(model.getId(), (k, v) -> memory.remove(k));
    }
}