package com.leafsoft.school.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the StaffDetails database table.
 * 
 */
@NamedQuery(name="StaffDetail.findAll", query="SELECT s FROM StaffDetail s")
public class StaffDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int staffid;

	private BigInteger contactnumber;

	private String email;

	@Temporal(TemporalType.DATE)
	private Date joiningdate;

	@Column(name="prv_experience")
	private String prvExperience;

	private String qualification;

	private String staffname;

	//bi-directional many-to-one association to Designation
	@ManyToOne
	@JoinColumn(name="designationid")
	private Designation designation;

	//bi-directional many-to-one association to StaffSubjectCourseHistory
	@OneToMany(mappedBy="staffDetail")
	private List<StaffSubjectCourseHistory> staffSubjectCourseHistories;

	public StaffDetail() {
	}

	public int getStaffid() {
		return this.staffid;
	}

	public void setStaffid(int staffid) {
		this.staffid = staffid;
	}

	public BigInteger getContactnumber() {
		return this.contactnumber;
	}

	public void setContactnumber(BigInteger contactnumber) {
		this.contactnumber = contactnumber;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getJoiningdate() {
		return this.joiningdate;
	}

	public void setJoiningdate(Date joiningdate) {
		this.joiningdate = joiningdate;
	}

	public String getPrvExperience() {
		return this.prvExperience;
	}

	public void setPrvExperience(String prvExperience) {
		this.prvExperience = prvExperience;
	}

	public String getQualification() {
		return this.qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getStaffname() {
		return this.staffname;
	}

	public void setStaffname(String staffname) {
		this.staffname = staffname;
	}

	public Designation getDesignation() {
		return this.designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public List<StaffSubjectCourseHistory> getStaffSubjectCourseHistories() {
		return this.staffSubjectCourseHistories;
	}

	public void setStaffSubjectCourseHistories(List<StaffSubjectCourseHistory> staffSubjectCourseHistories) {
		this.staffSubjectCourseHistories = staffSubjectCourseHistories;
	}

	public StaffSubjectCourseHistory addStaffSubjectCourseHistory(StaffSubjectCourseHistory staffSubjectCourseHistory) {
		getStaffSubjectCourseHistories().add(staffSubjectCourseHistory);
		staffSubjectCourseHistory.setStaffDetail(this);

		return staffSubjectCourseHistory;
	}

	public StaffSubjectCourseHistory removeStaffSubjectCourseHistory(StaffSubjectCourseHistory staffSubjectCourseHistory) {
		getStaffSubjectCourseHistories().remove(staffSubjectCourseHistory);
		staffSubjectCourseHistory.setStaffDetail(null);

		return staffSubjectCourseHistory;
	}

}