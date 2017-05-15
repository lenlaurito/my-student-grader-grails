class UrlMappings {

	static mappings = {
		//Student
		"/api/v1/student"(controller: "student") {
			action = [GET: "fetchAllStudents", POST: "createStudent"]
		}
		"/api/v1/student/${studentId}"(controller: "student") {
			action = [GET: "fetchStudent", PUT: "updateStudent", DELETE: "removeStudent"]
		}

		//Grade
		"/api/v1/student/${studentId}/grade/${gradeId}"(controller: "grade") {
			action = [GET: "fetchGradeOfStudent", PUT: "updateGradeOfStudent", DELETE: "deleteGradeOfStudent"]
		}
		"/api/v1/student/${studentId}/grade"(controller: "grade") {
			action = [GET: "fetchAllGradesOfStudent", POST: "createNewGradeForStudent"]
		}

		"/"(view: "/index")
		"500"(view: '/error')
	}
}
