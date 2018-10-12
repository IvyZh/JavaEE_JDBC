package com.ivy.github.jdbc.day02.dao;

import java.util.List;

import com.ivy.github.jdbc.day02.domain.Student;

public interface IStudentDao {
	void add(Student student);

	void delete(Long id);

	void update(Long id, Student student);

	Student get(Long id);

	List<Student> list();
}
