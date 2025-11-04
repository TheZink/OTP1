package com.attendace;

import com.attendace.View.LoginInterfaceController;
import com.attendace.dao.handlers.DefaultHandler;
import com.attendace.datasource.DbConnection;
import com.attendace.localisation.Translator;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Translator.setLocale("en", "US");
        LoginInterfaceController.launch(LoginInterfaceController.class);
    }
}