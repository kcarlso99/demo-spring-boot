package com.example.demo.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.PermissionsUsc;

public interface PermissionsUscRepository extends CrudRepository<PermissionsUsc, Integer> {
	
	List<PermissionsUsc> findByCampusCd(String campusCd);
	
	List<PermissionsUsc> findByCampusCdAndCollCd(String campusCd, String collCd);

}
