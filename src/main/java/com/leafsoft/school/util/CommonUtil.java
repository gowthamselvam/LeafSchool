package com.leafsoft.school.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import com.leafsoft.user.LeafUser;
import com.leafsoft.util.DateAndTime;
import com.leafsoft.util.DateUtil;

public class CommonUtil {

	public static LeafUser getLeafUserFromSessionJson(JSONObject sessionUser) {
		LeafUser leafuser = new LeafUser();
		if(sessionUser.has("username")) {
			
		}
		if(sessionUser.has("lid")) {
			leafuser.setLid(sessionUser.getInt("lid"));
		}
		if(sessionUser.has("email")) {
			leafuser.setEmail(sessionUser.getString("email"));
		}
		if(sessionUser.has("dob")) {
			leafuser.setDob(DateAndTime.getDateFromString(sessionUser.getString("dob"), DateAndTime.ORIGDATEFORMAT));
		}
		if(sessionUser.has("enabled")) {
			leafuser.setEnabled(sessionUser.getInt("enabled"));
		}
		if(sessionUser.has("username")) {
			leafuser.setUsername(sessionUser.getString("username"));
		}
		return leafuser;
	}
	
	  public void setCustomerAuthorities(List<String> roles) {
	        List<GrantedAuthority> listOfAuthorities = new ArrayList<GrantedAuthority>();
	        for (String role : roles) {
	            listOfAuthorities.add(new GrantedAuthorityImpl(role));
	        }
	        GrantedAuthority authorities = (GrantedAuthority)listOfAuthorities;
	    }
}
