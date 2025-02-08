package ru.sber.school.reflection;

import java.lang.reflect.Field;

public final class ReflectionHelper {

    private ReflectionHelper() {
    }

    public static boolean checkValueNotNull(Object object) throws IllegalAccessException {
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if (field.get(object) == null) {
                return false;
            }
        }
        return true;
    }

    public static void copyObject(Object source, Object target) throws IllegalAccessException {
        if (source.getClass() != target.getClass()) {
            throw new IllegalArgumentException("Объекты разых типов");
        }
        Field[] declaredFields = source.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            Object fieldValue = field.get(source);
            field.set(target, fieldValue);
        }
    }

    public static void validateStringLength(Object object) throws Exception {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType() == String.class) {
                if (field.isAnnotationPresent(ValidLength.class)) {
                    ValidLength annotation = field.getAnnotation(ValidLength.class);
                    Object obj = field.get(object);
                    if (obj == null) {
                        continue;
                    }
                    int max = annotation.max();
                    int min = annotation.min();
                    String str = (String) obj;
                    if (str.length() > max || str.length() < min) {
                        throw new IllegalStateException(field.getName() + " длина данного поля должна быть в диапазоне" + min + "-" + max);
                    }
                }
            }
        }
    }
}
