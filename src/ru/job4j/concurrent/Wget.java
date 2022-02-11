package ru.job4j.concurrent;

public class Wget {

    public static void main(String[] args) {
        Thread thread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 100; i++) {
                            System.out.print("\rLoading : " + i  + "%");
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );
        thread.start();
    }
}
