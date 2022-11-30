package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;


@Entity
@Table(name = "T_USER_INFO", schema="SECAPP")		// Annotation needed if Table name does not match class name
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)	// Annotation needed to enable EC Caching of this object
public class User implements Serializable {			// EH Cached objects must implement Serializable to allow objects to be written to disk

	private static final long serialVersionUID = 1L;	// Needed for Serializable objects

	//-- Annotation to use if PK value is stored in another table
	//@TableGenerator(name="user_gen", table="T_USER_SEQ", pkColumnName="SEQ_ID", valueColumnName="SEQ_NBR", allocationSize="1")
	//@GeneratedValue(strategy=GenerationType.TABLE, generator="user_gen")
	//------
	
	// Annotation if PK field is set to AUTO_INCREMENT in DB
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	// GenerationType.AUTO uses select hibernate_sequence.nextval from dual
	
	//-- Annotation for a Custom ID generator
	// @GenericGenerator(name="user_id", strategy="com.example.demo.entity.CustomIDGenerator")
	// @GeneratedValue(generator="user_id")
	//----------
	
	@Id
	@Column(name = "USER_ID")	// Annotation needed if variable name does not match column name
	private Integer userId; 	
	
	@Column(name = "USER_UIN")
	private String uin;
	
	@Column(name = "USER_ENTERPRISE_ID")
	private String entId;
	
	@Column(name = "GROUP_FLAG")
	@Type(type="yes_no")
	private Boolean isGroupFlag;
	
	@Column(name = "GROUP_NAME")
	private String groupName;
	
	@Column(name = "GROUP_EMAIL_ADDR")
	private String groupEmail;
	
	@Column(name = "INACTIVE_DT")
	@Temporal(TemporalType.DATE) // Temporal is required for java.util.Date and java.util.Calendar types
	private Date inactiveDt;
	
	// CasadeType.ALL means whatever happens to User object, also happens to mapped objects
	// So, saving User also saves permissions, etc
	// Without Cascade attribute, User saves will not save associated Permissions
	// FetchType.LAZY = Only loads data when it is accessed. Is the Default.
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	public Set<PermissionsUsc> userPermissionsUsc = new HashSet<PermissionsUsc>();

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUin() {
		return uin;
	}

	public void setUin(String uin) {
		this.uin = uin;
	}

	public String getEntId() {
		return entId;
	}

	public void setEntId(String entId) {
		this.entId = entId;
	}

	public boolean isGroupFlag() {
		return isGroupFlag;
	}

	public void setGroupFlag(boolean isGroupFlag) {
		this.isGroupFlag = isGroupFlag;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupEmail() {
		return groupEmail;
	}

	public void setGroupEmail(String groupEmail) {
		this.groupEmail = groupEmail;
	}

	public Date getInactiveDt() {
		return inactiveDt;
	}

	public void setInactiveDt(Date inactiveDt) {
		this.inactiveDt = inactiveDt;
	}
	
	public Set<PermissionsUsc> getUserPermissionsUsc() {
		return userPermissionsUsc;
	}

	public void setUserPermissionsUsc(Set<PermissionsUsc> userPermissionsUsc) {
		this.userPermissionsUsc = userPermissionsUsc;
	}
	
	/**
	 * Adds the Permissions to this object AND sets the User for the Permissions object
	 * @param perm
	 */
	public void addPermissionUSC(PermissionsUsc perm) {
		if ( perm != null ) {
			if ( userPermissionsUsc == null ) {
				userPermissionsUsc = new HashSet<PermissionsUsc>();
			}
			userPermissionsUsc.add(perm);
			perm.setUser(this);	// avoids having to manually set for every PermissionsUSC object
		}
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", uin=" + uin + ", entId=" + entId + ", isGroupFlag=" + isGroupFlag
				+ ", groupName=" + groupName + ", groupEmail=" + groupEmail + ", inactiveDt=" + inactiveDt + "]";
	}
	

}
