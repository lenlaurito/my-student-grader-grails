package com.synacy.mystudentgrader.student.grade

import com.synacy.mystudentgrader.student.Student
import grails.transaction.Transactional

@Transactional
class GradeService {

	public Grade fetchGradeOfStudent(Long gradeId, Student student) {
		return Grade.findByIdAndStudent(gradeId, student)
	}

	public Grade createGrade(String subject, BigDecimal finalGrade, Student student) {
		Grade grade = new Grade()
		grade.subject = subject
		grade.finalGrade = finalGrade
		grade.student = student
		return grade.save()
	}

	public Grade updateGrade(Grade grade, String subject, BigDecimal finalGrade) {
		grade.subject = subject
		grade.finalGrade = finalGrade
		return grade.save()
	}

	public void deleteGrade(Grade grade) {
		grade.delete()
	}
}
