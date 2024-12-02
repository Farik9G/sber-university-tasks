import terminal.PinValidator;
import terminal.Terminal;
import terminal.impl.TerminalImpl;
import terminal.TerminalServer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TerminalServer server = new TerminalServer();
        PinValidator pinValidator = new PinValidator();
        Terminal terminal = new TerminalImpl(server, pinValidator);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Ввести пин-код");
            System.out.println("2. Проверить баланс");
            System.out.println("3. Внести деньги");
            System.out.println("4. Снять деньги");
            System.out.println("5. Выйти");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1" -> {
                        System.out.println("Введите пин-код (по одной цифре за раз):");
                        for (int i = 0; i < 4; i++) {
                            String input = scanner.nextLine();
                            if (input.length() != 1 || !Character.isDigit(input.charAt(0))) {
                                System.out.println("Ошибка: введите ровно одну цифру.");
                                i--;
                                continue;
                            }
                            char digit = input.charAt(0);
                            terminal.enterPin(digit);
                        }
                    }
                    case "2" -> terminal.checkBalance();
                    case "3" -> {
                        System.out.println("Введите сумму для внесения (кратную 100):");
                        int amount = Integer.parseInt(scanner.nextLine());
                        terminal.deposit(amount);
                    }
                    case "4" -> {
                        System.out.println("Введите сумму для снятия (кратную 100):");
                        int amount = Integer.parseInt(scanner.nextLine());
                        terminal.withdraw(amount);
                    }
                    case "5" -> {
                        System.out.println("Программа завершена.");
                        scanner.close();
                        return;
                    }
                    default -> System.out.println("Некорректный выбор. Попробуйте снова.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
