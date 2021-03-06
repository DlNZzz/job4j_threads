package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {

    private static String file;
    private final String url;
    private final int speed;

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
            long timeStart = System.currentTimeMillis();
            long bytesWrited = 0;
            long deltaTime = 1000;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                bytesWrited += bytesRead;
                if (bytesWrited >= speed) {
                    bytesWrited -= speed;
                    long newDate = System.currentTimeMillis();
                    long currentTime = newDate - timeStart;
                    if (currentTime < deltaTime) {
                        Thread.sleep(deltaTime - currentTime);
                    }
                    timeStart = System.currentTimeMillis();
                }
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
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
            throw new IllegalArgumentException("???????????????????? 3 ??????????????????");
        }
        return true;
    }
}