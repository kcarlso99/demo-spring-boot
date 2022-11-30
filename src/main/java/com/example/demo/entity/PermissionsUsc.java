package com.example.demo.entity;

import java.time.Instant;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.transaction.annotation.Transactional;

/**
 * The persistent class for the SECAPP.T_PERMISSIONS_INITIATOR database table.
 * 
 * Note re: TIME/DATE/TIMESTAMP DB fields
 * Since Java 8, the new Java Date and Time API is available for dealing with temporal values. 
 * The types from the java.time package are directly mapped to corresponding SQL types.
 * So, there's no need to specify @Temporal annotation:

LocalDate is mapped to DATE.
LocalTime and OffsetTime are mapped to TIME.
Instant, LocalDateTime, OffsetDateTime and ZonedDateTime are mapped to TIMESTAMP.
 * 
 */		
@Entity
@Transactional
@Table(name = "T_PERMISSIONS_INITIATOR", schema="SECAPP")
public class PermissionsUsc {
	
	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY )
	@Column(name = "INITIATOR_ID")
	private Integer id; 
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	public User user;
	
	@Column(name = "CAMPUS_CD", length = 3) 	// 3-digit campus code
	private String campusCd;
	
	@Column(name = "COLL_CD", length = 2) 		// 2-digit coll code
	private String collCd;
	
	@Column(name = "DEPT_CD", length = 3) 		// 3-digit dept code
	private String deptCd;
	
	@Column(name="READ_ONLY_FLAG")
	@Type(type="yes_no")
	private boolean isReadOnly = false; // default to false
	
	@Column(name="PRIMARY_FLAG")
	@Type(type="yes_no")
	private boolean isPrimary; 
	
	@Column(name = "UPDATED_BY_ID")
	private Integer updatedByUserId = 14177; // Default to Application
	
	@Column(name = "UPDATED_TIME")
	//@Temporal(TemporalType.TIMESTAMP) 
	private Instant activDate = Instant.now(); // default to current time
	
	@Column(name = "CREATE_DT")
	private LocalDate createDt = LocalDate.now(); // default to current date
	
	@Column(name = "REMOVE_DT")
	//@Temporal(TemporalType.DATE) 
	private LocalDate removeDt;
	
	// Constructor
	public PermissionsUsc() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCampusCd() {
		return campusCd;
	}

	public void setCampusCd(String campusCd) {
		this.campusCd = campusCd;
	}

	public String getCollCd() {
		return collCd;
	}

	public void setCollCd(String collCd) {
		this.collCd = collCd;
	}

	public String getDeptCd() {
		return deptCd;
	}

	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}

	public boolean isReadOnly() {
		return isReadOnly;
	}

	public void setReadOnly(boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}

	public boolean isPrimary() {
		return isPrimary;
	}

	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	public Integer getUpdatedByUserId() {
		return updatedByUserId;
	}

	public void setUpdatedByUserId(Integer updatedByUserId) {
		this.updatedByUserId = updatedByUserId;
	}

	public Instant getActivDate() {
		return activDate;
	}

	public void setActivDate(Instant activDate) {
		this.activDate = activDate;
	}

	public LocalDate getCreateDt() {
		return createDt;
	}

	public void setCreateDt(LocalDate createDt) {
		this.createDt = createDt;
	}

	public LocalDate getRemoveDt() {
		return removeDt;
	}

	public void setRemoveDt(LocalDate removeDt) {
		this.removeDt = removeDt;
	}

	@Override
	public String toString() {
		return "PermissionsUsc [id=" + id + ", user=" + user + ", campusCd=" + campusCd + ", collCd=" + collCd
				+ ", deptCd=" + deptCd + ", isReadOnly=" + isReadOnly + ", isPrimary=" + isPrimary
				+ ", updatedByUserId=" + updatedByUserId + ", activDate=" + activDate + ", createDt=" + createDt
				+ ", removeDt=" + removeDt + "]";
	}
	
}
