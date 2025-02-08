package ru.meeral.terminal;

import ru.meeral.terminal.exceptions.AccountIsLockedException;

public class PinValidator {
    private static final String CORRECT_PIN = "1337";
    private static final int MAX_ATTEMPTS = 3;
    private static final int LOCK_DURATION_MS = 10000;

    private StringBuilder currentInput = new StringBuilder();
    private int failedAttempts = 0;
    private long lockTime = 0;

    public boolean validate(char digit) throws AccountIsLockedException {
        if (System.currentTimeMillis() < lockTime) {
            long remainingTime = (lockTime - System.currentTimeMillis()) / 1000;
            throw new AccountIsLockedException(
                    String.format("Аккаунт заблокирован. Осталось %d секунд.", remainingTime)
            );
        }

        if (!Character.isDigit(digit)) {
            System.out.println("Ошибка: пин-код должен состоять из цифр.");
            return false;
        }

        currentInput.append(digit);

        if (currentInput.length() == 4) {
            if (currentInput.toString().equals(CORRECT_PIN)) {
                resetState();
                return true;
            } else {
                handleFailedAttempt();
                return false;
            }
        }

        return false;
    }

    private void handleFailedAttempt() {
        failedAttempts++;
        currentInput.setLength(0);

        if (failedAttempts >= MAX_ATTEMPTS) {
            lockTime = System.currentTimeMillis() + LOCK_DURATION_MS;
            System.out.println("Аккаунт заблокирован на 10 секунд.");
        } else {
            System.out.printf("Неправильный пин-код. Осталось %d попыток.%n", MAX_ATTEMPTS - failedAttempts);
            System.out.println("Попробуйте снова.");
        }
    }


    private void resetState() {
        failedAttempts = 0;
        currentInput.setLength(0);
    }
}
