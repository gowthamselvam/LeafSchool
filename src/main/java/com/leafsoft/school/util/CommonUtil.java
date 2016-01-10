package com.leafsoft.school.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;

import com.leafsoft.user.LeafUser;
import com.leafsoft.util.DateAndTime;

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
	  
	  public static String getOrgDb(int orgid) {
		  return "db"+orgid;
	  }
	  
	  public static void logout(HttpServletRequest request) {
		  SecurityContextHolder.getContext().setAuthentication(null);
		  SecurityContextHolder.clearContext();
		  HttpSession hs = request.getSession();
		  Enumeration<String> e = hs.getAttributeNames();
		  while (e.hasMoreElements()) {
			  String attr = e.nextElement();
			  hs.setAttribute(attr, null);
		  }
		  		removeCookies(request);
		  		hs.invalidate();
		  }
	  public static void removeCookies(HttpServletRequest request) {
		  Cookie[] cookies = request.getCookies();
		  if (cookies != null && cookies.length > 0) {
			  for (int i = 0; i < cookies.length; i++) {
				  cookies[i].setMaxAge(0);
			  }
		  }
	  }
}
