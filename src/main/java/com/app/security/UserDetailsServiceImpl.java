package com.app.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements AuthenticationUserDetailsService {

	public UserDetails loadUserDetails(Authentication token)
			throws UsernameNotFoundException {
		UserDetails userDetails = null;

		String[] credentials = (String[]) token.getCredentials();
		boolean principal = Boolean.valueOf(token.getPrincipal().toString());

		if (credentials != null && principal == true) {
			String name = credentials[0];

			// Setting user Authorities
			if ("admin".equalsIgnoreCase(name)) {
				userDetails = getAdminUser(name);
			} else if ("dba".equalsIgnoreCase(name)) {
				userDetails = getDBAUser(name);
			} else if ("user".equalsIgnoreCase(name)) {
				userDetails = getUserUser(name);
			}
		}

		if (userDetails == null) {
			throw new UsernameNotFoundException("Invalid user - "
					+ credentials[0]);
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
