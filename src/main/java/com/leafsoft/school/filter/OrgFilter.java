package com.leafsoft.school.filter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.leafsoft.org.OrgUtil;

public class OrgFilter implements Filter {

	private static final Logger LOGGER = Logger.getLogger(OrgFilter.class.getName());
	
	public void destroy() {
		// TODO Auto-generated method stub

	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc)
			throws IOException, ServletException {
		try {
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) res;
			String path = request.getRequestURI();
			//String serviceName = path.substring(path.lastIndexOf("/")+1, path.indexOf("."));
				boolean success = OrgUtil.setCurrentUser(request,response);
				LOGGER.log(Level.INFO,"user Authenitcation status:::::"+success);
			LOGGER.log(Level.INFO,"Filter:::::ip:"+request.getRemoteAddr());
			fc.doFilter(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
	}

	public void init(FilterConfig fc) throws ServletException {
		// TODO Auto-generated method stub

	}
	
	public JSONArray getUserRoleForResource()
	{
//		String zuid = UserUtil.getCurrentUser() != null ? String.valueOf(UserUtil.getCurrentUser().getLid()) : null;
//		logger.log(Level.INFO,"STORE ACCESSS : ZUID {0}",zuid);
//		if(zuid == null)
//		{
//			return null;
//		}
		JSONArray rolename = new JSONArray();
		//logger.log(Level.INFO,"role name returned is: {0}",rolename);
		return rolename;
	}

}
