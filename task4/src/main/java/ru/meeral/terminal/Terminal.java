package terminal;

import terminal.exceptions.AccountIsLockedException;
import terminal.exceptions.InsufficientFundsException;
import terminal.exceptions.InvalidAmountException;
import terminal.exceptions.InvalidSessionException;

public interface Terminal {
    void enterPin(char digit) throws AccountIsLockedException;

    void checkBalance() throws AccountIsLockedException, InvalidSessionException;

    void withdraw(int amount) throws AccountIsLockedException, InvalidSessionException, InvalidAmountException, InsufficientFundsException;

    void deposit(int amount) throws AccountIsLockedException, InvalidSessionException, InvalidAmountException;
}
