package com.ace.exercise.processor;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import com.ace.exercise.cache.FileCache;

public class DirectoryProcessor implements Runnable {
	private final BlockingQueue<File> fileQueue;
	
	public DirectoryProcessor(BlockingQueue<File> fileQueue) {
		this.fileQueue = fileQueue;
	}

	public void run() {
		File currentDir = new File("G:\\temp");
		Collection<File> files = FileUtils.listFiles(currentDir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		//File[] files = currentDir.listFiles();
		System.out.println("Files in the directory: ");
		for(File file: files){
			System.out.println(file.getAbsolutePath());
			
		}
		long timestamp = System.currentTimeMillis();
		for (File file : files) {
			// check if the file is already cached or not			

			if (file.isFile()) {
				
				System.out.println("File: " + file.getAbsolutePath());
				try {
					if ((FilenameUtils.getExtension(file.getCanonicalPath())
							.equalsIgnoreCase("TXT") || FilenameUtils
							.getExtension(file.getCanonicalPath())
							.equalsIgnoreCase("CSV"))) {
						
						System.out.println("File CSV or TXT: " + file.getAbsolutePath());
						if (!FileCache.isCached(file)) {
							FileCache.updateCache(file, timestamp);
							try {
								System.out.println("File inserting into a queue: " + file.getAbsolutePath());
								fileQueue.put(file);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
