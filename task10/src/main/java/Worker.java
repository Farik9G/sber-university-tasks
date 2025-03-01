import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Multithreading extends Thread {
    private static final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

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

