package com.derrygirls.utils;

public class DerryGirlsUtils {

    private DerryGirlsUtils() {}


    public static String addExceptionMessage(long id, String defaultMessage, String item) {
        return String.format(defaultMessage, item, id);
    }
}
