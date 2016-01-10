package com.leafsoft.school.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Subjects database table.
 * 
 */
@NamedQuery(name="Subject.findAll", query="SELECT s FROM Subject s")
public class Subject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int subjectid;

	private String subjectname;

	private byte subjecttype;

	//bi-directional many-to-one association to StaffSubjectCourseHistory
	@OneToMany(mappedBy="subject")
	private List<StaffSubjectCourseHistory> staffSubjectCourseHistories;

	public Subject() {
	}

	public int getSubjectid() {
		return this.subjectid;
	}

	public void setSubjectid(int subjectid) {
		this.subjectid = subjectid;
	}

	public String getSubjectname() {
		return this.subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public byte getSubjecttype() {
		return this.subjecttype;
	}

	public void setSubjecttype(byte subjecttype) {
		this.subjecttype = subjecttype;
	}

	public List<StaffSubjectCourseHistory> getStaffSubjectCourseHistories() {
		return this.staffSubjectCourseHistories;
	}

	public void setStaffSubjectCourseHistories(List<StaffSubjectCourseHistory> staffSubjectCourseHistories) {
		this.staffSubjectCourseHistories = staffSubjectCourseHistories;
	}

	public StaffSubjectCourseHistory addStaffSubjectCourseHistory(StaffSubjectCourseHistory staffSubjectCourseHistory) {
		getStaffSubjectCourseHistories().add(staffSubjectCourseHistory);
		staffSubjectCourseHistory.setSubject(this);

		return staffSubjectCourseHistory;
	}

	public StaffSubjectCourseHistory removeStaffSubjectCourseHistory(StaffSubjectCourseHistory staffSubjectCourseHistory) {
		getStaffSubjectCourseHistories().remove(staffSubjectCourseHistory);
		staffSubjectCourseHistory.setSubject(null);

		return staffSubjectCourseHistory;
	}

}