package ru.meeral.terminal;

import ru.meeral.terminal.exceptions.InsufficientFundsException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class TerminalServer {
    private static final String BALANCE_FILE = Paths.get("task4","src", "main", "java", "ru", "meeral", "terminal", "balance.txt").toString();
    private int balance;

    public TerminalServer() {
        this.balance = loadBalanceFromFile();
    }

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) throws InsufficientFundsException {
        if (balance < amount) {
            throw new InsufficientFundsException(String.format("Недостаточно средств для снятия %d рублей", amount));
        }
        balance -= amount;
        saveBalanceToFile();
    }

    public void deposit(int amount) {
        balance += amount;
        saveBalanceToFile();
    }

    private int loadBalanceFromFile() {
        File file = new File(BALANCE_FILE);
        if (!file.exists()) {
            return 0;
        }

        try (Scanner scanner = new Scanner(file)) {
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("balance=")) {
                    return Integer.parseInt(line.substring(8));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Ошибка при чтении файла баланса: ");
        }
        return 0;
    }

    private void saveBalanceToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BALANCE_FILE))) {
            writer.write("balance=" + balance);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении баланса: ");
        }
    }
}
