package com.leafsoft.school.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ExamType database table.
 * 
 */
@NamedQuery(name="ExamType.findAll", query="SELECT e FROM ExamType e")
public class ExamType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int examtypeid;

	private String examtypename;

	//bi-directional many-to-one association to ExamDetail
	@OneToMany(mappedBy="examType")
	private List<ExamDetail> examDetails;

	public ExamType() {
	}

	public int getExamtypeid() {
		return this.examtypeid;
	}

	public void setExamtypeid(int examtypeid) {
		this.examtypeid = examtypeid;
	}

	public String getExamtypename() {
		return this.examtypename;
	}

	public void setExamtypename(String examtypename) {
		this.examtypename = examtypename;
	}

	public List<ExamDetail> getExamDetails() {
		return this.examDetails;
	}

	public void setExamDetails(List<ExamDetail> examDetails) {
		this.examDetails = examDetails;
	}

	public ExamDetail addExamDetail(ExamDetail examDetail) {
		getExamDetails().add(examDetail);
		examDetail.setExamType(this);

		return examDetail;
	}

	public ExamDetail removeExamDetail(ExamDetail examDetail) {
		getExamDetails().remove(examDetail);
		examDetail.setExamType(null);

		return examDetail;
	}

}