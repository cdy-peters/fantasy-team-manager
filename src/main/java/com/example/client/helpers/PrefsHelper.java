package com.example.client.helpers;

import java.util.prefs.Preferences;

/**
 * Helper class for managing user preferences.
 */
public class PrefsHelper {

    private static Preferences prefs = Preferences.userNodeForPackage(PrefsHelper.class);

    /**
     * Default constructor
     */
    private PrefsHelper() {
    }

    /**
     * Set a user preference.
     * 
     * @param name   The name of the preference
     * @param cookie The value of the preference
     */
    public static void setPref(String name, String cookie) {
        prefs.put(name, cookie);
    }

    /**
     * Get a user preference.
     * 
     * @param name The name of the preference
     * @return The value of the preference, or an empty string if the preference
     *         does
     */
    public static String getPref(String name) {
        return prefs.get(name, "");
    }

    /**
     * Remove a user preference.
     * 
     * @param name The name of the preference
     */
    public static void removePref(String name) {
        prefs.remove(name);
    }

}
