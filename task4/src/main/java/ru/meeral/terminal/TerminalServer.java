package terminal;

import terminal.exceptions.InsufficientFundsException;

public class TerminalServer {
    private static final String BALANCE_FILE = "balance.txt";
    private int balance = 0;

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) throws InsufficientFundsException {
        if (balance < amount) {
            throw new InsufficientFundsException(String.format("Недостаточно средств для снятия %d рублей", amount));
        }
        balance -= amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }
}
