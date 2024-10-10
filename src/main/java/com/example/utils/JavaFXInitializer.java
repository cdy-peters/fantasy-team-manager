package com.example.utils;

import javafx.application.Platform;

/**
 * Helper class for initializing JavaFX.
 */
public class JavaFXInitializer {
	private static boolean initialized = false;

	/**
	 * Default constructor
	 */
	private JavaFXInitializer() {
	}

	/**
	 * Initialize JavaFX.
	 */
	public static void initialize() {
		if (!initialized) {
			Platform.startup(() -> {
			});
			initialized = true;
		}
	}
}
