package com.example.demo.service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.PermissionsUsc;
import com.example.demo.entity.User;
import com.example.demo.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepo = null;

	@Override
	@Transactional
	public void transferPermissionsUsc(String entIdFrom, String entIdTo) {

		// Get User data
		User userFrom = userRepo.findByEntId(entIdFrom).get(0);
		User userTo = userRepo.findByEntId(entIdTo).get(0);
		
		// Get Permissions to Transfer
		Set<PermissionsUsc> permsFrom = userFrom.getUserPermissionsUsc();
		
		permsFrom.forEach(p -> {
			
			// Copy non-expired permission to User2
			if (p.getRemoveDt() == null) {
				PermissionsUsc perm = new PermissionsUsc();
				perm.setCampusCd(p.getCampusCd());
				perm.setCollCd(p.getCollCd());
				perm.setDeptCd(p.getDeptCd());
				perm.setPrimary(p.isPrimary());
				perm.setReadOnly(p.isReadOnly());	
				userTo.addPermissionUSC(perm);

				// End permission for User1
				p.setRemoveDt(LocalDate.now());
				p.setUpdatedByUserId(14177);
				p.setActivDate(Instant.now());
			}

		});
		
		System.out.println("Transferring " + permsFrom.size() + " permissions FROM " + userFrom + " TO " + userTo);
		
		// Save changes
		userRepo.save(userFrom);
		userRepo.save(userTo);
		
	}


}
