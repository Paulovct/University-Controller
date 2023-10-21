package com.university.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.university.Connection.DB;

public class Address extends DB {
	
	private int id = 0;
	private String street;
	private String cep;
	private int number;
	private String type;
	
	
	
	public Address() {}
	public Address(String street, String cep, int number, String type) {
		this.street = street;
		this.cep = cep;
		this.number = number;
		this.type = type;
	}
	private Address(int id, String street, String cep, int number, String type) {
		this.id = id;
		this.street = street;
		this.cep = cep;
		this.number = number;
		this.type = type;
	}
	
	
	public boolean save() {
		StringBuilder s = new StringBuilder();
		s.append("'" + this.street + "'");
		s.append(", '" + this.cep + "'");
		s.append(", " + this.number + "");
		s.append(", '" + this.type + "'");
		String sql = "INSERT INTO addresses (street, cep, number, type) VALUES ("+s+")";
		return DB.insert(sql);
	}
	
	public boolean update() {
		StringBuilder s = new StringBuilder();
		s.append(" street = '" + this.street + "'");
		s.append(", cep = '" + this.cep + "'");
		s.append(", number = " + this.number + "");
		s.append(", type ='" + this.type + "'");
		String sql = "UPDATE addresses SET " + s + " WHERE id = " + this.id;
		return DB.insert(sql);
	}
	
	public static Address find(int id) {
		ResultSet rs = DB.find("addresses", id);
		if(rs != null) {
			try {
				rs.next();
				return  new Address(id, rs.getString("street"), rs.getString("cep"), rs.getInt("number"), rs.getString("type"));
			} catch (SQLException e) {	
				return null;
			}
		} else {
			return null;
		}
	}
	
	public static ArrayList<Address> where(String field, String search) {
		ResultSet rs = DB.where("addresses", field, search);
		return select(rs);
	}
	
	public static ArrayList<Address> getAll() {
		ResultSet rs = DB.getAll("addresses");
		return select(rs);
	}
	
	public boolean delete() {
		if(id != 0) {
			String sql = "DELETE from addresses WHERE id = "+ this.id;
			return DB.execute(sql);
		}
		return false;
	}
	
	public static boolean delete(int id) {
		String sql = "DELETE from addresses WHERE id = " + id;
		return DB.execute(sql);
	}
	
	private static ArrayList<Address> select(ResultSet rs){
		if(rs != null){
			try {
				ArrayList<Address> query = new ArrayList<Address>();
				while(rs.next()) {
					Address address = new Address(rs.getString("street"), rs.getString("cep"), rs.getInt("number"), rs.getString("type"));
					address.setId(rs.getInt("id"));
					query.add(address);
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
	
	public ArrayList<Student> students(){
		if(this.id != 0) 
			return Student.where("address_id", this.id );
		return null;
	}
	
	
	public int getId() {
		return id;
	}
	private void setId(int id) {
		this.id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", cep=" + cep + ", number=" + number + ", type=" + type
				+ "]";
	}
	
}
