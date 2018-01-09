package com.ace.exercise.processFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;

public class CreateMTDFile implements Runnable {

	//private CountDownLatch countDownLatch;
	private File file;

	public CreateMTDFile(File file) {
		//this.countDownLatch = countDownLatch;
		this.file = file;
	}

	@Override
	public void run() {

		FileInputStream fileStream = null;
		InputStreamReader input = null;
		BufferedReader reader = null;
		try {
			fileStream = new FileInputStream(file);
			input = new InputStreamReader(fileStream);
			reader = new BufferedReader(input);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		String line;

		// Initializing counters
		int noOfWords = 0;
		int noOfletters = 0;
		int noOfvowels = 0;
		int noOfSpecialCharacters = 0;

		Set<Character> vowelSet = new HashSet<>(Arrays.asList('a', 'e', 'i',
				'o', 'u'));
		Set<Character> specialCharacterSet = new HashSet<>(Arrays.asList('@',
				'#', '$', '*'));

		try {
			while ((line = reader.readLine()) != null) {
				if (!(line.equals(""))) {
					noOfletters += line.length();
					// \\s+ is the space delimiter in java
					String[] wordList = line.split("\\s+");
					noOfWords += wordList.length;

					for (char c : line.toCharArray()) {
						if (vowelSet.contains(Character.toLowerCase(c))) {
							noOfvowels++;
						} else if (specialCharacterSet.contains(Character
								.toLowerCase(c))) {
							noOfSpecialCharacters++;
						}
					}

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		String fileName = null;
		String fileDirectoryName = null;
		String fileBaseName = null;
		String newFileName = null;
		try {
			fileName = file.getCanonicalPath();
			fileDirectoryName = FilenameUtils.getFullPath(fileName);
			fileBaseName = FilenameUtils.getBaseName(fileName);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		newFileName = fileDirectoryName + fileBaseName + ".MTD";

		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
				newFileName))) {

			bufferedWriter.write("No. of Words: " + noOfWords);
			bufferedWriter.newLine();
			bufferedWriter.write("No. of Letters: " + noOfletters);
			bufferedWriter.newLine();
			bufferedWriter.write("No. of Vowels: " + noOfvowels);
			bufferedWriter.newLine();
			bufferedWriter.write("No. of Special Chararacters: "
					+ noOfSpecialCharacters);

		} catch (IOException e) {
			e.printStackTrace();
		}
		//this.countDownLatch.countDown();

	}

}
