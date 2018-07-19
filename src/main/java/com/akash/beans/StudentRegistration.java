package com.akash.beans;

import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

import lombok.Data;

@Data
public class StudentRegistration {
	
	public static final String STUDENT_NAME = "name";

	@MongoId
	@MongoObjectId
	private String id;
	private String name;
	private int age;
}
