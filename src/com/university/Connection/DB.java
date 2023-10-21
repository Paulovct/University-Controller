package com.university.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

	private static Connection getConnection() {
		String url = "jdbc:postgresql://localhost:5432/your-database";
		String user = "user";
		String password = "your-password";
		try {
			return DriverManager.getConnection(url, user, password);
		} catch ( SQLException e) {
			return null;
		} 
	}
	
	private static void close(Connection connection) {
		try {
			if(connection != null)
				connection.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void close(Connection connection, Statement stat) {
		close(connection);
		try {
			stat.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected static ResultSet getAll(String table) {
		String sql = "SELECT * from " + table;
		Connection conn = DB.getConnection();
		try {
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			return null;
		}
	
	}
	
	protected static ResultSet where(String table, String field, String search) {
		String sql = "SELECT * from " + table + " where " + field + " like  '%"+ search + "%'";
		Connection conn = DB.getConnection();
		try {
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			return null;
		}
	
	}
	
	protected static ResultSet where(String table, String field, int search) {
		String sql = "SELECT * from " + table + " where " + field + " =  "+ search;
		Connection conn = DB.getConnection();
		try {
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			return null;
		}
	
	}
	
	protected static ResultSet find(String table, int id) {
		String sql = "SELECT * from "+ table + " where id = " + id;
		Connection conn = DB.getConnection();
		try {
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			return null;
		}
	}
	
	protected static boolean insert(String sql) {
		return execute(sql);
	}
	
	protected static boolean execute(String sql) {
		try {
			Connection conn = getConnection();
			Statement stat = conn.createStatement();
			stat.execute(sql);
			DB.close(conn, stat);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
}
