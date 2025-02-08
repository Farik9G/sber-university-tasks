package ru.sber.school.reflection.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class CachedInvocationHandler implements InvocationHandler {

    private final Map<Object, Object> resultByArg = new HashMap<>();
    private final Object delegate;

    public CachedInvocationHandler(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            Object key = generateCacheKey(method, args);
            if (!resultByArg.containsKey(key)) {
                System.out.println("Delegation of " + method.getName());
                Object result = invokeMethod(method, args);
                resultByArg.put(key, result);
            }
            return resultByArg.get(key);
        } else {
            return invokeMethod(method, args);
        }
    }

    private Object invokeMethod(Method method, Object[] args) throws Throwable {
        try {
            return method.invoke(delegate, args);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Impossible", e);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    private Object generateCacheKey(Method method, Object[] args) {
        return new AbstractMap.SimpleEntry<>(method, Arrays.hashCode(args));
    }
}
