package ru.meeral.terminal.exceptions;

public class AccountIsLockedException extends Exception{
    public AccountIsLockedException(String message) {
        super(message);
    }
}
