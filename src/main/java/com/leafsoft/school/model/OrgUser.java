package com.leafsoft.school.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the OrgUsers database table.
 * 
 */
@Entity
@Table(name="OrgUsers")
@NamedQuery(name="OrgUser.findAll", query="SELECT o FROM OrgUser o")
public class OrgUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int luid;

	private BigInteger createtime;

	private int defaultorgid;

	private String email;

	private int lid;

	private String username;

	//bi-directional many-to-one association to OrgUserRole
	@OneToMany(mappedBy="orgUser")
	private List<OrgUserRole> orgUserRoles;

	public OrgUser() {
	}

	public int getLuid() {
		return this.luid;
	}

	public void setLuid(int luid) {
		this.luid = luid;
	}

	public BigInteger getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(BigInteger createtime) {
		this.createtime = createtime;
	}

	public int getDefaultorgid() {
		return this.defaultorgid;
	}

	public void setDefaultorgid(int defaultorgid) {
		this.defaultorgid = defaultorgid;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getLid() {
		return this.lid;
	}

	public void setLid(int lid) {
		this.lid = lid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<OrgUserRole> getOrgUserRoles() {
		return this.orgUserRoles;
	}

	public void setOrgUserRoles(List<OrgUserRole> orgUserRoles) {
		this.orgUserRoles = orgUserRoles;
	}

	public OrgUserRole addOrgUserRole(OrgUserRole orgUserRole) {
		getOrgUserRoles().add(orgUserRole);
		orgUserRole.setOrgUser(this);

		return orgUserRole;
	}

	public OrgUserRole removeOrgUserRole(OrgUserRole orgUserRole) {
		getOrgUserRoles().remove(orgUserRole);
		orgUserRole.setOrgUser(null);

		return orgUserRole;
	}

}