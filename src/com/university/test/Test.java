package com.university.test;

import com.university.models.Address;
import com.university.models.Department;
import com.university.models.Student;
import com.university.models.Student_Teacher;
import com.university.models.Teacher;


public class Test {

	public static void main(String[] args) {
		
		System.out.println(Department.getAll());
		System.out.println(Address.getAll());
		System.out.println(Student.getAll());
		System.out.println(Teacher.getAll());
		System.out.println(Student_Teacher.getAll());
		

		System.out.println("----------------- - ----------------");
	

		System.out.println(Department.find(8));
		System.out.println(Address.find(1));
		System.out.println(Student.find(2));
		System.out.println(Teacher.find(2));
		System.out.println(Student_Teacher.find(2));
		
		
		
	}

}
