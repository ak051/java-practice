package com.ace.exercise.cache;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FileCache {
	private static final Map<File, Long> cacheMap = new HashMap<>();

	public static void clearCache() {
		cacheMap.clear();

	}

	public static void updateCache(File file, Long timestamp) {
		cacheMap.put(file, timestamp);

	}

	public static boolean isCached(File key) {
		return cacheMap.containsKey(key);

	}

	public static Map<File, Long> getCachemap() {
		return cacheMap;
	}
	
	

}
