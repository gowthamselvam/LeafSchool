package com.leafsoft.school.dao;

import java.util.List;

import javax.sql.DataSource;

import com.leafsoft.school.model.OrgDetail;
import com.leafsoft.school.model.OrgUser;
import com.leafsoft.school.model.OrgUserRole;

public interface OrgUserRolesDao {

	public void setDataSource(DataSource dataSource);
	
	public int insert(OrgUser orgUser,String role);
	
	public OrgUserRole loadOrgUserByLuid(int luid,int orgId);
	
	public int getTotalNumberOfOrgForUser(int luid);
	
	public OrgUserRole getSingleOrg(int luid);
	
	public List<OrgUserRole> findAllOrg(int luid);
}
