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

public class LangUtils {

    Handler handler = new DefaultHandler();
    Locale locale;

    public List<String> language(Map<String, Object> object, String type) {
        Request request = null;
        if (type.equals("User")) {
            request = new Request(RequestDao.USERS, RequestType.LANGUAGE, object);
        } else {
            request = new Request(RequestDao.STAFF, RequestType.LANGUAGE, object);
        }
        return (ArrayList<String>) handler.handle(request);
    }

    public String setLanguage(String language){
        if (language.contains("en")){Translator.setLocale("en", "US"); return "en";}
        else if (language.contains("fa")) {Translator.setLocale("fa", "IR"); return "fa";}
        else if (language.contains("fi")) {Translator.setLocale("fi", "FI"); return "fi";}
        else if (language.contains("ja")) {Translator.setLocale("ja", "JP"); return "ja";}
        return null;
    }

    public String getLanguage(){
        return locale.toLanguageTag();
    }
}
