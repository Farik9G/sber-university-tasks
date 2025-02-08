package ru.sber.school.reflection;

import java.lang.reflect.Method;

public class BeanUtils {
    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to   Object which properties will be set.
     * @param from Object which properties will be used to get values.
     */
    public static void assign(Object to, Object from) {
        Method[] fromMethods = from.getClass().getMethods();

        for (Method fromMethod : fromMethods) {
            if (isGetter(fromMethod)) {
                String setterName = "set" + fromMethod.getName().substring(3);
                try {
                    Method toMethod = to.getClass().getMethod(setterName, fromMethod.getReturnType());

                    if (toMethod.getParameterTypes()[0].isAssignableFrom(fromMethod.getReturnType())) {
                        Object value = fromMethod.invoke(from);
                        toMethod.invoke(to, value);
                    }
                } catch (NoSuchMethodException e) {
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static boolean isGetter(Method method) {
        return (method.getName().startsWith("get") || method.getName().startsWith("is"))
                && method.getParameterCount() == 0
                && method.getReturnType() != Void.TYPE;
    }

}
