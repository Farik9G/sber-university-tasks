package cacheProxy;

import calculator.Calculator;
import database.DataBaseHandler;

import java.lang.reflect.Proxy;

public class CacheProxy {
    public static Calculator cached(Calculator calculator, DataBaseHandler databaseHandler) {
        return (Calculator) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[] {Calculator.class}, new CacheProxyHandler(calculator, databaseHandler)
        );
    }

}
