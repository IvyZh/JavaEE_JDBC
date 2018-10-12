package com.ivy.github.jdbc.day02.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ivy.github.jdbc.day02.domain.Student;
import com.ivy.github.jdbc.day02.utils.JdbcUtil;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class StudentDaoImpl implements IStudentDao {
	@Override
	public void add(Student student) {
		String sql = "INSERT INTO t_student02(name,age) VALUES(?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, student.getName());
			ps.setInt(2, student.getAge());
			int count = ps.executeUpdate();
			System.out.println("count :" + count);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, null);
		}

	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM t_student02 WHERE id= ?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setLong(1, id);
			int count = ps.executeUpdate();
			System.out.println("count :" + count);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, null);

		}

	}

	@Override
	public void update(Long id, Student student) {
		String sql = "update t_student02 set name = ?,age= ? where id =?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, student.getName());
			ps.setInt(2, student.getAge());
			ps.setLong(3, id);
			int count = ps.executeUpdate();
			System.out.println("count :" + count);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, null);
		}
	}

	@Override
	public Student get(Long id) {
		String sql = "SELECT * FROM t_student02 WHERE id = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setLong(1, id);
			System.out.println("sql:" + sql);
			rs = ps.executeQuery();
			System.out.println("rs= " + rs);
			while (rs.next()) {
				String name = rs.getString("name");
				int age = rs.getInt("age");
				return new Student(id, name, age);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, rs);
		}

		return null;
	}

	@Override
	public List<Student> list() {
		List<Student> list = new ArrayList<>();
		// 2.获取连接对象
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "select * from t_student02;";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				int age = rs.getInt("age");
				long id = rs.getLong("id");
				list.add(new Student(id, name, age));
			}
			return list;

		} catch (Exception e) {
		} finally {
			// 5. 释放资源
			JdbcUtil.close(conn, ps, rs);
		}

		return null;
	}

}
