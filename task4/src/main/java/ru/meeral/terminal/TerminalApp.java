package ru.meeral.terminal;

import ru.meeral.terminal.exceptions.AccountIsLockedException;
import ru.meeral.terminal.exceptions.InsufficientFundsException;
import ru.meeral.terminal.exceptions.InvalidAmountException;
import ru.meeral.terminal.exceptions.InvalidSessionException;
import ru.meeral.terminal.impl.TerminalImpl;
import java.util.Scanner;

public class TerminalApp {
    private final Terminal terminal;
    private final Scanner scanner;

    public TerminalApp(Terminal terminal) {
        this.terminal = terminal;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
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
                    case "1" -> handlePinInput();
                    case "2" -> terminal.checkBalance();
                    case "3" -> handleDeposit();
                    case "4" -> handleWithdraw();
                    case "5" -> exit();
                    default -> System.out.println("Некорректный выбор. Попробуйте снова.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void handlePinInput() throws AccountIsLockedException {
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

    private void handleDeposit() {
        System.out.println("Введите сумму для внесения (кратную 100):");
        try {
            int amount = Integer.parseInt(scanner.nextLine());
            terminal.deposit(amount);
        } catch (NumberFormatException | AccountIsLockedException | InvalidSessionException | InvalidAmountException e) {
            System.out.println("Ошибка: введена некорректная сумма. Пожалуйста, введите целое число.");
        }
    }

    private void handleWithdraw() {
        System.out.println("Введите сумму для снятия (кратную 100):");
        try {
            int amount = Integer.parseInt(scanner.nextLine());
            terminal.withdraw(amount);
        } catch (NumberFormatException | AccountIsLockedException | InvalidSessionException | InvalidAmountException |
                 InsufficientFundsException e) {
            System.out.println("Ошибка: введена некорректная сумма. Пожалуйста, введите целое число.");
        }
    }

    private void exit() {
        System.out.println("Программа завершена.");
        scanner.close();
        System.exit(0);
    }
}
