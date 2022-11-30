package com.example.demo.entity.manytomany;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Student {
	
	@Id
	private int pidm;
	private String uin;
	
	// Assumes T_STUDENT_COURSES table exists with 2 columns:
	// STUDENT PIDM FK -> T_STUDENT.PIDM
	// COURSE_ID 	FK -> T_COURSE.ID
	@ManyToMany( cascade=CascadeType.ALL )
	@JoinTable( 
		name="T_STUDENT_COURSES", 
		joinColumns=@JoinColumn(name="STUDENT_PIDM", referencedColumnName="PIDM"), 
		inverseJoinColumns=@JoinColumn(name="COURSE_ID", referencedColumnName="ID") ) 
	private Set<Course> courses;
	
	public int getPidm() {
		return pidm;
	}
	public void setPidm(int pidm) {
		this.pidm = pidm;
	}
	public String getUin() {
		return uin;
	}
	public void setUin(String uin) {
		this.uin = uin;
	}
	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
	

}
