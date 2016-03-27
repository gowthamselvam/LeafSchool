package com.app.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import com.leafsoft.org.OrgUtil;
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
