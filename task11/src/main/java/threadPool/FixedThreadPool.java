package threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FixedThreadPool implements ThreadPool {
    private final int threadCount;
    private final BlockingQueue<Runnable> taskQueue;
    private final List<Worker> workers;
    private volatile boolean isRunning = true;

    public FixedThreadPool(int threadCount) {
        this.threadCount = threadCount;
        this.taskQueue = new LinkedBlockingQueue<>();
        this.workers = new ArrayList<>();
    }

    @Override
    public void start() {
        for (int i = 0; i < threadCount; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            worker.start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (isRunning) {
            taskQueue.offer(runnable);
        }
    }

    public void shutdown() {
        isRunning = false;
        for (Worker worker : workers) {
            worker.interrupt();
        }
    }

    private class Worker extends Thread {
        @Override
        public void run() {
            while (isRunning) {
                try {
                    Runnable task = taskQueue.take();
                    task.run();
                } catch (InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}