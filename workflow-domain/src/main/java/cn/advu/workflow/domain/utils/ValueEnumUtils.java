package cn.advu.workflow.domain.utils;


import cn.advu.workflow.domain.enums.IValueEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ValueEnumUtils {

	/**
     * <p>Gets the {@code Map} of enums by name.</p>
     *
     * <p>This method is useful when you need a map of enums by name.</p>
     *
     * @param <E> the type of the enumeration
     * @param enumClass  the class of the enum to query, not null
     * @return the modifiable map of enum names to enums, never null
     */
    public static <E extends Enum<E>> Map<String, E> getEnumMap(final Class<E> enumClass) {
        final Map<String, E> map = new LinkedHashMap<String, E>();
        for (final E e: enumClass.getEnumConstants()) {
            map.put(e.name(), e);
        }
        return map;
    }

    /**
     * <p>Gets the {@code List} of enums.</p>
     *
     * <p>This method is useful when you need a list of enums rather than an array.</p>
     *
     * @param <E> the type of the enumeration
     * @param enumClass  the class of the enum to query, not null
     * @return the modifiable list of enums, never null
     */
    public static <E extends Enum<E>> List<E> getEnumList(final Class<E> enumClass) {
        return new ArrayList<E>(Arrays.asList(enumClass.getEnumConstants()));
    }

    /**
     * <p>Checks if the specified value is a valid enum for the class.</p>
     *
     * <p>This method differs from {@link Enum#valueOf} in that checks if the value is
     * a valid enum without needing to catch the exception.</p>
     *
     * @param <E> the type of the enumeration
     * @param enumClass  the class of the enum to query, not null
     * @param enumValue   the enum value, null returns false
     * @return true if the enum value is valid, otherwise false
     */
    public static <E extends Enum<E>> boolean isValidEnum(final Class<E> enumClass, final String enumValue) {
        if (enumValue == null) {
            return false;
        }
        return getEnum(enumClass,enumValue) != null;
    }

    /**
     * <p>Gets the enum for the class, returning {@code null} if not found.</p>
     *
     * <p>This method differs from {@link Enum#valueOf} in that it does not throw an exception
     * for an invalid enum value.</p>
     *
     * @param <E> the type of the enumeration
     * @param enumClass  the class of the enum to query, not null
     * @param enumValue   the enum value, null returns null
     * @return the enum, null if not found
     */
    public static <E extends Enum<E>> E getEnum(final Class<E> enumClass, final String enumValue) {
        if (enumValue == null) {
            return null;
        }
        for(E e : EnumSet.allOf(enumClass)){
    		IValueEnum valueEnum = (IValueEnum)e;
    		if(valueEnum.getValue().equals(enumValue)){
    			return e;
    		}
    	}
        return null;
    }
}
