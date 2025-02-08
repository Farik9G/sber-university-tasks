package ru.meeral.urlReader;

import java.io.*;
import java.net.*;

public class URLReader {

    public static void readContent(String url) {
        try {
            URL website = new URL(url);
            BufferedReader in = new BufferedReader(new InputStreamReader(website.openStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            System.out.println("Неправильный формат URL.");
        } catch (IOException e) {
            System.out.println("Ошибка при доступе к ресурсу. Пожалуйста, проверьте URL.");
        }
    }

//    Можно потраить работу на https://stackoverflow.com/ и http://www.oracle.com/ или https://steamcommunity.com/.
//    На http://steamcommunity.com/ или
//    https://www.faceit.com/ru/client не работает, не знаю почему.

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String url;

        while (true) {
            System.out.print("Введите URL ресурса: ");
            try {
                url = reader.readLine();
                readContent(url);
            } catch (IOException e) {
                System.out.println("Ошибка ввода. Попробуйте снова.");
            }
        }
    }
}
