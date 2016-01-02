package com.leafsoft.school.dao;

import javax.sql.DataSource;

import com.leafsoft.school.model.OrgDetail;

public interface OrganizationDao {
	
	public void setDataSource(DataSource dataSource);
	
	public int insert(OrgDetail org);
	
	public OrgDetail loadOrgDetailByOrgId(long orgId);
	
	public boolean hasOrg(String orgName);
	
}
