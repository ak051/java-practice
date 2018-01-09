package com.ace.training.service.impl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ace.training.dao.StudentSubjectDao;
import com.ace.training.dto.StudentJson;
import com.ace.training.service.StudentJsonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StudentJsonServiceImpl implements StudentJsonService {

	private static Logger LOG = LoggerFactory
			.getLogger(StudentJsonServiceImpl.class);

	@Autowired
	private StudentSubjectDao studentSubjectDao;

	@Autowired
	private ObjectMapper mapper;

	@Override
	public void createStudentJson() {

		ExecutorService executor = Executors.newFixedThreadPool(5);
		List<StudentJson> studentJsonList = studentSubjectDao
				.fetchStudentRecords();

		for (StudentJson studentJson : studentJsonList) {

			try {
				String jsonString = mapper.writeValueAsString(studentJson);
				String fileName = studentJson.getId();
				executor.submit(new GenerateReportServiceImpl(fileName,
						jsonString));
			} catch (JsonProcessingException e) {
				LOG.error("Json Parsing Exception " + e);
			}

		}

		executor.shutdown();

	}

}
