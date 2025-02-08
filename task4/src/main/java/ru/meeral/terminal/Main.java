package ru.meeral.terminal;

import ru.meeral.terminal.impl.TerminalImpl;

public class Main {
    public static void main(String[] args) {
//        Корректный пин-код - 1337
//        Баланс сохраняется в balance.txt

        TerminalServer server = new TerminalServer();
        PinValidator pinValidator = new PinValidator();
        Terminal terminal = new TerminalImpl(server, pinValidator);

        TerminalApp app = new TerminalApp(terminal);
        app.start();
    }
}