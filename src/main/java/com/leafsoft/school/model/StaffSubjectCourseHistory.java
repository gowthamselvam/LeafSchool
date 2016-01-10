package com.leafsoft.school.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the StaffSubjectCourseHistory database table.
 * 
 */
@NamedQuery(name="StaffSubjectCourseHistory.findAll", query="SELECT s FROM StaffSubjectCourseHistory s")
public class StaffSubjectCourseHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int staffsubjectcourseid;

	@Temporal(TemporalType.DATE)
	private Date acadamicyear;

	//bi-directional many-to-one association to ExamSubjectMapping
	@OneToMany(mappedBy="staffSubjectCourseHistory")
	private List<ExamSubjectMapping> examSubjectMappings;

	//bi-directional many-to-one association to StaffDetail
	@ManyToOne
	@JoinColumn(name="staffid")
	private StaffDetail staffDetail;

	//bi-directional many-to-one association to Subject
	@ManyToOne
	@JoinColumn(name="subjectid")
	private Subject subject;

	//bi-directional many-to-one association to Course
	@ManyToOne
	@JoinColumn(name="courseid")
	private Course cours;

	public StaffSubjectCourseHistory() {
	}

	public int getStaffsubjectcourseid() {
		return this.staffsubjectcourseid;
	}

	public void setStaffsubjectcourseid(int staffsubjectcourseid) {
		this.staffsubjectcourseid = staffsubjectcourseid;
	}

	public Date getAcadamicyear() {
		return this.acadamicyear;
	}

	public void setAcadamicyear(Date acadamicyear) {
		this.acadamicyear = acadamicyear;
	}

	public List<ExamSubjectMapping> getExamSubjectMappings() {
		return this.examSubjectMappings;
	}

	public void setExamSubjectMappings(List<ExamSubjectMapping> examSubjectMappings) {
		this.examSubjectMappings = examSubjectMappings;
	}

	public ExamSubjectMapping addExamSubjectMapping(ExamSubjectMapping examSubjectMapping) {
		getExamSubjectMappings().add(examSubjectMapping);
		examSubjectMapping.setStaffSubjectCourseHistory(this);

		return examSubjectMapping;
	}

	public ExamSubjectMapping removeExamSubjectMapping(ExamSubjectMapping examSubjectMapping) {
		getExamSubjectMappings().remove(examSubjectMapping);
		examSubjectMapping.setStaffSubjectCourseHistory(null);

		return examSubjectMapping;
	}

	public StaffDetail getStaffDetail() {
		return this.staffDetail;
	}

	public void setStaffDetail(StaffDetail staffDetail) {
		this.staffDetail = staffDetail;
	}

	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Course getCours() {
		return this.cours;
	}

	public void setCours(Course cours) {
		this.cours = cours;
	}

}