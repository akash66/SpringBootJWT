package com.akash.service;

import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.akash.beans.StudentRegistration;
import com.akash.database.MongoRepository;

import static com.akash.beans.StudentRegistration.STUDENT_NAME;
import static com.mongodb.client.model.Filters.eq;
import static com.akash.database.MongoUtils.filterToString;

@Component
public class StudentRegistrationService {

	private static final String COLLECTION = "students";

	@Autowired
	private MongoRepository repository;
	private MongoCollection students;

	public void init() {
		students = repository.getCollection(COLLECTION);
	}

	public StudentRegistration registerStudent(StudentRegistration studentregd) {
		studentregd.setId(null);
		StudentRegistration dbStudentregd = findStudentByName(studentregd.getName());

		if (dbStudentregd == null) {
			students.save(studentregd);
		} else {
			students.update(new ObjectId(dbStudentregd.getId())).with(studentregd);
			studentregd.setId(dbStudentregd.getId());
		}
		return studentregd;
	}

	public StudentRegistration findStudentById(String id) {
		return students.findOne(new ObjectId(id)).as(StudentRegistration.class);
	}

	public StudentRegistration findStudentByName(String name) {
		return students.findOne(filterToString(eq(STUDENT_NAME, name))).as(StudentRegistration.class);
	}

	public boolean deleteStudent(String name) {
		StudentRegistration studentregd = findStudentByName(name);
		if (studentregd != null) {
			students.remove(new ObjectId(studentregd.getId()));
			return true;
		}
		return false;
	}
}
