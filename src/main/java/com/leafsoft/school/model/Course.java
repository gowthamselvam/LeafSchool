package com.leafsoft.school.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the courses database table.
 * 
 */
@NamedQuery(name="Course.findAll", query="SELECT c FROM Course c")
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int courseid;

	private String course;

	private String section;

	//bi-directional many-to-one association to StaffSubjectCourseHistory
	@OneToMany(mappedBy="cours")
	private List<StaffSubjectCourseHistory> staffSubjectCourseHistories;

	//bi-directional many-to-one association to StudentCourseHistory
	@OneToMany(mappedBy="cours")
	private List<StudentCourseHistory> studentCourseHistories;

	public Course() {
	}

	public int getCourseid() {
		return this.courseid;
	}

	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}

	public String getCourse() {
		return this.course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getSection() {
		return this.section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public List<StaffSubjectCourseHistory> getStaffSubjectCourseHistories() {
		return this.staffSubjectCourseHistories;
	}

	public void setStaffSubjectCourseHistories(List<StaffSubjectCourseHistory> staffSubjectCourseHistories) {
		this.staffSubjectCourseHistories = staffSubjectCourseHistories;
	}

	public StaffSubjectCourseHistory addStaffSubjectCourseHistory(StaffSubjectCourseHistory staffSubjectCourseHistory) {
		getStaffSubjectCourseHistories().add(staffSubjectCourseHistory);
		staffSubjectCourseHistory.setCours(this);

		return staffSubjectCourseHistory;
	}

	public StaffSubjectCourseHistory removeStaffSubjectCourseHistory(StaffSubjectCourseHistory staffSubjectCourseHistory) {
		getStaffSubjectCourseHistories().remove(staffSubjectCourseHistory);
		staffSubjectCourseHistory.setCours(null);

		return staffSubjectCourseHistory;
	}

	public List<StudentCourseHistory> getStudentCourseHistories() {
		return this.studentCourseHistories;
	}

	public void setStudentCourseHistories(List<StudentCourseHistory> studentCourseHistories) {
		this.studentCourseHistories = studentCourseHistories;
	}

	public StudentCourseHistory addStudentCourseHistory(StudentCourseHistory studentCourseHistory) {
		getStudentCourseHistories().add(studentCourseHistory);
		studentCourseHistory.setCours(this);

		return studentCourseHistory;
	}

	public StudentCourseHistory removeStudentCourseHistory(StudentCourseHistory studentCourseHistory) {
		getStudentCourseHistories().remove(studentCourseHistory);
		studentCourseHistory.setCours(null);

		return studentCourseHistory;
	}

}