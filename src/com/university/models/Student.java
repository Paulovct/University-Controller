package com.university.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.university.Connection.DB;

public class Student extends DB {

	private int id = 0;
	private String name;
	private String cpf;
	private int address_id = 0;
	
	
	public Student() {}
	public Student(String name, String cpf, int address_id) {
		super();
		this.name = name;
		this.cpf = cpf;
		this.address_id = address_id;
	}
	private Student(int id, String name, String cpf, int address_id) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.address_id = address_id;
	}
	
	
	public boolean save() {
		StringBuilder s = new StringBuilder();
		s.append("'"+this.name+"'");
		s.append(",'"+this.cpf+"'");
		s.append(","+this.address_id+"");
		String sql = "INSERT INTO students (name, cpf, address_id) VALUES ("+s+")";
		return DB.insert(sql);
	}
	
	public boolean update() {
		StringBuilder s = new StringBuilder();
		s.append("name ='"+this.name+"'");
		s.append(", cpf = '"+this.cpf+"'");
		s.append(", address_id = "+this.address_id+"");
		String sql = "UPDATE students SET " + s + " WHERE id = " + this.id;
		return DB.insert(sql);
	}
	
	public static Student find(int id) {
		ResultSet rs = DB.find("students", id);
		if(rs != null) {
			try {
				rs.next();
				return new Student(id, rs.getString("name"),rs.getString("cpf"),rs.getInt("address_id"));
			} catch (SQLException e) {	
				return null;
			}
		} else {
			return null;
		}
	}
	
	public static ArrayList<Student> where(String field, String search){
		ResultSet rs = DB.where("students", field, search);
		return select(rs);
	}
	
	public static ArrayList<Student> where(String field, int search){
		ResultSet rs = DB.where("students", field, search);
		return select(rs);
	}
	
	public static ArrayList<Student> getAll() {
		ResultSet rs = DB.getAll("students");
		return select(rs);
	}
	
	public boolean delete() {
		if(id != 0) {
			String sql = "DELETE from students WHERE id = "+ this.id;
			return DB.execute(sql);
		}
		return false;
	}
	
	public static boolean delete(int id) {
		String sql = "DELETE from students WHERE id = "+id;
		return DB.execute(sql);
	}
	
	private static ArrayList<Student> select(ResultSet rs){
		if(rs != null){
			try {
				ArrayList<Student> query = new ArrayList<Student>();
				while(rs.next()) {
					Student student = new Student(rs.getString("name"),rs.getString("cpf"),rs.getInt("address_id"));
					student.setId(rs.getInt("id"));
					query.add(student);
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
	
	public Address address() {
		if(this.address_id != 0) {
			return Address.find(address_id);
		}
		return null;
	}
	
	public ArrayList<Teacher> teachers() {
		if(this.id != 0) {
			ArrayList<Student_Teacher> query = Student_Teacher.where("student_id", this.id );
			ArrayList<Teacher> teachers = new ArrayList<Teacher>();
			for(Student_Teacher relation : query) {
				Teacher teacher = Teacher.find(relation.getTeacher_id());
				if(teacher != null) {
					teachers.add(teacher);
				}
			};
			return teachers;
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
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public int getAddress_id() {
		return address_id;
	}
	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", cpf=" + cpf + ", address_id=" + address_id + "]";
	}

}
