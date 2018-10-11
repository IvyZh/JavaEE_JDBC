package com.ivy.github.jdbc.day01.test;

import java.util.List;

import org.junit.Test;

import com.ivy.github.jdbc.day01.dao.IStudentDao;
import com.ivy.github.jdbc.day01.dao.StudentDaoImpl;
import com.ivy.github.jdbc.day01.domain.Student;

public class IStudentDaoTest {

	@Test
	public void testAdd() {
		IStudentDao dao = new StudentDaoImpl();
		Student student = new Student();
		student.setAge(18);
		student.setName("张无忌");
		dao.add(student);
	}

	@Test
	public void testDelete() {
		IStudentDao dao = new StudentDaoImpl();
		dao.delete(2L);
	}

	@Test
	public void testUpdate() {
		IStudentDao dao = new StudentDaoImpl();
		Student student = new Student();
		student.setAge(18);
		student.setName("赵敏");
		dao.update(3L, student);
	}

	@Test
	public void testGet() {
		IStudentDao dao = new StudentDaoImpl();
		Student s = dao.get(24L);
		System.out.println("get:" + s);
	}

	@Test
	public void testList() {
		IStudentDao dao = new StudentDaoImpl();
		List<Student> s = dao.list();
		System.out.println("list:" + s);
	}

}
