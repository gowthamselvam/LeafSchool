package com.leafsoft.school.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ExamSubjectMapping database table.
 * 
 */
@NamedQuery(name="ExamSubjectMapping.findAll", query="SELECT e FROM ExamSubjectMapping e")
public class ExamSubjectMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int examsubjectid;

	@Temporal(TemporalType.DATE)
	private Date acadamicyear;

	//bi-directional many-to-one association to ExamDetail
	@ManyToOne
	@JoinColumn(name="examid")
	private ExamDetail examDetail;

	//bi-directional many-to-one association to StaffSubjectCourseHistory
	@ManyToOne
	@JoinColumn(name="staffsubjectcourseid")
	private StaffSubjectCourseHistory staffSubjectCourseHistory;

	//bi-directional many-to-one association to StudentAcadamicHistory
	@OneToMany(mappedBy="examSubjectMapping")
	private List<StudentAcadamicHistory> studentAcadamicHistories;

	public ExamSubjectMapping() {
	}

	public int getExamsubjectid() {
		return this.examsubjectid;
	}

	public void setExamsubjectid(int examsubjectid) {
		this.examsubjectid = examsubjectid;
	}

	public Date getAcadamicyear() {
		return this.acadamicyear;
	}

	public void setAcadamicyear(Date acadamicyear) {
		this.acadamicyear = acadamicyear;
	}

	public ExamDetail getExamDetail() {
		return this.examDetail;
	}

	public void setExamDetail(ExamDetail examDetail) {
		this.examDetail = examDetail;
	}

	public StaffSubjectCourseHistory getStaffSubjectCourseHistory() {
		return this.staffSubjectCourseHistory;
	}

	public void setStaffSubjectCourseHistory(StaffSubjectCourseHistory staffSubjectCourseHistory) {
		this.staffSubjectCourseHistory = staffSubjectCourseHistory;
	}

	public List<StudentAcadamicHistory> getStudentAcadamicHistories() {
		return this.studentAcadamicHistories;
	}

	public void setStudentAcadamicHistories(List<StudentAcadamicHistory> studentAcadamicHistories) {
		this.studentAcadamicHistories = studentAcadamicHistories;
	}

	public StudentAcadamicHistory addStudentAcadamicHistory(StudentAcadamicHistory studentAcadamicHistory) {
		getStudentAcadamicHistories().add(studentAcadamicHistory);
		studentAcadamicHistory.setExamSubjectMapping(this);

		return studentAcadamicHistory;
	}

	public StudentAcadamicHistory removeStudentAcadamicHistory(StudentAcadamicHistory studentAcadamicHistory) {
		getStudentAcadamicHistories().remove(studentAcadamicHistory);
		studentAcadamicHistory.setExamSubjectMapping(null);

		return studentAcadamicHistory;
	}

}