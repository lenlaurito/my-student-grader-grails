package com.synacy.mystudentgrader.student

import org.springframework.http.HttpStatus

class StudentController {

	static responseFormats = ['json']

	StudentService studentService

	def fetchAllStudents() {
		List<Student> students = studentService.fetchAllStudents()
		respond(students)
	}

	def fetchStudent(Long studentId) {
		Student student = studentService.fetchById(studentId)
		respond(student)
	}

	def createStudent() {
		String gender = request.JSON.gender ?: null
		Integer age = request.JSON.age ?: null
		String name = request.JSON.name ?: null
		String yearLevel = request.JSON.yearLevel ?: null

		Student student = studentService.createNewStudent(name, age, gender, yearLevel)
		respond(student, [status: HttpStatus.CREATED])
	}

	def updateStudent(Long studentId) {
		String gender = request.JSON.gender ?: null
		Integer age = request.JSON.age ?: null
		String name = request.JSON.name ?: null
		String yearLevel = request.JSON.yearLevel ?: null

		Student student = studentService.fetchById(studentId)
		student = studentService.updateStudent(student, name, age, gender, yearLevel)
		respond(student)
	}

	def removeStudent(Long studentId) {
		Student student = studentService.fetchById(studentId)
		studentService.deleteStudent(student)

		render(status: HttpStatus.NO_CONTENT)
	}

}
