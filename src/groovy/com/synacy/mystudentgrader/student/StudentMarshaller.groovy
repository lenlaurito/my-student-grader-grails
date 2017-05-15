package com.synacy.mystudentgrader.student

import grails.converters.JSON

class StudentMarshaller {

	void register() {
		JSON.registerObjectMarshaller(Student) { Student student ->

			return [
					id       : student.id,
					name     : student.name,
					age      : student.age,
					gender   : student.gender,
					yearLevel: student.yearLevel
			]
		}
	}

}
