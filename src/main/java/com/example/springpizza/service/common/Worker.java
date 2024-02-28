package com.example.springpizza.service.common;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Worker {

    ConcurrentLinkedQueue<Runnable> jobs = new ConcurrentLinkedQueue<>();

    public void addJob(Runnable job) {
        jobs.add(job);
    }

    public void startWork() {
        Thread thread = new Thread(() -> {
            while (true) {
                while (jobs.isEmpty()) {
                    Thread.yield();
                }
                jobs.poll().run();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
