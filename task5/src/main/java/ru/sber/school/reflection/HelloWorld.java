package ru.sber.school.reflection;

public class HelloWorld {
    private final String name;

    public HelloWorld(String name) {
        this.name = name;
    }

    private void sayHello(){
        System.out.println("Hello, "+name);
    }

    public static void main(String[] args) {
        new HelloWorld("World").sayHello();
    }

}
