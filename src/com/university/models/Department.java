package com.university.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.university.Connection.DB;

public class Department extends DB {

	private int id = 0;
	private String name;


	public Department(){	}
	public Department(String name) {
		this.name = name;
	}
	private Department(int id, String name) {
		this.id = id;
		this.name = name;
	}


	public boolean save() {
		StringBuilder s = new StringBuilder();
		s.append("'"+this.name+"'");
		String sql = "INSERT INTO departments (name) VALUES ("+s+")";
		return DB.insert(sql);
	}

	public boolean update() {
		if(this.id != 0) {
			StringBuilder s = new StringBuilder();
			s.append("name = '"+this.name+"'");
			String sql = "UPDATE departments SET " + s + " WHERE id = " + this.id;
			return DB.insert(sql);
		} 
		return false;
	}

	public static Department find(int id) {
		ResultSet rs = DB.find("departments", id);
		if(rs != null) {
			try {
				rs.next();
				return new Department(rs.getInt("id"), rs.getString("name"));
			} catch (SQLException e) {	
				return null;
			}
		} else {
			return null;
		}
	}

	public static ArrayList<Department> getAll() {
		ResultSet rs = DB.getAll("departments");
		return select(rs);
	}

	public static ArrayList<Department> where(String field, String search) {
		ResultSet rs = DB.where("departments", field, search);
		return select(rs);
	}
	
	public static ArrayList<Department> where(String field, int search) {
		ResultSet rs = DB.where("departments", field, search);
		return select(rs);
	}

	public boolean delete() {
		if(id != 0) {
			String sql = "DELETE from departments WHERE id = "+ this.id;
			return DB.execute(sql);
		}
		return false;
	}

	public static boolean delete(int id) {
		String sql = "DELETE from departments WHERE id = "+id;
		return DB.execute(sql);
	}

	private static ArrayList<Department> select(ResultSet rs) {
		if(rs != null){
			try {
				ArrayList<Department> query = new ArrayList<Department>();
				while(rs.next()) {
					Department dp = new Department(rs.getString("name"));
					dp.setId(rs.getInt("id"));
					query.add(dp);
				}
				return query;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}
	
	public ArrayList<Teacher> teachers(){
		if(this.id != 0)
			return Teacher.where("department_id", this.id);
		return null;
	}


	public int getId() {
		return id;
	}
	private void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}


}
