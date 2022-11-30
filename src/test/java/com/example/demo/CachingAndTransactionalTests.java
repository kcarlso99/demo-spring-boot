package com.example.demo;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.PermissionsUsc;
import com.example.demo.entity.User;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CachingAndTransactionalTests {

	// EntityManager provides access to the Session and data cache
	@Autowired
	EntityManager entityMgr;

	@Autowired
	UserRepository ur;
	
	@Autowired
	UserServiceImpl service;


	//-----------------------------------------------------------------
	// Caching
	// Level 1 - By default, Spring uses level 1 caching. Each Hibernate session has it's own cache.
	// Level 2 - (aka EH Cache) Level 2 caching is when all Hibernate sessions share the same cache. Fast and lightweight. Used by most Java EE apps.
	//
	// Adding Level 2 / EH Cache to app:
	// 1. Add Maven Dependency
	// 2. Configure Cache
	// 3. Create ehcache.xml
	// 4. Make entities cacheable
	//--------------------------------------------------------------------
	@Test
	@Transactional	// Transactional annotation required for caching
	void testCaching() {
		ur.findById(7088).get();	// 1st retrieve will be from DB
		ur.findById(7088).get();	// 2nd will be from L1 cache
		ur.findById(7088).get();	// 3rd will be from L1 cache
		// the log will show only 1 Hibernate SQL statement b/c only 1 trip to the DB. Rest is from Cache.
	}

	@Test
	@Transactional	
	void testEvictCache() {
		Session session = entityMgr.unwrap(Session.class);	// Gives access to the Session

		User user = ur.findById(7088).get();	// 1st retrieve will be from DB
		ur.findById(7088).get();	// 2nd will be retrieved from L1 cache

		session.evict(user);	// remove user object from Level 1 cache

		ur.findById(7088).get();	// Now will retrieve from DB
	}
	
	//------------------------------------------------------
	// Transactional and Lazy Loading Examples
	//------------------------------------------------------
	
	// This test only works with FetchType set to EAGER. Exception if LAZY.
	@Test
	void getUserPermissionsByUin_Eager() {
		List<User> users = ur.findByUin("657346401");
		User user = users.get(0);
		
		if (user != null) {
			System.out.println("First User ================> " + user);
			
			// Get Permissions with EAGER loading
			Set<PermissionsUsc> perms = user.getUserPermissionsUsc();
			System.out.println("Permissions USC ================> " + perms);
		}
		
	}
	
	
	@Test
	void transferUscPerms() {
		service.transferPermissionsUsc("rharden3", "dkjohnst");
	}
	
	
	
	
	
	@Test
	@Transactional 		// needed when modifying DB
	@Rollback(false)	// only use for Testing. Defaults to True so testing rolls back changes unless true.
	void deleteUserByEntId() {
		ur.deleteUserByEntId("user2");
	}
	
	// Transactional annotation required for Lazy loading to work
	@Test
	@Transactional
	void getUserPermissionsByUin_Lazy() {
		// Get attached objects with LAZY loading
		User user = ur.findById(102000).get();
		// Output Perms
		Set<PermissionsUsc> perms = user.getUserPermissionsUsc();
		perms.forEach(perm -> System.out.println(perm));
	}
	
	@Test
	@Transactional
	@Rollback(false)
	void updateUser_Lazy() {
		// Get attached objects with LAZY loading
		User user = ur.findById(102000).get();
		
		// Update Perms
		Set<PermissionsUsc> perms = user.getUserPermissionsUsc();
		perms.forEach(perm -> perm.setCampusCd("100"));
		
		ur.save(user);
	}

}
