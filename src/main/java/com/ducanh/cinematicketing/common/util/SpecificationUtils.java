package com.ducanh.cinematicketing.common.util;

public class SpecificationUtils {
    public static String toPattern(String value)
    {
        String escaped = value.trim().toLowerCase()
                .replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_");
        return "%" + escaped + "%";
    }
}
