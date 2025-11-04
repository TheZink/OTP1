package com.attendace.localisation;

import java.util.Locale;
import java.util.ResourceBundle;

public class translator {
    static private Locale locale;
    static private ResourceBundle resourceBundle;

    static public void setLocale(String langCode, String countryCode) {
        locale = new Locale(langCode, countryCode);
        resourceBundle = ResourceBundle.getBundle("i18n/messages", locale);
    }
}
