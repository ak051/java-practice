package com.ace.training.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;

import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ace.training.controller.MainController;
import com.ace.training.dao.StudentSubjectDao;
import com.ace.training.dto.StudentDTOXml;
import com.ace.training.dto.StudentJson;
import com.ace.training.dto.StudentsDataDTOXml;
import com.ace.training.dto.SubjectDTOXml;
import com.ace.training.entity.Student;
import com.ace.training.entity.Subject;
import com.ace.training.exceptions.DaoException;

@Component
@Transactional
public class StudentSubjectDaoImpl implements StudentSubjectDao {
	
	private static Logger LOG = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public void insertToDb (StudentsDataDTOXml studentsData) throws DaoException {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		List<StudentDTOXml> studentsDTO = studentsData.getStudents();

		try {
			for (StudentDTOXml studentDTOXml : studentsDTO) {

				Student student = new Student();
				student.setId(studentDTOXml.getId());
				student.setFirstName(studentDTOXml.getStudentName()
						.getFirstName());
				student.setLastName(studentDTOXml.getStudentName()
						.getLastName());
				student.setClassName(studentDTOXml.getStudentClass());

				Double totalMarks = new Double(0);
				String result = "PASS";
				List<SubjectDTOXml> subjectDTO = studentDTOXml.getSubjects();
				List<Subject> subjects = new ArrayList<>();
				for (SubjectDTOXml subjectDTOXml : subjectDTO) {
					Subject subject = new Subject();
					subject.setId(studentDTOXml.getId());
					subject.setName(subjectDTOXml.getName());
					Double marks = new Double(subjectDTOXml.getMarks());
					if (result.equals("PASS")) {
						if (marks.doubleValue() < 35.0) {
							result = "FAIL";

						}
					}
					subject.setMarks(new Double(subjectDTOXml.getMarks()));
					subjects.add(subject);
					totalMarks = totalMarks + marks;

				}
				student.setSubjects(subjects);
				student.setTotalMarks(totalMarks);
				student.setResult(result);
				session.save(student);
			}

			session.getTransaction().commit();
		} catch (NonUniqueObjectException e) {
			LOG.error("Data Access Exception " + e);
			throw new DaoException("Duplicate records in the Xml. Please upload the correct Xml File.");
			
		}

	}

	@Override
	@SuppressWarnings("unchecked")
	public void calculateResult() {
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		List<Student> studentList = session.createCriteria(Student.class)
				.list();

		Set<Double> totalMarksSet = new TreeSet<>();
		Map<Double, Integer> rankMap = new HashMap<>();

		for (Student student : studentList) {
			if (student.getResult().equals("PASS")) {
				totalMarksSet.add(student.getTotalMarks());
			}
		}

		int rank = totalMarksSet.size();
		for (Double totalMarks : totalMarksSet) {
			rankMap.put(totalMarks, rank);
			rank--;
		}

		for (Student student : studentList) {
			if (student.getResult().equals("PASS")) {
				student.setRank(rankMap.get(student.getTotalMarks()));
				session.save(student);
			}
		}

		session.getTransaction().commit();
	}

	@Override
	public List<StudentJson> fetchStudentRecords() {

		List<StudentJson> studentJsonList = new ArrayList<>();
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		@SuppressWarnings("unchecked")
		List<Student> studentList = session.createCriteria(Student.class)
				.list();

		for (Student student : studentList) {
			StudentJson studentJson = new StudentJson();
			studentJson.setId(student.getId());
			studentJson.setFirstName(student.getFirstName());
			studentJson.setLastName(student.getLastName());
			studentJson.setClassName(student.getClassName());
			studentJson.setRank(student.getRank() == null ? "NA" : student
					.getRank().toString());
			studentJson.setResult(student.getResult().toString());
			studentJson.setTotal(student.getTotalMarks().toString());
			List<SubjectDTOXml> subjects = new ArrayList<>();
			List<Subject> subjectsList = student.getSubjects();
			for (Subject subject : subjectsList) {
				SubjectDTOXml subjectDTOXml = new SubjectDTOXml();
				subjectDTOXml.setMarks(subject.getMarks().toString());
				subjectDTOXml.setName(subject.getName());
				subjects.add(subjectDTOXml);

			}
			studentJson.setSubjects(subjects);
			studentJsonList.add(studentJson);

		}
		session.getTransaction().commit();
		return studentJsonList;

	}

	@Override
	public void deleteAllRecords() {
		deleteSubject();
		deleteStudent();

	}

	private void deleteSubject() {
		EntityManager em = entityManagerFactory.createEntityManager();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaDelete<Subject> querySubject = builder
				.createCriteriaDelete(Subject.class);
		querySubject.from(Subject.class);
		em.getTransaction().begin();
		em.createQuery(querySubject).executeUpdate();
		em.getTransaction().commit();

	}

	private void deleteStudent() {
		EntityManager em = entityManagerFactory.createEntityManager();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaDelete<Student> queryStudent = builder
				.createCriteriaDelete(Student.class);
		queryStudent.from(Student.class);
		em.getTransaction().begin();
		em.createQuery(queryStudent).executeUpdate();
		em.getTransaction().commit();

	}

}
