package com.leafsoft.school.dao;



import org.json.JSONArray;

import com.leafsoft.school.model.OrgUser;
import com.leafsoft.school.model.OrgUserRole;

public interface OrgUserRolesDao {

	public int insert(OrgUser orgUser,String role);
	
	public OrgUserRole loadOrgUserByLuid(int luid,int orgId);
	
	public int getTotalNumberOfOrgForUser(int luid);
	
	public OrgUserRole getSingleOrg(int luid);
	
	public JSONArray findAllUserOrg(int luid);
	
	public JSONArray findAll();
}
