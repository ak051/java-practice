package com.ace.training.service.impl;

import java.io.File;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenerateReportServiceImpl implements Runnable {
	
	private static Logger LOG = LoggerFactory.getLogger(GenerateReportServiceImpl.class);

	private static final String UPLOADED_FOLDER = System
			.getProperty("user.dir") + File.separator + "tmp" + File.separator;

	private static final String extention = ".JSON";

	private final String fileName;
	private final String jsonString;

	public GenerateReportServiceImpl(String fileName, String jsonString) {

		this.fileName = fileName;
		this.jsonString = jsonString;
	}

	@Override
	public void run() {
		try (FileOutputStream fileOutputStream = new FileOutputStream(
				UPLOADED_FOLDER + fileName + extention)) {
			byte byteArray[] = jsonString.getBytes(); // converting string into byte array
			fileOutputStream.write(byteArray);
		} catch (Exception e) {
			LOG.error("Exception occurred in generating report " + e);
		}

	}
}
