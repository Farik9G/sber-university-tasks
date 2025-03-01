import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    private static final int NUM_WORKERS = 2;
    private static final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
    public static void main(String[] args) {
        String filePath = "task10/src/main/java/numbers.txt";

        for (int i = 0; i < NUM_WORKERS; i++) {
            new Worker(queue).start();
        }

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            for (String line : lines) {
                try {
                    int number = Integer.parseInt(line.trim());
                    queue.put(number);
                } catch (NumberFormatException e) {
                    System.err.println("Ошибка: некорректное число в файле - " + line);
                }
            }

            for (int i = 0; i < NUM_WORKERS; i++) {
                queue.put(-1);
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}
