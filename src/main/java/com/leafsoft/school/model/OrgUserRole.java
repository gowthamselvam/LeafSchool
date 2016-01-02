package com.leafsoft.school.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the OrgUserRoles database table.
 * 
 */
@Entity
@Table(name="OrgUserRoles")
@NamedQuery(name="OrgUserRole.findAll", query="SELECT o FROM OrgUserRole o")
public class OrgUserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_role_id")
	private int userRoleId;

	private String rolename;

	//bi-directional many-to-one association to OrgDetail
	@ManyToOne
	@JoinColumn(name="orgid")
	private OrgDetail orgDetail;

	//bi-directional many-to-one association to OrgUser
	@ManyToOne
	@JoinColumn(name="luid")
	private OrgUser orgUser;

	public OrgUserRole() {
	}

	public int getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public OrgDetail getOrgDetail() {
		return this.orgDetail;
	}

	public void setOrgDetail(OrgDetail orgDetail) {
		this.orgDetail = orgDetail;
	}

	public OrgUser getOrgUser() {
		return this.orgUser;
	}

	public void setOrgUser(OrgUser orgUser) {
		this.orgUser = orgUser;
	}

}