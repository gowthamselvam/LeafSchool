package com.leafsoft.school.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the OrgDetails database table.
 * 
 */
@Entity
@Table(name="OrgDetails")
@NamedQuery(name="OrgDetail.findAll", query="SELECT o FROM OrgDetail o")
public class OrgDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int orgid;

	private String address;

	private String city;

	private String country;

	private BigInteger createdtime;

	private String currencycode;

	private String dateformat;

	private String orgname;

	private String state;

	private byte status;

	private String timetype;

	private String zipcode;

	//bi-directional many-to-one association to OrgUserRole
	@OneToMany(mappedBy="orgDetail")
	private List<OrgUserRole> orgUserRoles;

	public OrgDetail() {
	}

	public int getOrgid() {
		return this.orgid;
	}

	public void setOrgid(int orgid) {
		this.orgid = orgid;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public BigInteger getCreatedtime() {
		return this.createdtime;
	}

	public void setCreatedtime(BigInteger createdtime) {
		this.createdtime = createdtime;
	}

	public String getCurrencycode() {
		return this.currencycode;
	}

	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}

	public String getDateformat() {
		return this.dateformat;
	}

	public void setDateformat(String dateformat) {
		this.dateformat = dateformat;
	}

	public String getOrgname() {
		return this.orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getTimetype() {
		return this.timetype;
	}

	public void setTimetype(String timetype) {
		this.timetype = timetype;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public List<OrgUserRole> getOrgUserRoles() {
		return this.orgUserRoles;
	}

	public void setOrgUserRoles(List<OrgUserRole> orgUserRoles) {
		this.orgUserRoles = orgUserRoles;
	}

	public OrgUserRole addOrgUserRole(OrgUserRole orgUserRole) {
		getOrgUserRoles().add(orgUserRole);
		orgUserRole.setOrgDetail(this);

		return orgUserRole;
	}

	public OrgUserRole removeOrgUserRole(OrgUserRole orgUserRole) {
		getOrgUserRoles().remove(orgUserRole);
		orgUserRole.setOrgDetail(null);

		return orgUserRole;
	}

}