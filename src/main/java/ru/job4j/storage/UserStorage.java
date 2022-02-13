package ru.job4j.storage;

import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {
    private final Map<Integer, User> userMap = new HashMap<>();

    public synchronized boolean add(User user) {
        return null == userMap.putIfAbsent(user.getId(), User.of(user));
    }

    public synchronized boolean update(User user) {
        return null != userMap.replace(user.getId(), User.of(user));
    }

    public synchronized boolean delete(User user) {
        return userMap.remove(user.getId(), user);
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        User userFromId = userMap.get(fromId);
        User userToId = userMap.get(toId);
        if (userFromId != null && userToId != null && userFromId.getAmount() >= amount) {
            userFromId.setAmount(userFromId.getAmount() - amount);
            userToId.setAmount(userToId.getAmount() + amount);
        }
    }
}
