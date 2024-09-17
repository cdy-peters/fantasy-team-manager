package com.example.utils;

import javafx.application.Platform;

public class JavaFXInitializer {
	private static boolean initialized = false;

	public static void initialize() {
		if (!initialized) {
			Platform.startup(() -> {
			});
			initialized = true;
		}
	}
}
