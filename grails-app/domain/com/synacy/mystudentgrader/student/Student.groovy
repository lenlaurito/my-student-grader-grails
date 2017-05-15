package com.synacy.mystudentgrader.student

import com.synacy.mystudentgrader.student.grade.Grade

class Student {

	Long id
	String name
	Integer age
	Gender gender
	String yearLevel

	static constraints = {
	}

	static mapping = {
		id generator: 'sequence', params: [sequence: 'student_sequence']
	}

	static hasMany = [
			grades: Grade
	]
}
