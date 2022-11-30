package com.example.masterclass.collections;

import java.util.List;

public class Department {
	
	private String name;
	private List<Employee> employees;
	
	public Department(String dept) {
		this.name = dept;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	

}
