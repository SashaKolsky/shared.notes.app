package com.dictality.utils;

public class CommonUtils {

    public static Integer tryParseInt(String text, int defaultValue) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
