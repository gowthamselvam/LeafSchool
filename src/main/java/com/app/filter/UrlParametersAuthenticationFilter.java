package com.app.filter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

public class UrlParametersAuthenticationFilter  extends AbstractPreAuthenticatedProcessingFilter {

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		// Check for cookies
		Cookie[] cookie_jar = request.getCookies();
		System.out.print(request.getUserPrincipal());
		HttpSession session = request.getSession();
		System.out.print(session.getId());
		// Check to see if any cookies exists
		if (cookie_jar != null)
		{
			for (int i =0; i< cookie_jar.length; i++)
			{
				Cookie aCookie = cookie_jar[i];
				System.out.println ("Name : " + aCookie.getName());
				System.out.println ("Value: " + aCookie.getValue());
				System.out.println("a"+aCookie.getDomain());
				System.out.println("a"+aCookie.getPath());
			}
		}
		//Checking for no. of URL params
		if (request.getParameterMap().size() == 1) {
	        return true;
	    }
	    return false;
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		
		String[] credentials = new String[1];
	    
		//Getting authentication credentials
		credentials[0] = request.getParameter("param1");
	    
	    return credentials;
	}

}
