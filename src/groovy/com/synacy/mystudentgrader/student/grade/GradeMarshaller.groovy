package com.synacy.mystudentgrader.student.grade

import grails.converters.JSON

class GradeMarshaller {

	void register() {
		JSON.registerObjectMarshaller(Grade) { Grade grade ->

			return [
					id        : grade.id,
					subject   : grade.subject,
					finalGrade: grade.finalGrade,
					student   : grade.student
			]
		}
	}

}
