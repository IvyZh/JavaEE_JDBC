package com.ivy.github.jdbc.day02.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class JdbcUtil {
	private static Properties p = new Properties();
	// 1. 加载数据库驱动
	static {
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream is = loader.getResourceAsStream("db.properties");
			p.load(is);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			Class.forName(p.getProperty("driverClassName"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("加载数据库驱动异常！");
		}
	}

	// 2. 获取连接对象
	public static Connection getConnection() {
		try {
			return (Connection) DriverManager.getConnection(p.getProperty("url"), p.getProperty("username"),
					p.getProperty("password"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException("获取数据库连接对象异常！");
	}

	// 关闭资源
	public static void close(Connection conn, Statement st, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			rs = null;
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
				st = null;
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					conn = null;
				}
			}
		}

	}
}
