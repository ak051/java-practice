package com.ace.exercise.executors;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.ace.exercise.processFile.CreateMTDFile;
import com.ace.exercise.processor.DirectoryProcessor;

public class DirectoryScanExecutor {
	
	public static void main(String[] args) {
		final BlockingQueue<File> fileQueue = new ArrayBlockingQueue<File>(10);
	
		ScheduledExecutorService service = Executors
				.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(new DirectoryProcessor(fileQueue), 0, 2, TimeUnit.MINUTES);
		Thread thread = new Thread(new FileQueueConsumer(fileQueue));
		thread.start();
	}
}

class FileQueueConsumer implements Runnable {
	private final BlockingQueue<File> fileQueue;
	
	public FileQueueConsumer(BlockingQueue<File> fileQueue) {
		this.fileQueue = fileQueue;
	}

	@Override
	public void run() {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		while (true) {
			File file = null;
			try {
				file = fileQueue.take();
				System.out.println("Currently Processing director: "
						+ file.getCanonicalPath() + " for MTD");
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
			executor.submit(new CreateMTDFile(file));

		}

	}

}
