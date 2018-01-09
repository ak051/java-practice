package com.ace.training.service.impl;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ace.training.dao.StudentSubjectDao;
import com.ace.training.dto.StudentsDataDTOXml;
import com.ace.training.exceptions.DaoException;
import com.ace.training.service.UploadToDbService;

@Service
public class UploadToDbServiceImpl implements UploadToDbService {
	
	@Autowired
	private StudentSubjectDao studentSubjectDao;

	@Override
	public void parseXml(String fileName) throws JAXBException,DaoException {

			File file = new File(fileName);
			JAXBContext jaxbContext = JAXBContext.newInstance(StudentsDataDTOXml.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StudentsDataDTOXml studentsData = (StudentsDataDTOXml) jaxbUnmarshaller.unmarshal(file);
			
			//Before inserting delete all records
			studentSubjectDao.deleteAllRecords();

			studentSubjectDao.insertToDb(studentsData);
			
			//Calculate Result and Rank
			studentSubjectDao.calculateResult();
	}

}
