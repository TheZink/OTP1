package com.attendace.utils;

import java.util.ArrayList;
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

    public List<String> language(Map<String, Object> object, String type) {
        Request request = null;
        if (type.equals("User")) {
            request = new Request(RequestDao.USERS, RequestType.LANGUAGE, object);
        } else {
            request = new Request(RequestDao.STAFF, RequestType.LANGUAGE, object);
        }
        return (ArrayList<String>) handler.handle(request);
    }

    public void setLanguage(String language){
        if (language.equals("en")){Translator.setLocale("en", "US");}
        else if (language.equals("fa")) {Translator.setLocale("fa", "IR");}
        else if (language.equals("fi")) {Translator.setLocale("fi", "FI");}
        else if (language.equals("ja")) {Translator.setLocale("ja", "JP");}
    }
}
