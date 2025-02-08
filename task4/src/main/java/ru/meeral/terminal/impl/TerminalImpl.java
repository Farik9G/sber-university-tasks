package ru.meeral.terminal.impl;

import ru.meeral.terminal.PinValidator;
import ru.meeral.terminal.TerminalServer;
import ru.meeral.terminal.Terminal;
import ru.meeral.terminal.exceptions.AccountIsLockedException;
import ru.meeral.terminal.exceptions.InsufficientFundsException;
import ru.meeral.terminal.exceptions.InvalidAmountException;
import ru.meeral.terminal.exceptions.InvalidSessionException;

public class TerminalImpl implements Terminal {
    private final TerminalServer server;
    private final PinValidator pinValidator;
    private boolean sessionActive = false;

    public TerminalImpl(TerminalServer server, PinValidator pinValidator) {
        this.server = server;
        this.pinValidator = pinValidator;
    }

    @Override
    public void enterPin(char digit) throws AccountIsLockedException {
        boolean isPinComplete = pinValidator.validate(digit);

        if (isPinComplete) {
            sessionActive = true;
            System.out.println("Пин-код принят. Доступ к терминалу открыт.");
        }
    }

    @Override
    public void checkBalance() throws AccountIsLockedException, InvalidSessionException {
        ensureSessionActive();
        System.out.printf("Текущий баланс: %d рублей%n", server.getBalance());
    }

    @Override
    public void deposit(int amount) throws AccountIsLockedException, InvalidSessionException, InvalidAmountException {
        ensureSessionActive();
        validateAmount(amount);
        server.deposit(amount);
        System.out.printf("Вы внесли %d рублей. Текущий баланс: %d рублей%n", amount, server.getBalance());
    }

    @Override
    public void withdraw(int amount) throws AccountIsLockedException, InvalidSessionException, InvalidAmountException, InsufficientFundsException {
        ensureSessionActive();
        validateAmount(amount);
        server.withdraw(amount);
        System.out.printf("Вы сняли %d рублей. Текущий баланс: %d рублей%n", amount, server.getBalance());
    }

    private void ensureSessionActive() throws InvalidSessionException {
        if (!sessionActive) {
            throw new InvalidSessionException("Сначала необходимо ввести пин-код.");
        }
    }

    private void validateAmount(int amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Сумма должна быть положительным числом.");
        }
        if (amount % 100 != 0) {
            throw new InvalidAmountException("Сумма должна быть кратна 100.");
        }
    }
}
