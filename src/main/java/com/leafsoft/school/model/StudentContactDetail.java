package com.leafsoft.school.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the StudentContactDetails database table.
 * 
 */
@NamedQuery(name="StudentContactDetail.findAll", query="SELECT s FROM StudentContactDetail s")
public class StudentContactDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int familyid;

	private String address;

	private String caste;

	private String city;

	private BigInteger contactnumber;

	private String countrycode;

	private String fathername;

	private String guardianname;

	private byte guardiantype;

	private String mothername;

	private String state;

	private String zipcode;

	//bi-directional many-to-one association to StudentDetail
	@OneToMany(mappedBy="studentContactDetail")
	private List<StudentDetail> studentDetails;

	public StudentContactDetail() {
	}

	public int getFamilyid() {
		return this.familyid;
	}

	public void setFamilyid(int familyid) {
		this.familyid = familyid;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCaste() {
		return this.caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public BigInteger getContactnumber() {
		return this.contactnumber;
	}

	public void setContactnumber(BigInteger contactnumber) {
		this.contactnumber = contactnumber;
	}

	public String getCountrycode() {
		return this.countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public String getFathername() {
		return this.fathername;
	}

	public void setFathername(String fathername) {
		this.fathername = fathername;
	}

	public String getGuardianname() {
		return this.guardianname;
	}

	public void setGuardianname(String guardianname) {
		this.guardianname = guardianname;
	}

	public byte getGuardiantype() {
		return this.guardiantype;
	}

	public void setGuardiantype(byte guardiantype) {
		this.guardiantype = guardiantype;
	}

	public String getMothername() {
		return this.mothername;
	}

	public void setMothername(String mothername) {
		this.mothername = mothername;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public List<StudentDetail> getStudentDetails() {
		return this.studentDetails;
	}

	public void setStudentDetails(List<StudentDetail> studentDetails) {
		this.studentDetails = studentDetails;
	}

	public StudentDetail addStudentDetail(StudentDetail studentDetail) {
		getStudentDetails().add(studentDetail);
		studentDetail.setStudentContactDetail(this);

		return studentDetail;
	}

	public StudentDetail removeStudentDetail(StudentDetail studentDetail) {
		getStudentDetails().remove(studentDetail);
		studentDetail.setStudentContactDetail(null);

		return studentDetail;
	}

}