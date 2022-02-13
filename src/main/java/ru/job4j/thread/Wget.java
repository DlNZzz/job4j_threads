package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

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
            int fileSize = 0;
            Date oldDate = new Date();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileSize += bytesRead;
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            Date newDate = new Date();
            if ((newDate.getTime() - oldDate.getTime()) / 1000 > fileSize / speed) {
                Thread.sleep((newDate.getTime() - oldDate.getTime()));
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
            throw new IllegalArgumentException("необходимо 3 параметра");
        }
        return true;
    }
}