package com.wongnai.interview;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Utils {
    public static Map<String, Set<Long>> map = new HashMap<>();

    public static boolean isStringNotEmpty(String str) {
        return (str != null && !str.isEmpty());
    }

    public static String getStringEmpty(String str) {
        String strReturn = "";
        if (str != null && !str.isEmpty()) {
            strReturn = str;
        }

        return strReturn;
    }
}
