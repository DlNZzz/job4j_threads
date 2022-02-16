package ru.job4j.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public void emailTo(User user) {
        String username = user.getUsername();
        String email = user.getEmail();
        String subject = String.format("Notification %s to email %s.", username, email);
        String body = String.format("Add a new event to %s", username);
        send(subject, body, email);
    }

    public void send(String subject, String body, String email) {
        pool.submit(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    public void close() {
        pool.shutdown();
    }
}
