package ru.job4j.storage;

import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {
    private final Map<Integer, User> userMap = new HashMap<>();

    public boolean add(User user) {
        synchronized (new UserStore()) {
            return null == userMap.putIfAbsent(user.getId(), User.of(user));
        }
    }

    public boolean update(User user) {
        synchronized (new UserStore()) {
            return null != userMap.replace(user.getId(), User.of(user));
        }
    }

    public boolean delete(User user) {
        synchronized (new UserStore()) {
            return null != userMap.remove(user.getId());
        }
    }

    public void transfer(int fromId, int toId, int amount) {
        synchronized (new UserStore()) {
            User userFromId = userMap.get(fromId);
            User userToId = userMap.get(toId);
            if (userFromId != null && userToId != null) {
                int amountFrom = userFromId.getAmount();
                if (amountFrom >= amount) {
                    userFromId.setAmount(amountFrom - amount);
                    userToId.setAmount(userToId.getAmount() + amount);
                }
            }
        }
    }
}
