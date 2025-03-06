package cacheProxy;

import database.DataBaseHandler;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class CacheProxyHandler implements InvocationHandler {
    private final Object target;
    private final DataBaseHandler databaseHandler;

    public CacheProxyHandler(Object target, DataBaseHandler databaseHandler) {
        this.target = target;
        this.databaseHandler = databaseHandler;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cacheable.class)) {
            return executeWithCache(method, args);
        }
        return method.invoke(target, args);
    }

    private Object executeWithCache(Method method, Object[] args) {
        Integer number = (Integer) args[0];
        List<Integer> cachedResult = databaseHandler.loadData(number);

        if (cachedResult != null && !cachedResult.isEmpty()) {
            System.out.printf("Для числа %d найден результат в кэше:\n", number);
            return cachedResult;
        }

        try {
            System.out.printf("Сохраняем результат для числа %d\n", number);
            List<Integer> result = (List<Integer>) method.invoke(target, args);
            databaseHandler.saveData(number, result);
            return result;
        } catch (Exception exception) {
            System.err.println("Ошибка при вызове метода: " + exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }
}
