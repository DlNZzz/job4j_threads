package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {

    private final String url;
    private final int speed;
    private static String file;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (valid(args)) {
            String url = args[0];
            int speed = Integer.parseInt(args[1]);
            file = args[2];
            Thread wget = new Thread(new Wget(url, speed));
            wget.start();
            wget.join();
        }
    }

    private static boolean valid(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("необходимо 3 параметра");
        }
        return true;
    }
}