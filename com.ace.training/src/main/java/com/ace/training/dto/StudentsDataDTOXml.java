package com.ace.training.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "students")
public class StudentsDataDTOXml {

	private List<StudentDTOXml> students;

	@XmlElement(name = "student")
	public List<StudentDTOXml> getStudents() {
		return students;
	}

	public void setStudents(List<StudentDTOXml> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "StudentsData [students=" + students + "]";
	}
	
	

}
