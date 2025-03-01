import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        String filePath = "task10/src/main/java/numbers.txt";

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

            for (String line : lines) {
                try {
                    int number = Integer.parseInt(line.trim());
                    executor.submit(new Multithreading(number));
                } catch (NumberFormatException e) {
                    System.err.println("Ошибка: некорректное число в файле - " + line);
                }
            }

            executor.shutdown();
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
    }
}
