package com.app.security;

import java.io.IOException;

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

	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		
		// Redirecting service to access denied page for invalid users
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		if(OrgUtil.getOwner() == null) {
			redirectStrategy.sendRedirect(request, response, AppResources.getInstance().getAccountsUrl());
		} else {
			redirectStrategy.sendRedirect(request, response, "/accessdenied");
		}
		
	}

}
