package ru.meeral.terminal;

import ru.meeral.terminal.exceptions.InsufficientFundsException;
import ru.meeral.terminal.exceptions.AccountIsLockedException;
import ru.meeral.terminal.exceptions.InvalidAmountException;
import ru.meeral.terminal.exceptions.InvalidSessionException;

public interface Terminal {
    void enterPin(char digit) throws AccountIsLockedException;

    void checkBalance() throws AccountIsLockedException, InvalidSessionException;

    void withdraw(int amount) throws AccountIsLockedException, InvalidSessionException, InvalidAmountException, InsufficientFundsException;

    void deposit(int amount) throws AccountIsLockedException, InvalidSessionException, InvalidAmountException;
}
