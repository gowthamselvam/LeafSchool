package com.app.controller;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.app.model.Employee;
import com.app.model.Employees;
import com.leafsoft.http.HttpUtil;
import com.leafsoft.org.OrgUtil;
import com.leafsoft.util.AppResources;
import com.leafsoft.util.JSONUtil;

@Controller
public class SchoolController 
{
	private static Logger LOGGER = Logger.getLogger(SchoolController.class.getName());
	@RequestMapping(value = {"/", "/welcome"})
	public ModelAndView welcome(HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "LeafSoft");
		model.addObject("message", "This is welcome page!");
		if(OrgUtil.getOwner() == null) {
			OrgUtil.setCurrentUser(request);
		}
		model.addObject("user",OrgUtil.getOwner());
		model.addObject("org",OrgUtil.getOrgDetails());
		model.setViewName("home");
		return model;

	}
	
	@RequestMapping(value = {"/invaliduser"})
	public ModelAndView invalidUser() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "SchoolManagement");
		model.addObject("message", "Ooops!!!, It's seems that you did't made login on leafsoft, Please login/signup on the below link and then access school application! Thank you");
		model.addObject("leafsofturl",AppResources.getInstance().getAccountsUrl());
		model.setViewName("invaliduser");
		return model;
	}
	
	@RequestMapping(value = {"/logoutsession"})
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		OrgUtil.cleanup();
		HttpSession session = request.getSession();
		Cookie[] cookie_jar = request.getCookies();
		System.out.print("sessionidprincipal"+request.getUserPrincipal());
		System.out.print("sessionidname"+session.getAttribute("user"));
	// Check to see if any cookies exists
		JSONObject sessionUser = null;
		if (cookie_jar != null)
		{
			for (int i =0; i< cookie_jar.length; i++)
			{
				Cookie aCookie = cookie_jar[i];
				System.out.println ("Name : " + aCookie.getName());
				System.out.println ("Value: " + aCookie.getValue());
				if(!aCookie.getValue().equals(request.getRequestedSessionId()) || Boolean.valueOf(AppResources.getInstance().isDevelopmentMode()))
					try {
						System.out.print("Boolean.valueOf(AppResources.getInstance().isDevelopmentMode())"+Boolean.valueOf(AppResources.getInstance().isDevelopmentMode()));
						if(!Boolean.valueOf(AppResources.getInstance().isDevelopmentMode())) {
							String httpResponse = HttpUtil.makeApiCall(AppResources.getInstance().getAccountsUrl()+"/logoutUsers", aCookie.getValue(),"GET");
							System.out.print("response:::::::::"+httpResponse);
						} 
						} catch(Exception e) {
							e.printStackTrace();
						}
					}
		}
		SecurityContextHolder.clearContext();
		try {
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		redirectStrategy.sendRedirect(request, response, AppResources.getInstance().getAccountsUrl());
		} catch(Exception e) {
			LOGGER.log(Level.INFO,e.getMessage(),e);
		}
	}
	
	
	
	@RequestMapping(produces="application/json",value = "/employees", method = RequestMethod.GET)
	public @ResponseBody String getEmployees() 
	{
		
		String userRole = getUserRole();
		
		System.out.println("User Role : "+userRole);
        
		Employee emp1 = new Employee();
		Employee emp2 = new Employee();
		
		if(userRole.equals("admin")){
		
			emp1.setFirstName("Bala");
			emp1.setLastName("Raj");
			emp1.setRole("ADMIN");
			emp2.setFirstName("Tim");
			emp2.setLastName("Rock");
			emp2.setRole("ADMIN");
		
		}else if(userRole.equals("dba")){
			
			emp1.setFirstName("Steve");
			emp1.setLastName("Jose");
			emp1.setRole("DBA");
			emp2.setFirstName("Bill");
			emp2.setLastName("Fish");
			emp2.setRole("DBA");
			
		}else if(userRole.equals("user")){
			
			emp1.setFirstName("Joy");
			emp1.setLastName("Tiger");
			emp1.setRole("USER");
			emp2.setFirstName("Wood");
			emp2.setLastName("Rich");
			emp2.setRole("USER");
			
		} 
		
		Employees emps = new Employees();
		emps.setUsers(new ArrayList<Employee>());
		emps.getUsers().add(emp1);
		emps.getUsers().add(emp2);
		
		return new JSONObject().put("he", 1).toString();
	}
	
	@RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
	public ModelAndView accessdenied() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "LeafSoft");
		model.addObject("message", "This is welcome page!");
		model.setViewName("access-denied");
		return model;

	}
	
	private String getUserRole(){
		
		String userRole = "";
		
		SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        
        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if ("ROLE_ADMIN".equals(auth.getAuthority())){
                userRole = "admin";
                break;
            }
            if ("ROLE_DBA".equals(auth.getAuthority())){
                userRole = "dba";
                break;
            }
            if ("ROLE_USER".equals(auth.getAuthority())){
                userRole = "user";
                break;
            }
            if ("ROLE_COMMONUSER".equals(auth.getAuthority())){
                userRole = "commonuser";
                break;
            }
        }
        
        return userRole;
	}
}
