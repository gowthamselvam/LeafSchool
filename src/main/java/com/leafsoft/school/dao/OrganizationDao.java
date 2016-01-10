package com.leafsoft.school.dao;

import com.leafsoft.school.model.OrgDetail;

public interface OrganizationDao {
	
	public int insert(OrgDetail org);
	
	public OrgDetail loadOrgDetailByOrgId(long orgId);
	
	public boolean hasOrg(String orgName);
	
}
