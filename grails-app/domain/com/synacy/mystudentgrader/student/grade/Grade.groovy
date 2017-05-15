package com.synacy.mystudentgrader.student.grade

import com.synacy.mystudentgrader.student.Student

class Grade {

	BigDecimal finalGrade
	String subject

	static constraints = {
	}

	static mapping = {
		id generator: 'sequence', params: [sequence: 'grade_sequence']
	}

	static belongsTo = [student: Student]
}
