package com.app.security;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

import com.leafsoft.org.OrgUtil;
import com.leafsoft.util.AppResources;

public class AuthenticationEntryPointDenied implements AuthenticationEntryPoint {
	private static final Logger LOGGER = Logger.getLogger(AuthenticationEntryPointDenied.class.getName());
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		LOGGER.log(Level.INFO,"OrgUtil.getUserlid():::"+OrgUtil.getUserlid());
		LOGGER.log(Level.INFO,"OrgUtil.getOrgId():::"+OrgUtil.getOrgId());
		LOGGER.log(Level.INFO,"OrgUtil.isValidOrg():::"+OrgUtil.isValidOrg());
		// Redirecting service to access denied page for invalid users
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		if(OrgUtil.getUserlid() == null) {
			redirectStrategy.sendRedirect(request, response, AppResources.getInstance().getAccountsUrl());
		} else if(!OrgUtil.isValidOrg()) {
			redirectStrategy.sendRedirect(request, response, "/accessdenied");
		}
		
	}

}
