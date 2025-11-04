package com.attendace.localisation;

import java.util.Locale;
import java.util.ResourceBundle;

public class Translator {
    static private Locale locale;
    static private ResourceBundle resourceBundle;

    static public void setLocale(String langCode, String countryCode) {
        locale = new Locale(langCode, countryCode);
        resourceBundle = ResourceBundle.getBundle("ResourceBundle", locale);
    }

    static public String getString(String key) {
        return resourceBundle.getString(key);
    }
}
