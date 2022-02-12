package ru.job4j.storage;

import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {

    private final UserStore userStore = new UserStore();
    private final Map<Integer, User> userMap = new HashMap<>();

    public boolean add(User user) {
        synchronized (userStore) {
            if (!userMap.containsKey(user.getId())) {
                userMap.put(user.getId(), User.of(user));
                return true;
            }
            return false;
        }
    }

    public boolean update(User user) {
        synchronized (userStore) {
            if (userMap.containsKey(user.getId())) {
                userMap.put(user.getId(), User.of(user));
                return true;
            }
            return false;
        }
    }

    public boolean delete(User user) {
        synchronized (userStore) {
            if (userMap.containsKey(user.getId())) {
                userMap.remove(user.getId());
                return true;
            }
            return false;
        }
    }

    public void transfer(int fromId, int toId, int amount) {
        synchronized (userStore) {
            User userFromId = userMap.get(fromId);
            User userToId = userMap.get(toId);
            userFromId.reduceAmount(amount);
            userToId.addAmount(amount);
        }
    }
}
