import threadPool.FixedThreadPool;
import threadPool.ScalableThreadPool;
import threadPool.ThreadPool;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Тест FixedThreadPool");
        testThreadPool(new FixedThreadPool(2));

        System.out.println("\nТест ScalableThreadPool");
        testThreadPool(new ScalableThreadPool(2, 5));

    }

    private static void testThreadPool(ThreadPool threadPool) throws InterruptedException {
        threadPool.start();

        for (int i = 1; i <= 20 ; i++) {
            final int taskId = i;
            threadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " выполняет задачу " + taskId);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        Thread.sleep(5000);
        if (threadPool instanceof FixedThreadPool) {
            ((FixedThreadPool) threadPool).shutdown();
        } else if (threadPool instanceof ScalableThreadPool) {
            ((ScalableThreadPool) threadPool).shutdown();
        }
    }
}
