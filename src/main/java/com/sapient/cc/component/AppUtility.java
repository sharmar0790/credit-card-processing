package com.sapient.cc.component;

public final class AppUtility {

    private AppUtility() {
    }

    public static boolean stringContainsDigits(String str) {
        boolean result = false;
        for (int i = 0; i < str.length(); i++) {

            result = Character.isDigit(str.charAt(i));
        }
        return result;
    }
}
