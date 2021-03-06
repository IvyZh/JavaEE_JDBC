package com.ivy.github.jdbc.day01;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class ConnectionTest {

	static String url = "jdbc:mysql://localhost:3306/mydb";
	static String user = "root";
	static String password = "123456";

	public static void main(String[] args) throws Exception {

		// DDL，如创建表
		//createTable();

		//DML，增删改
		//addData();
		//deleteData();
		//updateData();

		//DQL，查询
		//selectAllData();
	}

	private static void updateData() {
		String sql = "UPDATE t_student02 SET name = 'AA' WHERE name= 'bb';";
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

	private static void deleteData() {
		String sql = "DELETE FROM t_student02 WHERE name='xixi';";
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

	private static void addData() {

		int age = new Random().nextInt(88);
		String sql = "INSERT INTO t_student02(name,age) VALUES('haha'," + age + ");";
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

	private static void selectAllData() throws Exception {
		// 1.加载注册驱动
		String className = "com.mysql.jdbc.Driver";
		Class.forName(className);

		// 2.获取连接对象
		String url = "jdbc:mysql://localhost:3306/mydb";
		Connection conn = (Connection) DriverManager.getConnection(url, "root", "123456");

		// 3. 创建语句对象
		Statement st = (Statement) conn.createStatement();

		// 4. 执行SQL
		String sql = "select * from t_student";
		//st.execute(sql);
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			String name = rs.getString("name");
			int age = rs.getInt("age");
			System.out.println(name + "..." + age);
		}
		// 5. 释放资源
		rs.close();
		st.close();
		conn.close();

	}

	private static void createTable() {
		Connection conn = null;
		Statement st = null;
		try {
			// 1. 加载注册驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 2. 获取连接对象
			conn = (Connection) DriverManager.getConnection(url, user, password);

			// 3. 创建语句对象
			st = (Statement) conn.createStatement();

			// 4. 执行语句
			String sql = "CREATE table t_student02(id BIGINT PRIMARY KEY AUTO_INCREMENT,\r\n" + "name VARCHAR(20),\r\n"
					+ "age INT\r\n" + ")";
			int count = st.executeUpdate(sql);
			System.out.println("count :" + count);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5. 释放资源
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

}
