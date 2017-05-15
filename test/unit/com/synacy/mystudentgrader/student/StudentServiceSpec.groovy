package com.synacy.mystudentgrader.student

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(StudentService)
@Mock([Student])
class StudentServiceSpec extends Specification {

	def setup() {
	}

	def cleanup() {
	}

	void "fetchById should return the student with the given id"() {
		given:
		Student student = new Student(name: "John", age: 22, yearLevel: "FIRST_YEAR", gender: "MALE")
		student.save()

		when:
		Student fetchedStudent = service.fetchById(student.id)

		then:
		fetchedStudent.id == student.id
		fetchedStudent.name == student.name
		fetchedStudent.yearLevel == student.yearLevel
		fetchedStudent.gender == student.gender
	}

	void "createNewStudent should create and return the new student with the correct details"() {
		given:
		String name = "Jane"
		int age = 20
		String yearLevel = "THIRD_YEAR"
		String gender = "FEMALE"

		when:
		Student createdStudent = service.createNewStudent(name, age, gender, yearLevel)
		println createdStudent.id

		then:
		createdStudent.name == name
		createdStudent.age == age
		createdStudent.yearLevel == yearLevel
		createdStudent.gender == gender
		Student.exists(createdStudent.id)
	}
}
