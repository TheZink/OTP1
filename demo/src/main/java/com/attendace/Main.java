package com.attendace;

import com.attendace.view.LoginInterfaceController;
import com.attendace.localisation.Translator;

/**
 * Main entry point for the Attendance application.
 * Sets the default locale and launches the login interface.
 */
public class Main {

    /**
     * The main method that starts the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Translator.setLocale("en", "US");
        LoginInterfaceController.launch(LoginInterfaceController.class);
    }
}