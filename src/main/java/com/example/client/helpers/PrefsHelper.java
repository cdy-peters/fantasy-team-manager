package com.example.client.helpers;

import java.util.prefs.Preferences;

public class PrefsHelper {

    private static Preferences prefs = Preferences.userNodeForPackage(PrefsHelper.class);

    public static void setPref(String name, String cookie) {
        prefs.put(name, cookie);
    }

    public static String getPref(String name) {
        return prefs.get(name, "");
    }

    public static void removePref(String name) {
        prefs.remove(name);
    }

}
