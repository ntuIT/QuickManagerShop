package com.qman.web.orm;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author DEV-LongDT
 */
public final class PersistentEnums {

    /**
     * 
     */
    public static <T extends PersistentEnum<?>> Type getTypeArgument(Class<T> clazz) {
        final Type interfaces[] = clazz.getGenericInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            if (interfaces[i] instanceof ParameterizedType) {
                final ParameterizedType pt = (ParameterizedType) interfaces[i];
                if (pt.getRawType() == PersistentEnum.class) return pt.getActualTypeArguments()[0];
            }
        }
        return null;
    }

    /**
     * clazz.getEnumConstants() does the same job, but it's slow
     */
    public static <V, T extends Enum<T> & PersistentEnum<V>> Map<V, T> index(Class<T> clazz) {
        final T[] constants = sun.misc.SharedSecrets.getJavaLangAccess().getEnumConstantsShared(clazz);
        final Map<V, T> r = new HashMap<V, T>();
        for (T t : constants) r.put(t.getValue(), t);
        return r;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Enum<T> & PersistentEnum<?>> T parse(Class<T> clazz, Object value) {
        if (value == null) return null;
        final T[] constants = sun.misc.SharedSecrets.getJavaLangAccess().getEnumConstantsShared(clazz);
        return (constants == null || constants.length == 0) ? null : (T) ((PersistentEnum<?>) constants[0]).getAll().get(value);
    }
}
