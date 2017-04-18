package com.github.angads25.textanalyzer;

import java.util.HashMap;

public class LogSingleton {
	private static HashMap<String, IterableFile> instance = new HashMap<>();

	private LogSingleton() {
	}
	
	public static HashMap<String, IterableFile> getInstance() {
		return instance;
	}
	
	public static synchronized void updateStatus(IterableFile file) {
		instance.put(file.getFile().getName()+"|"+file.getLength(), file);
	}
}
