package com.akash.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.akash.SpringBootRest.SpringBootRestApplication;
import com.akash.beans.StudentRegistration;
import com.akash.service.StudentRegistrationService;

@Controller
@RequestMapping(value = SpringBootRestApplication.API_V1, produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentRegisterController {

	@Autowired
	private StudentRegistrationService service;

	@RequestMapping(value = "/register/student", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<StudentRegistration> registerStudent(
			final @Validated @RequestBody StudentRegistration studentregd) {
		return new ResponseEntity<>(service.registerStudent(studentregd), HttpStatus.OK);
	}

	@RequestMapping(value = "/find/student" + "/{name}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<StudentRegistration> getPlayerDetails(@PathVariable String name) {
		StudentRegistration studentRegd = service.findStudentByName(name);
		return new ResponseEntity<>(studentRegd, studentRegd != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/delete/student" + "/{name}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Void> deleteCompany(@PathVariable String name) {
		return new ResponseEntity<>(service.deleteStudent(name) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
}
