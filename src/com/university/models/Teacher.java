package com.university.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.university.Connection.DB;

public class Teacher extends DB {

	private int id = 0;
	private String name;
	private int department_id = 0;
	

	public Teacher() {}
	public Teacher(String name, int department_id) {
		this.name = name;
		this.department_id = department_id;
	}
	private Teacher(int id, String name, int department_id) {
		this.id = id;
		this.name = name;
		this.department_id = department_id;
	}


	public boolean save() {
		StringBuilder s = new StringBuilder();
		s.append("'"+this.name+"'");
		s.append(","+this.department_id+"");
		String sql = "INSERT INTO teachers (name, department_id) VALUES ("+s+")";
		return DB.insert(sql);
	}

	public boolean update() {
		StringBuilder s = new StringBuilder();
		s.append("name = '"+this.name+"'");
		s.append(", department_id = "+this.department_id+"");
		String sql = "UPDATE teachers SET " + s + " WHERE id = "+ this.id;
		return DB.insert(sql);
	}

	public static Teacher find(int id) {
		ResultSet rs = DB.find("teachers", id);
		if(rs != null) {
			try {
				rs.next();
				return new Teacher(id, rs.getString("name"), rs.getInt("department_id"));
			} catch (SQLException e) {	
				return null;
			}
		} else {
			return null;
		}
	}

	public static ArrayList<Teacher> where(String field, String search) {
		ResultSet rs = DB.where("teachers", field, search);
		return select(rs);
	}
	
	public static ArrayList<Teacher> where(String field, int search) {
		ResultSet rs = DB.where("teachers", field, search);
		return select(rs);
	}

	public static ArrayList<Teacher> getAll() {
		ResultSet rs = DB.getAll("teachers");
		return select(rs);
	}

	public boolean delete() {
		if(id != 0) {
			String sql = "DELETE from teachers WHERE id = "+ this.id;
			return DB.execute(sql);
		}
		return false;
	}

	public static boolean delete(int id) {
		String sql = "DELETE from teachers WHERE id = "+id;
		return DB.execute(sql);
	}

	private static ArrayList<Teacher> select(ResultSet rs) {
		if(rs != null){
			try {
				ArrayList<Teacher> query = new ArrayList<Teacher>();
				while(rs.next()) {
					Teacher teacher = new Teacher(rs.getString("name"),rs.getInt("department_id"));
					teacher.setId(rs.getInt("id"));
					query.add(teacher);
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
	
	public Department department() {
		if(this.department_id !=  0)
			return Department.find(this.department_id);
		return null;
	}
	
	public ArrayList<Student> students() {
		if(this.id != 0) {
			ArrayList<Student_Teacher> query = Student_Teacher.where("teacher_id", this.id );
			ArrayList<Student> students = new ArrayList<Student>();
			for(Student_Teacher relation : query) {
				Student student = Student.find(relation.getStudent_id());
				if(student != null) {
					students.add(student);
				}
			};
			return students;
		}
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

	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", department_id=" + department_id + "]";
	}
	
}
