package com.app.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.leafsoft.org.OrgUtil;
import com.leafsoft.school.dao.OrgUserRolesDao;
import com.leafsoft.school.dao.OrgUsersDao;
import com.leafsoft.school.dao.impl.OrgUserRolesDaoImpl;
import com.leafsoft.school.dao.impl.OrgUsersDaoImpl;
import com.leafsoft.school.model.OrgUser;
import com.leafsoft.school.model.OrgUserRole;
import com.leafsoft.user.LeafUser;
import com.leafsoft.util.JdbcUtil;

public class UserDetailsServiceImpl implements AuthenticationUserDetailsService {

	public UserDetails loadUserDetails(Authentication token)
			throws UsernameNotFoundException {
		UserDetails userDetails = null;

		LeafUser credentials = (LeafUser) token.getCredentials();
		boolean principal = Boolean.valueOf(token.getPrincipal().toString());

		if (credentials != null && principal == true) {
			int lid = credentials.getLid();
			String name = credentials.getUsername();
			DriverManagerDataSource datasource = new JdbcUtil().getOrgDBDataSource();
	    	OrgUsersDao orgUserDAO = new OrgUsersDaoImpl();
	    	orgUserDAO.setDataSource(datasource);
	    	
	    	OrgUser orguser = orgUserDAO.loadOrgUserByLid(lid);
	    	OrgUserRolesDao orgroleDAO = new OrgUserRolesDaoImpl();
	    	orgroleDAO.setDataSource(datasource);
	    	if(OrgUtil.getOrgId() !=null && orguser.getLuid() == OrgUtil.getOwnerid()) {
	    		OrgUserRole orgUserRole = orgroleDAO.loadOrgUserByLuid(orguser.getLuid(), OrgUtil.getOrgId());
		    	if(orguser != null) {
					if (orgUserRole.getRolename().equals("ROLE_ADMIN")) {
						userDetails = getAdminUser(name);
					} else if (orgUserRole.getRolename().equals("ROLE_DBA")) {
						userDetails = getDBAUser(name);
					} else if (orgUserRole.getRolename().equals("ROLE_USER")) {
						userDetails = getUserUser(name);
					}
		    	}
	    	} else {
	    		userDetails = getCommonUser(name);
	    	}
		}

		if (userDetails == null) {
			throw new UsernameNotFoundException("Invalid user - "
					+ credentials.getLid());
		}

		return userDetails;
	}

	private UserDetails getAdminUser(String username) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_DBA"));
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_COMMONUSER"));
		return new User(username, "notused", true, true, true, true,
				grantedAuthorities);
	}

	private UserDetails getDBAUser(String username) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_DBA"));
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_COMMONUSER"));
		return new User(username, "notused", true, true, true, true,
				grantedAuthorities);
	}

	private UserDetails getUserUser(String username) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_COMMONUSER"));
		return new User(username, "notused", true, true, true, true,
				grantedAuthorities);
	}
	
	private UserDetails getCommonUser(String username) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_COMMONUSER"));
		return new User(username, "notused", true, true, true, true,
				grantedAuthorities);
	}
}
