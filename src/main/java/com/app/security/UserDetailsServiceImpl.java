package com.app.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.leafsoft.school.dao.OrgUsersDao;
import com.leafsoft.school.dao.impl.OrgUsersDaoImpl;
import com.leafsoft.school.model.OrgUser;
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
			DriverManagerDataSource datasource = new JdbcUtil().getAccountsDataSource();
	    	OrgUsersDao orgUserDAO = new OrgUsersDaoImpl();
	    	orgUserDAO.setDataSource(datasource);
	    	
	    	OrgUser orguser = orgUserDAO.loadOrgUserByLid(lid);
			// Setting user Authorities
	    	if(orguser == null)
	    	{
	    		orguser = new OrgUser();
	    		orguser.setUsername(name);
	    	}
	    	
	    	if(orguser != null) {
				if ("admin".equalsIgnoreCase(name)) {
					userDetails = getAdminUser(name);
				} else if ("dba".equalsIgnoreCase(name)) {
					userDetails = getDBAUser(name);
				} else if ("user".equalsIgnoreCase(name)) {
					userDetails = getUserUser(name);
				}
	    	}
		}

		if (userDetails == null) {
			throw new UsernameNotFoundException("Invalid user - "
					+ credentials.getLid());
		}

		return userDetails;
	}

	private UserDetails getAdminUser(String username) {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new GrantedAuthorityImpl("ROLE_USER"));
		grantedAuthorities.add(new GrantedAuthorityImpl("ROLE_DBA"));
		grantedAuthorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
		return new User(username, "notused", true, true, true, true,
				grantedAuthorities);
	}

	private UserDetails getDBAUser(String username) {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new GrantedAuthorityImpl("ROLE_USER"));
		grantedAuthorities.add(new GrantedAuthorityImpl("ROLE_DBA"));
		return new User(username, "notused", true, true, true, true,
				grantedAuthorities);
	}

	private UserDetails getUserUser(String username) {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new GrantedAuthorityImpl("ROLE_USER"));
		return new User(username, "notused", true, true, true, true,
				grantedAuthorities);
	}
}
