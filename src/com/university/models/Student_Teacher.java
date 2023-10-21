package com.university.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.university.Connection.DB;

public class Student_Teacher extends DB {
	
	private int id = 0;
	private int teacher_id = 0;
	private int student_id = 0;
	
	
	public Student_Teacher() {}
	public Student_Teacher(int teacher_id, int student_id) {
		this.teacher_id = teacher_id;
		this.student_id = student_id;
	}
	private Student_Teacher(int id, int teacher_id, int student_id) {
		this.id = id;
		this.teacher_id = teacher_id;
		this.student_id = student_id;
	}
	
	
	public boolean save() {
		StringBuilder s = new StringBuilder();
		s.append(this.teacher_id);
		s.append(","+this.student_id);
		String sql = "INSERT INTO student_teacher (teacher_id, student_id) VALUES ("+s+")";
		return DB.insert(sql);
	}
	
	public boolean update() {
		StringBuilder s = new StringBuilder();
		s.append("teacher_id =" +this.teacher_id);
		s.append(", student_id ="+this.student_id);
		String sql = "UPDATE student_teacher SET " + s + " WHERE id = "+ this.id;
		return DB.insert(sql);
	}
	
	public static Student_Teacher find(int id) {
		ResultSet rs = DB.find("student_teacher", id);
		if(rs != null) {
			try {
				rs.next();
				return  new Student_Teacher(id ,rs.getInt("teacher_id"), rs.getInt("student_id"));
			} catch (SQLException e) {	
				return null;
			}
		} else {
			return null;
		}
	}
	
	public static ArrayList<Student_Teacher> where(String field, int search){
		ResultSet rs = DB.where("student_teacher", field, search);
		return select(rs);
	}
	
	public static ArrayList<Student_Teacher> getAll() {
		ResultSet rs = DB.getAll("student_teacher");
		return select(rs);
	}
	
	public boolean delete() {
		if(id != 0) {
			String sql = "DELETE from student_teacher WHERE id = "+ this.id;
			return DB.execute(sql);
		}
		return false;
	}
	
	public static boolean delete(int id) {
		String sql = "DELETE from student_teacher WHERE id = "+id;
		return DB.execute(sql);
	}
	
	private static ArrayList<Student_Teacher> select(ResultSet rs){
		if(rs != null){
			try {
				ArrayList<Student_Teacher> query = new ArrayList<Student_Teacher>();
				while(rs.next()) {
					Student_Teacher st = new Student_Teacher(rs.getInt("teacher_id"), rs.getInt("student_id"));
					st.setId(rs.getInt("id"));
					query.add(st);
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
	
	public Teacher teacher() {
		if(this.teacher_id != 0)
			return Teacher.find(this.teacher_id);
		return null;
	}
	
	public Student student() {
		if(this.student_id != 0)
			return Student.find(this.student_id);
		return null;
	}
	
	
	public int getId() {
		return id;
	}
	private void setId(int id) {
		this.id = id;
	}
	public int getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	@Override
	public String toString() {
		return "Student_Teacher [id=" + id + ", teacher_id=" + teacher_id + ", student_id=" + student_id + "]";
	}
	
}
