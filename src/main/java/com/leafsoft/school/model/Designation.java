package com.leafsoft.school.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Designations database table.
 * 
 */
@NamedQuery(name="Designation.findAll", query="SELECT d FROM Designation d")
public class Designation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int designationid;

	private String designationname;

	//bi-directional many-to-one association to DesignationGroup
	@ManyToOne
	@JoinColumn(name="designationgroupid")
	private DesignationGroup designationGroup;

	//bi-directional many-to-one association to StaffDetail
	@OneToMany(mappedBy="designation")
	private List<StaffDetail> staffDetails;

	public Designation() {
	}

	public int getDesignationid() {
		return this.designationid;
	}

	public void setDesignationid(int designationid) {
		this.designationid = designationid;
	}

	public String getDesignationname() {
		return this.designationname;
	}

	public void setDesignationname(String designationname) {
		this.designationname = designationname;
	}

	public DesignationGroup getDesignationGroup() {
		return this.designationGroup;
	}

	public void setDesignationGroup(DesignationGroup designationGroup) {
		this.designationGroup = designationGroup;
	}

	public List<StaffDetail> getStaffDetails() {
		return this.staffDetails;
	}

	public void setStaffDetails(List<StaffDetail> staffDetails) {
		this.staffDetails = staffDetails;
	}

	public StaffDetail addStaffDetail(StaffDetail staffDetail) {
		getStaffDetails().add(staffDetail);
		staffDetail.setDesignation(this);

		return staffDetail;
	}

	public StaffDetail removeStaffDetail(StaffDetail staffDetail) {
		getStaffDetails().remove(staffDetail);
		staffDetail.setDesignation(null);

		return staffDetail;
	}

}