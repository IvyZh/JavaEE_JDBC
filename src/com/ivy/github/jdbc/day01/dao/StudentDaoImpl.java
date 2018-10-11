package com.ivy.github.jdbc.day01.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ivy.github.jdbc.day01.domain.Student;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class StudentDaoImpl implements IStudentDao {

	static String url = "jdbc:mysql://localhost:3306/mydb";
	static String user = "root";
	static String password = "123456";

	@Override
	public void add(Student student) {
		String sql = "INSERT INTO t_student02(name,age) VALUES('" + student.getName() + "'," + student.getAge() + ");";
		Connection conn = null;
		Statement st = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(url, user, password);
			st = (Statement) conn.createStatement();
			int count = st.executeUpdate(sql);
			System.out.println("count :" + count);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					conn = null;
				}

			if (st != null)
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					st = null;
				}

		}

	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM t_student02 WHERE id=" + id;
		Connection conn = null;
		Statement st = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(url, user, password);
			st = (Statement) conn.createStatement();
			int count = st.executeUpdate(sql);
			System.out.println("count :" + count);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					conn = null;
				}

			if (st != null)
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					st = null;
				}

		}

	}

	@Override
	public void update(Long id, Student student) {
		String sql = "UPDATE t_student02 SET name = '" + student.getName() + "',age=" + student.getAge() + " WHERE id= "
				+ id;
		Connection conn = null;
		Statement st = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(url, user, password);
			st = (Statement) conn.createStatement();
			int count = st.executeUpdate(sql);
			System.out.println("count :" + count);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					conn = null;
				}

			if (st != null)
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					st = null;
				}

		}
	}

	@Override
	public Student get(Long id) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			// 1.加载注册驱动
			String className = "com.mysql.jdbc.Driver";
			Class.forName(className);

			// 2.获取连接对象
			String url = "jdbc:mysql://localhost:3306/mydb";
			conn = (Connection) DriverManager.getConnection(url, "root", "123456");

			// 3. 创建语句对象
			st = (Statement) conn.createStatement();
			// 4. 执行SQL
			String sql = "select * from t_student02 where id=" + id;
			//st.execute(sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				String name = rs.getString("name");
				int age = rs.getInt("age");
				return new Student(id, name, age);
			}

		} catch (Exception e) {
		} finally {
			// 5. 释放资源
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rs = null;
			}
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				st = null;
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}

		return null;
	}

	@Override
	public List<Student> list() {
		List<Student> list = new ArrayList<>();
		ResultSet rs = null;
		// 2.获取连接对象
		String url = "jdbc:mysql://localhost:3306/mydb";
		try (Connection conn = (Connection) DriverManager.getConnection(url, "root", "123456");
				Statement st = (Statement) conn.createStatement();) {
			String className = "com.mysql.jdbc.Driver";
			Class.forName(className);

			String sql = "select * from t_student02;";
			rs = st.executeQuery(sql);

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
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rs = null;
			}
		}

		return null;
	}

}
