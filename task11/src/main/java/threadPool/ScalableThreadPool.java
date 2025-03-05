package threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ScalableThreadPool implements ThreadPool {
    private final int minThreads;
    private final int maxThreads;
    private final BlockingQueue<Runnable> taskQueue;
    private final List<Thread> workers;
    private volatile boolean isRunning = true;

    public ScalableThreadPool(int min, int max) {
        this.minThreads = min;
        this.maxThreads = max;
        this.taskQueue = new LinkedBlockingQueue<>();
        this.workers = new ArrayList<>();
    }

    @Override
    public void start() {
        for (int i = 0; i < minThreads; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            worker.start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        synchronized (workers) {
            if (workers.size() < maxThreads) {
                Worker worker = new Worker();
                workers.add(worker);
                worker.start();
            }
            taskQueue.offer(runnable);
        }
    }

    public void shutdown() {
        isRunning = false;
        synchronized (workers) {
            for (Thread worker : workers) {
                worker.interrupt();
            }
            workers.clear();
        }
    }

    private class Worker extends Thread {
        @Override
        public void run() {
            while (isRunning) {
                try {
                    Runnable task = taskQueue.poll(1, TimeUnit.SECONDS);
                    if (task != null) {
                        task.run();
                    } else {
                        synchronized (workers) {
                            if (workers.size() > minThreads) {
                                workers.remove(this);
                                break;
                            }
                        }
                    }
                } catch (InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

}
