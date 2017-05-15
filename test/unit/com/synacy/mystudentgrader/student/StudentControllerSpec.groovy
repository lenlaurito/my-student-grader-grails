package com.synacy.mystudentgrader.student

import grails.test.mixin.TestFor
import org.springframework.http.HttpStatus
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(StudentController)
class StudentControllerSpec extends Specification {

	StudentService studentService = Mock()

	def setup() {
		controller.studentService = studentService
	}

	def cleanup() {
	}

	void "fetchAllStudents should respond with the correct student"() {
		given:
		Student student1 = new Student(name: "Jane", age: 20, yearLevel: "FIRST_YEAR", gender: Gender.FEMALE)
		Student student2 = new Student(name: "John", age: 22, yearLevel: "SECOND_YEAR", gender: Gender.MALE)
		studentService.fetchStudents(null, null) >> [student1, student2]

		when:
		controller.fetchAllStudents()

		then:
		response.status == HttpStatus.OK.value()
		response.json.size() == 2
		response.json.find {
			it.name == "Jane" && it.age == 20 && it.yearLevel == "FIRST_YEAR" && it.gender.name == Gender.FEMALE.name()
		} != null
		response.json.find {
			it.name == "John" && it.age == 22 && it.yearLevel == "SECOND_YEAR" && it.gender.name == Gender.MALE.name()
		} != null
	}

	void "fetchStudent should respond with the student which has the specified id"() {
		given:
		Long studentId = 2L
		Student student = new Student(name: "Jane", age: 20, yearLevel: "FIRST_YEAR", gender: Gender.FEMALE)
		studentService.fetchById(studentId) >> student

		when:
		controller.fetchStudent(studentId)

		then:
		response.status == HttpStatus.OK.value()
		response.json.name == "Jane"
		response.json.age == 20
		response.json.yearLevel == "FIRST_YEAR"
		response.json.gender.name == Gender.FEMALE.name()
	}

	void "createStudent should respond with the newly created student with the details specified"() {
		given:
		String name = "John"
		int age = 19
		String yearLevel = "FIRST_YEAR"
		Gender gender = Gender.MALE

		request.json = [name: name, age: age, yearLevel: yearLevel, gender: gender.name()]

		Student student = new Student(name: name, age: age, yearLevel: yearLevel, gender: gender)

		when:
		controller.createStudent()

		then:
		1 * studentService.createNewStudent(name, age, gender, yearLevel) >> student

		then:
		response.status == HttpStatus.CREATED.value()
		response.json.name == name
		response.json.age == age
		response.json.yearLevel == yearLevel
		response.json.gender.name == gender.name()
	}
}
