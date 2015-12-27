package com.leafsoft.school.dao;

import javax.sql.DataSource;

import com.leafsoft.school.model.OrgDetail;
import com.leafsoft.school.model.OrgUser;

public interface OrgUserRolesDao {

	public void setDataSource(DataSource dataSource);
	
	public int insert(OrgUser orgUser,String role);
}
