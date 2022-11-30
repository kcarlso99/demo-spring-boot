package com.example.demo.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.manytomany.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {
	
	// NOT recommended to call Stored Procs from Spring JPA 
	// Instead use Spring JDBC. Less error prone.

}
