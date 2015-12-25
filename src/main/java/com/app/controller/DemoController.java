package com.app.controller;

import java.util.ArrayList;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.model.Employee;
import com.app.model.Employees;

@Controller
@RequestMapping("/employees")
public class DemoController 
{
	@RequestMapping(method = RequestMethod.GET,  headers="Accept=application/xml")
	public @ResponseBody Employees getEmployees() 
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
		
		return emps;
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
