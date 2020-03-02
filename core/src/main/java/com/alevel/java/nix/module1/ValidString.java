package com.alevel.java.nix.module1;

public class ValidString {
    public static boolean isValidString(String string) {
        for (int i = 0, length = string.length(); i < length; i++) {
            if (string.charAt(i) == '(') {
                if (string.charAt(length - 1 - i) == ')') {
                    continue;
                } else {
                    return false;
                }
            }
            if (string.charAt(i) == '{') {
                if (string.charAt(length - 1 - i) == '}') {
                    continue;
                } else {
                    return false;
                }
            }
            if (string.charAt(i) == '[') {
                if (string.charAt(length - 1 - i) == ']') {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
