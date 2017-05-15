package com.synacy.mystudentgrader.student.grade

import com.synacy.mystudentgrader.student.Student
import com.synacy.mystudentgrader.student.StudentService
import org.springframework.http.HttpStatus

class GradeController {

	static responseFormats = ['json']

	StudentService studentService
	GradeService gradeService

	def fetchGradeOfStudent(Long studentId, Long gradeId) {
		Student student = studentService.fetchById(studentId)
		Grade grade = gradeService.fetchGradeOfStudent(gradeId, student)
		respond(grade)
	}

	def fetchAllGradesOfStudent(Long studentId) {
		Student student = studentService.fetchById(studentId)
		List<Grade> grades = student.grades as List
		respond(grades)
	}

	def createNewGradeForStudent(Long studentId) {
		String subject = request.JSON.subject ?: null
		BigDecimal finalGrade = request.JSON.finalGrade ?: null

		Student student = studentService.fetchById(studentId)
		Grade grade = gradeService.createGrade(subject, finalGrade, student)
		respond(grade, [status: HttpStatus.CREATED])
	}

	def updateGradeOfStudent(Long studentId, Long gradeId) {
		String subject = request.JSON.subject ?: null
		BigDecimal finalGrade = request.JSON.finalGrade ?: null

		Student student = studentService.fetchById(studentId)
		Grade grade = gradeService.fetchGradeOfStudent(gradeId, student)
		grade = gradeService.updateGrade(grade, subject, finalGrade)
		respond(grade)
	}

	def deleteGradeOfStudent(Long studentId, Long gradeId) {
		Student student = studentService.fetchById(studentId)
		Grade grade = gradeService.fetchGradeOfStudent(gradeId, student)

		gradeService.deleteGrade(grade)
		render([status: HttpStatus.NO_CONTENT])
	}
}
