package com.example.demo;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.PermissionsUsc;
import com.example.demo.entity.User;
import com.example.demo.entity.manytomany.Course;
import com.example.demo.entity.manytomany.Student;
import com.example.demo.repo.PermissionsUscRepository;
import com.example.demo.repo.StudentRepository;
import com.example.demo.repo.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringDataTests {
	
	@Autowired
	UserRepository ur;
	
	@Autowired
	PermissionsUscRepository pr;
	
	@Autowired
	StudentRepository sr;

	
	@Test
	void getUser() {
		User user = ur.findById(7088).get();
		System.out.println("User ================> " + user.toString());
	}
	
	@Test
	void getUserByUin() {
		List<User> users = ur.findByUin("657346401");
		User user = users.get(0);
		
		if (user != null) {
			System.out.println("First User ================> " + user);
		}
		
	}
	@Test
	void getUserByUinEntId() {
		List<User> users = ur.findByUinAndEntId("665526362", "slzbrgr2");
		System.out.println("User ================> " + users.get(0));
	}
	
	@Test
	void getUserByEntIdLike() {
		// For Like, must NOT use %
		// Not sure why not working. Should be returning several rows.
		List<User> users = ur.findByEntIdLike("user");
		System.out.println("Users ================> " + users.size());
		users.forEach(u -> System.out.println( u.getEntId() ));
	}
	
	@Test
	void getUserByEntIdContains() {
		List<User> users = ur.findByEntIdContains("user%");
		System.out.println("Users ================> " + users.size());
		users.forEach(u -> System.out.println( u ));
	}
	
	@Test 
	void getByEntId() {
		List<User> users = ur.findByEntId("user5");
		System.out.println("Users ================> " + users.size());
		users.forEach(u -> System.out.println( u ));
	}
	
	@Test 
	void getUsersByEntIdNQ() {
		List<User> users = ur.findUsersByEntIdNQ("user5");
		System.out.println("Users ================> " + users.size());
		users.forEach(u -> System.out.println( u ));
	}

	@Test
	void createUser() {
		User user = new User();
		user.setEntId("Test1");
		ur.save(user);
		System.out.println("Created User ================> " + user);
	}
	
	@Test
	void createUserAndMappedEntitites() {
		// New User
		User user = new User();
		user.setUserId(102000);
		user.setEntId("Test2");
		
		// New User Permissions	
		LocalDate date = LocalDate.now();
		Instant time = Instant.now();
		int id = 10600;
		PermissionsUsc perm1 = new PermissionsUsc();
		perm1.setId(id++);
		perm1.setCampusCd("100");
		perm1.setCollCd("KN");
		perm1.setDeptCd("588");
		perm1.setUpdatedByUserId(14159);
		perm1.setActivDate(time);
		perm1.setCreateDt(date);
		perm1.setPrimary(true);
		user.addPermissionUSC(perm1);
		// perm1.setUser(user);	// need to set the FK value IF the parent object doesn't set it
		
		PermissionsUsc perm2 = new PermissionsUsc();
		perm2.setId(id++);
		perm2.setCampusCd("200");
		perm2.setCollCd("JN");
		perm2.setDeptCd("588");
		perm2.setUpdatedByUserId(14159);
		perm2.setActivDate(time);
		perm2.setCreateDt(date);
		perm2.setPrimary(true);
		//perm2.setUser(user);	// need to set the FK value IF the parent object doesn't set it
		user.addPermissionUSC(perm2);
		
		ur.save(user);
		System.out.println("Created User ================> " + user);
	}
	
	@Test
	void getUscsByCampusAndColl() {
		List<PermissionsUsc> uscs = pr.findByCampusCdAndCollCd("100", "KN");
		System.out.println("USCs ================> " + uscs.size());
	}

	
	//------------------------------------------------------
	// ManyToMany Examples
	//------------------------------------------------------
	
	@Test
	void updateStudent() {
		Student student = new Student();
		student.setPidm(1234);
		student.setUin("123456789");
		
		Set<Course> courses = new HashSet<Course>();
		
		Course course = new Course();
		course.setCourseId(1);
		course.setDesc("Biology 101");
		courses.add(course);
		
		student.setCourses(courses);
		
		sr.save(student);// Will save to T_STUDENT, T_COURSE, and T_STUDENT_COURSE	
	}
	
	
	@Test
	@Transactional	// Transactional annotation required to lazy load the Courses
	void findStudent() {
		Student stu = sr.findById(1).get();
		System.out.println(stu);
		System.out.println(stu.getCourses().size());
	}


}
