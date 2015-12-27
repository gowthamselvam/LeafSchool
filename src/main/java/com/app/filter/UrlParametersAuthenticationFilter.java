package com.app.filter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import com.leafsoft.http.HttpUtil;
import com.leafsoft.org.OrgUtil;
import com.leafsoft.school.util.CommonUtil;
import com.leafsoft.user.LeafUser;

public class UrlParametersAuthenticationFilter  extends AbstractPreAuthenticatedProcessingFilter {

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		// Check for cookies
		return OrgUtil.setCurrentUser(request);
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		
		LeafUser leafuser = null;
	    
		//Getting authentication credentials
		if(OrgUtil.getUser()!=null) {
			leafuser = OrgUtil.getUser();
		}
	    
	    return leafuser;
	}
}
