package com.ace.exercise.metadata;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FileMetadata {

	private static final Map<String, List<File>> dmtdMap = new ConcurrentHashMap<>();
	private static final Map<String, List<File>> smtdMap = new ConcurrentHashMap<>();

}
