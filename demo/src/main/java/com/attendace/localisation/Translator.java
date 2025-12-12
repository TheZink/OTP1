package com.attendace.localisation;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Utility class for handling localization and translation using resource bundles.
 * Provides static methods to set the locale, retrieve the current locale,
 * and fetch localized strings by key.
 */
public class Translator {

    /** The resource bundle used for localization. */
    private static ResourceBundle resourceBundle;

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private Translator() {
        // Private constructor to prevent instantiation
    }

    /**
     * Sets the locale for the application and loads the corresponding resource bundle.
     *
     * @param langCode    the language code (e.g., "en" for English)
     * @param countryCode the country code (e.g., "US" for United States)
     */
    public static void setLocale(String langCode, String countryCode) {
        Locale locale = new Locale(langCode, countryCode);
        resourceBundle = ResourceBundle.getBundle("ResourceBundle", locale);
    }

    /**
     * Gets the current locale used by the resource bundle.
     *
     * @return the current {@link Locale}
     */
    public static Locale getLocale() {
        return resourceBundle.getLocale();
    }

    /**
     * Retrieves the localized string for the specified key from the resource bundle.
     *
     * @param key the key for the desired string
     * @return the localized string corresponding to the key
     */
    public static String getString(String key) {
        return resourceBundle.getString(key);
    }
}