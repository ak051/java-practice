package com.ace.training.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(SubjectPK.class)
public class Subject {
	@Id
	@Column
	private String id;
	@Id
	@Column
	private String name;
	@Column
	private Double marks;
	@ManyToOne
	@JoinColumn(name="id",insertable=false, updatable=false)
	private Student student;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getMarks() {
		return marks;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public void setMarks(Double marks) {
		this.marks = marks;
	}
	

}
