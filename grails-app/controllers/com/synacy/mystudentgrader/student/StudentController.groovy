package com.synacy.mystudentgrader.student

import com.synacy.mystudentgrader.student.com.synacy.mystudentgrader.InvalidRequestException
import com.synacy.mystudentgrader.student.com.synacy.mystudentgrader.ResourceNotFoundException
import org.springframework.http.HttpStatus

class StudentController {

	static responseFormats = ['json']

	StudentService studentService

	def fetchAllStudents() {
		Integer max = params.max ? Integer.parseInt(params.max) : null
		Integer offset = params.max ? Integer.parseInt(params.offset) : null

		List<Student> students = studentService.fetchStudents(max, offset)
		Integer studentCount = studentService.fetchTotalNumberOfStudents()
		Map<String, Object> paginatedStudentDetails = [totalRecords: studentCount, records: students]
		respond(paginatedStudentDetails)
	}

	def fetchStudent(Long studentId) {
		Student student = studentService.fetchById(studentId)
		if (!student) {
			throw new ResourceNotFoundException("Student not found")
		}
		respond(student)
	}

	def createStudent() {
		String genderString = request.JSON.gender ?: null
		Integer age = request.JSON.age ?: null
		String name = request.JSON.name ?: null
		String yearLevel = request.JSON.yearLevel ?: null

		Gender gender = Gender.valueOfGender(genderString)

		if (!name || !gender || !age || !yearLevel) {
			throw new InvalidRequestException("Invalid request.")
		}


		Student student = studentService.createNewStudent(name, age, gender, yearLevel)
		respond(student, [status: HttpStatus.CREATED])
	}

	def updateStudent(Long studentId) {
		String genderString = request.JSON.gender ?: null
		Integer age = request.JSON.age ?: null
		String name = request.JSON.name ?: null
		String yearLevel = request.JSON.yearLevel ?: null

		Gender gender = Gender.valueOfGender(genderString)

		if (!name || !gender || !age || !yearLevel) {
			throw new InvalidRequestException("Invalid request.")
		}

		Student student = studentService.fetchById(studentId)
		if (!student) {
			throw new ResourceNotFoundException("Student not found")
		}
		student = studentService.updateStudent(student, name, age, gender, yearLevel)
		respond(student)
	}

	def removeStudent(Long studentId) {
		Student student = studentService.fetchById(studentId)
		if (!student) {
			throw new ResourceNotFoundException("Student not found")
		}
		studentService.deleteStudent(student)

		render(status: HttpStatus.NO_CONTENT)
	}


	def handleResourceNotFoundException(ResourceNotFoundException e) {
		response.status = HttpStatus.NOT_FOUND.value()
		respond([error: e.getMessage()])
	}

	def handleInvalidRequestException(InvalidRequestException e) {
		response.status = HttpStatus.UNPROCESSABLE_ENTITY.value()
		respond([error: e.getMessage()])
	}

}
