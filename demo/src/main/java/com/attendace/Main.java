package com.attendace;

import com.attendace.view.LoginInterfaceController;
import com.attendace.localisation.Translator;

public class Main {
    public static void main(String[] args) {
        Translator.setLocale("en", "US");
        LoginInterfaceController.launch(LoginInterfaceController.class);
    }
}