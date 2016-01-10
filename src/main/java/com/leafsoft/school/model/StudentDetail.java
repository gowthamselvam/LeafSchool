package com.leafsoft.school.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the StudentDetails database table.
 * 
 */
@NamedQuery(name="StudentDetail.findAll", query="SELECT s FROM StudentDetail s")
public class StudentDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int studentid;

	@Temporal(TemporalType.DATE)
	private Date dob;

	private byte gender;

	private BigInteger regdate;

	private byte status;

	private String studentname;

	//bi-directional many-to-one association to StudentAcadamicHistory
	@OneToMany(mappedBy="studentDetail")
	private List<StudentAcadamicHistory> studentAcadamicHistories;

	//bi-directional many-to-one association to StudentCourseHistory
	@OneToMany(mappedBy="studentDetail")
	private List<StudentCourseHistory> studentCourseHistories;

	//bi-directional many-to-one association to StudentContactDetail
	@ManyToOne
	@JoinColumn(name="familyid")
	private StudentContactDetail studentContactDetail;

	public StudentDetail() {
	}

	public int getStudentid() {
		return this.studentid;
	}

	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public byte getGender() {
		return this.gender;
	}

	public void setGender(byte gender) {
		this.gender = gender;
	}

	public BigInteger getRegdate() {
		return this.regdate;
	}

	public void setRegdate(BigInteger regdate) {
		this.regdate = regdate;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getStudentname() {
		return this.studentname;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}

	public List<StudentAcadamicHistory> getStudentAcadamicHistories() {
		return this.studentAcadamicHistories;
	}

	public void setStudentAcadamicHistories(List<StudentAcadamicHistory> studentAcadamicHistories) {
		this.studentAcadamicHistories = studentAcadamicHistories;
	}

	public StudentAcadamicHistory addStudentAcadamicHistory(StudentAcadamicHistory studentAcadamicHistory) {
		getStudentAcadamicHistories().add(studentAcadamicHistory);
		studentAcadamicHistory.setStudentDetail(this);

		return studentAcadamicHistory;
	}

	public StudentAcadamicHistory removeStudentAcadamicHistory(StudentAcadamicHistory studentAcadamicHistory) {
		getStudentAcadamicHistories().remove(studentAcadamicHistory);
		studentAcadamicHistory.setStudentDetail(null);

		return studentAcadamicHistory;
	}

	public List<StudentCourseHistory> getStudentCourseHistories() {
		return this.studentCourseHistories;
	}

	public void setStudentCourseHistories(List<StudentCourseHistory> studentCourseHistories) {
		this.studentCourseHistories = studentCourseHistories;
	}

	public StudentCourseHistory addStudentCourseHistory(StudentCourseHistory studentCourseHistory) {
		getStudentCourseHistories().add(studentCourseHistory);
		studentCourseHistory.setStudentDetail(this);

		return studentCourseHistory;
	}

	public StudentCourseHistory removeStudentCourseHistory(StudentCourseHistory studentCourseHistory) {
		getStudentCourseHistories().remove(studentCourseHistory);
		studentCourseHistory.setStudentDetail(null);

		return studentCourseHistory;
	}

	public StudentContactDetail getStudentContactDetail() {
		return this.studentContactDetail;
	}

	public void setStudentContactDetail(StudentContactDetail studentContactDetail) {
		this.studentContactDetail = studentContactDetail;
	}

}