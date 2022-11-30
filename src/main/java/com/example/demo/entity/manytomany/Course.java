package com.example.demo.entity.manytomany;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Course {
	
	@Id
	private int id;
	private String desc;
	
	// The mapping was done in the Student object so just reference the mapping object here.
	// Don't set-up another mapping
	@ManyToMany(mappedBy="courses")
	private Set<Student> students;
	
	public int getCourseId() {
		return id;
	}
	public void setCourseId(int courseId) {
		this.id = courseId;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
	

}
