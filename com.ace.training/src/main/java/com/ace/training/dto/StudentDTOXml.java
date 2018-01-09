package com.ace.training.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "student")
public class StudentDTOXml {

	private String Id;
	private StudentNameDTOXml studentName;
	private String studentClass;
	private List<SubjectDTOXml> subjects;

	@XmlElement(name = "id")
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	@XmlElement(name = "name")
	public StudentNameDTOXml getStudentName() {
		return studentName;
	}

	public void setStudentName(StudentNameDTOXml studentName) {
		this.studentName = studentName;
	}

	@XmlElement(name = "class")
	public String getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}

	@XmlElement(name = "subject")
	public List<SubjectDTOXml> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<SubjectDTOXml> subjects) {
		this.subjects = subjects;
	}

	@Override
	public String toString() {
		return "Student [Id=" + Id + ", studentName=" + studentName
				+ ", studentClass=" + studentClass + ", subjects=" + subjects
				+ "]";
	}

}
