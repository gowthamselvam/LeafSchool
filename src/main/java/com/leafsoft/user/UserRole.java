package com.leafsoft.user;

import java.io.Serializable;


public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private int userRoleId;

	private String rolename;

	//bi-directional many-to-one association to LeafUser
	private LeafUser leafUser;

	public UserRole() {
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

	public LeafUser getLeafUser() {
		return this.leafUser;
	}

	public void setLeafUser(LeafUser leafUser) {
		this.leafUser = leafUser;
	}

}