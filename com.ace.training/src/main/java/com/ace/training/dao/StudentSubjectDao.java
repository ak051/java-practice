package com.ace.training.dao;

import java.util.List;

import com.ace.training.dto.StudentJson;
import com.ace.training.dto.StudentsDataDTOXml;
import com.ace.training.exceptions.DaoException;

public interface StudentSubjectDao {

	public abstract void insertToDb(StudentsDataDTOXml studentsData) throws DaoException ;

	public abstract void calculateResult();

	public abstract List<StudentJson> fetchStudentRecords();

	public abstract void deleteAllRecords();

}