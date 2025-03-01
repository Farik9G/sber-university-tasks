import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

public class Worker extends Thread {

    private final BlockingQueue<Integer> queue;

    public Worker(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int number = queue.take();

                if (number == -1) {
                    break;
                }

                BigInteger result = calculateFactorial(number);
                System.out.println("Поток: " + Thread.currentThread().getName() + " | Факториал " + number + " = " + result);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private BigInteger calculateFactorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }


}

