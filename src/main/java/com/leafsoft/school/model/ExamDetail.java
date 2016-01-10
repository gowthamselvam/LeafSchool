package com.leafsoft.school.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ExamDetails database table.
 * 
 */
@NamedQuery(name="ExamDetail.findAll", query="SELECT e FROM ExamDetail e")
public class ExamDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int examid;

	private String examname;

	//bi-directional many-to-one association to ExamType
	@ManyToOne
	@JoinColumn(name="examtypeid")
	private ExamType examType;

	//bi-directional many-to-one association to ExamSubjectMapping
	@OneToMany(mappedBy="examDetail")
	private List<ExamSubjectMapping> examSubjectMappings;

	public ExamDetail() {
	}

	public int getExamid() {
		return this.examid;
	}

	public void setExamid(int examid) {
		this.examid = examid;
	}

	public String getExamname() {
		return this.examname;
	}

	public void setExamname(String examname) {
		this.examname = examname;
	}

	public ExamType getExamType() {
		return this.examType;
	}

	public void setExamType(ExamType examType) {
		this.examType = examType;
	}

	public List<ExamSubjectMapping> getExamSubjectMappings() {
		return this.examSubjectMappings;
	}

	public void setExamSubjectMappings(List<ExamSubjectMapping> examSubjectMappings) {
		this.examSubjectMappings = examSubjectMappings;
	}

	public ExamSubjectMapping addExamSubjectMapping(ExamSubjectMapping examSubjectMapping) {
		getExamSubjectMappings().add(examSubjectMapping);
		examSubjectMapping.setExamDetail(this);

		return examSubjectMapping;
	}

	public ExamSubjectMapping removeExamSubjectMapping(ExamSubjectMapping examSubjectMapping) {
		getExamSubjectMappings().remove(examSubjectMapping);
		examSubjectMapping.setExamDetail(null);

		return examSubjectMapping;
	}

}