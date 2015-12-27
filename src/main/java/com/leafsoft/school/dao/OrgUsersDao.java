package com.leafsoft.school.dao;

import javax.sql.DataSource;

import com.leafsoft.school.model.OrgUser;
import com.leafsoft.user.LeafUser;


public interface OrgUsersDao {

	public void setDataSource(DataSource dataSource);
	
	public int insert(OrgUser user);
	
	public OrgUser loadOrgUserByLid(int lid);
	
	public OrgUser loadUserByUsername(String userName);
	
	public boolean hasUser(int lid) ;
	
}
