package com.leafsoft.school.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the DesignationGroup database table.
 * 
 */
@NamedQuery(name="DesignationGroup.findAll", query="SELECT d FROM DesignationGroup d")
public class DesignationGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int designationgroupid;

	private String designationgroupname;

	//bi-directional many-to-one association to Designation
	@OneToMany(mappedBy="designationGroup")
	private List<Designation> designations;

	public DesignationGroup() {
	}

	public int getDesignationgroupid() {
		return this.designationgroupid;
	}

	public void setDesignationgroupid(int designationgroupid) {
		this.designationgroupid = designationgroupid;
	}

	public String getDesignationgroupname() {
		return this.designationgroupname;
	}

	public void setDesignationgroupname(String designationgroupname) {
		this.designationgroupname = designationgroupname;
	}

	public List<Designation> getDesignations() {
		return this.designations;
	}

	public void setDesignations(List<Designation> designations) {
		this.designations = designations;
	}

	public Designation addDesignation(Designation designation) {
		getDesignations().add(designation);
		designation.setDesignationGroup(this);

		return designation;
	}

	public Designation removeDesignation(Designation designation) {
		getDesignations().remove(designation);
		designation.setDesignationGroup(null);

		return designation;
	}

}