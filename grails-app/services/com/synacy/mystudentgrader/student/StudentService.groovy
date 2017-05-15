package com.synacy.mystudentgrader.student

import grails.transaction.Transactional

@Transactional
class StudentService {

	public List<Student> fetchAllStudents() {
		return Student.findAll()
	}

	public Student fetchById(Long id) {
		return Student.findById(id)
	}

	public Student createNewStudent(String name, int age, String gender, String yearLevel) {
		Student student = new Student()
		student.name = name
		student.age = age
		student.gender = gender
		student.yearLevel = yearLevel
		return student.save()
	}

	public updateStudent(Student student, String name, int age, String gender, String yearLevel) {
		student.name = name
		student.age = age
		student.gender = gender
		student.yearLevel = yearLevel
		return student.save()
	}

	public deleteStudent(Student student) {
		student.delete()
	}
}
