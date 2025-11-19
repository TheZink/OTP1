package com.attendace.localisation;

import java.util.Locale;
import java.util.ResourceBundle;

public class Translator {
    private Translator() {
        // Private constructor to prevent instantiation
    }

    private static ResourceBundle resourceBundle;

    public static void setLocale(String langCode, String countryCode) {
        Locale locale = new Locale(langCode, countryCode);
        resourceBundle = ResourceBundle.getBundle("ResourceBundle", locale);
    }

    public static String getString(String key) {
        return resourceBundle.getString(key);
    }
}
