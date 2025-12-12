package com.attendace.utils;

import java.util.ArrayList;
import java.util.Locale;
import java.util.List;
import java.util.Map;

import com.attendace.dao.Handler;
import com.attendace.dao.Request;
import com.attendace.dao.handlers.DefaultHandler;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;
import com.attendace.localisation.Translator;

/**
 * Utility class for handling language and locale operations for users and staff.
 * Provides methods to retrieve and set language preferences.
 */
public class LangUtils {

    /** Handler for processing DAO requests. */
    Handler handler = new DefaultHandler();

    /** The current locale. */
    Locale locale;

    /**
     * Retrieves the language preferences for a user or staff member.
     *
     * @param object a map containing user or staff identification data
     * @param type   the type of account ("User" or "Staff")
     * @return a list of language codes associated with the user or staff
     */
    public List<String> language(Map<String, Object> object, String type) {
        Request request = null;
        if (type.equals("User")) {
            request = new Request(RequestDao.USERS, RequestType.LANGUAGE, object);
        } else {
            request = new Request(RequestDao.STAFF, RequestType.LANGUAGE, object);
        }
        return (ArrayList<String>) handler.handle(request);
    }

    /**
     * Sets the application's language and locale based on the provided language code.
     *
     * @param language the language code (e.g., "en", "fa", "fi", "ja")
     * @return the language code that was set, or null if not recognized
     */
    public String setLanguage(String language){
        if (language.contains("en")){
            Translator.setLocale("en", "US");
            return "en";
        }
        else if (language.contains("fa")) {
            Translator.setLocale("fa", "IR");
            return "fa";
        }
        else if (language.contains("fi")) {
            Translator.setLocale("fi", "FI");
            return "fi";
        }
        else if (language.contains("ja")) {
            Translator.setLocale("ja", "JP");
            return "ja";
        }
        return null;
    }

    /**
     * Gets the current locale as a language tag.
     *
     * @return the current locale in IETF BCP 47 language tag format
     */
    public String getLanguage(){
        return locale.toLanguageTag();
    }
}
