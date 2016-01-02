package com.app.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.app.model.Employee;
import com.app.model.Employees;
import com.leafsoft.org.OrgUtil;

@Controller
public class SchoolController 
{
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
		model.setViewName("helloWorld");
		return model;

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
        }
        
        return userRole;
	}
}
