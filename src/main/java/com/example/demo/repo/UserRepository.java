package com.example.demo.repo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.User;

// Extending CrudRepository provides many DAO helper classes to automatically 
// get data from the DB. Must specify related Entity object and Entity object's PK type.
public interface UserRepository extends CrudRepository<User, Integer> {
	
	//================================================
	// Spring JPA Data Examples
	// To find all options for Spring JPA data, go to 
	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-keywords
	//================================================
	
	List<User> findByUin(String uin);
	
	List<User> findByUinAndEntId(String uin, String entId);
	
	// Keyword Like ==> must NOT use % in the method call. Like not working as-is. Need to use JPQL.
	List<User> findByEntIdLike(String entId);
	
	List<User> findByInactiveDtIsAfter(Date date);
	
	//================================================
	// JPQL Examples
	//================================================
	
	// Keyword Contains ==> must use % in the method call
	// Keyword Contains is not working so using JPQL to define the query. Not sure why.
	@Query("from User where entId like :entID")
	List<User> findByEntIdContains(@Param("entID") String entId);
	
	@Query("from User where entId = :entID")
	List<User> findByEntId(@Param("entID") String entId);
	
	// When using JPQL to modify data, must include the Modifying attribute
	@Modifying
	@Query("delete from User where entId = :entID" )
	void deleteUserByEntId(@Param("entID") String entId);
	
	//================================================
	// Native SQL Examples (for more complex queries)
	// Native SQL must use real table/column names
	//================================================
	
	@Query(value="SELECT * FROM SECAPP.T_USER_INFO where USER_ENTERPRISE_ID = :entID", nativeQuery=true)
	List<User> findUsersByEntIdNQ(@Param("entID") String entId);

}
