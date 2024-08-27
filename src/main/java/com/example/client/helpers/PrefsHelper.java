package com.example.client.helpers;

import java.util.prefs.Preferences;

public class PrefsHelper {

    public static void setPref(String name, String cookie) {
        Preferences prefs = Preferences.userNodeForPackage(PrefsHelper.class);
        prefs.put(name, cookie);
    }

    public static String getPref(String name) {
        Preferences prefs = Preferences.userNodeForPackage(PrefsHelper.class);
        return prefs.get(name, "");
    }

    public static void removePref(String name) {
        Preferences prefs = Preferences.userNodeForPackage(PrefsHelper.class);
        prefs.remove(name);
    }

}
