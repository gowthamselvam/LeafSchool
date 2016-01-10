package com.leafsoft.school.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the StudentCourseHistory database table.
 * 
 */
@NamedQuery(name="StudentCourseHistory.findAll", query="SELECT s FROM StudentCourseHistory s")
public class StudentCourseHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int studentcoursehistoryid;

	@Temporal(TemporalType.DATE)
	private Date acadamicyear;

	//bi-directional many-to-one association to StudentAcadamicHistory
	@OneToMany(mappedBy="studentCourseHistory")
	private List<StudentAcadamicHistory> studentAcadamicHistories;

	//bi-directional many-to-one association to StudentDetail
	@ManyToOne
	@JoinColumn(name="studentid")
	private StudentDetail studentDetail;

	//bi-directional many-to-one association to Course
	@ManyToOne
	@JoinColumn(name="courseid")
	private Course cours;

	public StudentCourseHistory() {
	}

	public int getStudentcoursehistoryid() {
		return this.studentcoursehistoryid;
	}

	public void setStudentcoursehistoryid(int studentcoursehistoryid) {
		this.studentcoursehistoryid = studentcoursehistoryid;
	}

	public Date getAcadamicyear() {
		return this.acadamicyear;
	}

	public void setAcadamicyear(Date acadamicyear) {
		this.acadamicyear = acadamicyear;
	}

	public List<StudentAcadamicHistory> getStudentAcadamicHistories() {
		return this.studentAcadamicHistories;
	}

	public void setStudentAcadamicHistories(List<StudentAcadamicHistory> studentAcadamicHistories) {
		this.studentAcadamicHistories = studentAcadamicHistories;
	}

	public StudentAcadamicHistory addStudentAcadamicHistory(StudentAcadamicHistory studentAcadamicHistory) {
		getStudentAcadamicHistories().add(studentAcadamicHistory);
		studentAcadamicHistory.setStudentCourseHistory(this);

		return studentAcadamicHistory;
	}

	public StudentAcadamicHistory removeStudentAcadamicHistory(StudentAcadamicHistory studentAcadamicHistory) {
		getStudentAcadamicHistories().remove(studentAcadamicHistory);
		studentAcadamicHistory.setStudentCourseHistory(null);

		return studentAcadamicHistory;
	}

	public StudentDetail getStudentDetail() {
		return this.studentDetail;
	}

	public void setStudentDetail(StudentDetail studentDetail) {
		this.studentDetail = studentDetail;
	}

	public Course getCours() {
		return this.cours;
	}

	public void setCours(Course cours) {
		this.cours = cours;
	}

}