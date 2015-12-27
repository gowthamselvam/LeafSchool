package com.leafsoft.org;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.leafsoft.school.dao.OrgUsersDao;
import com.leafsoft.school.dao.impl.OrgUsersDaoImpl;
import com.leafsoft.school.model.OrgDetail;
import com.leafsoft.school.model.OrgUser;
import com.leafsoft.school.util.CommonUtil;
import com.leafsoft.user.LeafUser;
import com.leafsoft.util.JdbcUtil;


public class OrgUtil {
	
	private static Logger LOGGER = Logger.getLogger(OrgUtil.class.getName());
	
	private static ThreadLocal<OrgUser> OWNER = new ThreadLocal<OrgUser>();
	private static ThreadLocal<List> USER_ROLE = new ThreadLocal<List>();
	private static ThreadLocal<Integer> OWNERID = new ThreadLocal<Integer>();
	private static ThreadLocal<Integer> USERLID = new ThreadLocal<Integer>();
	private static ThreadLocal<LeafUser> USER = new ThreadLocal<LeafUser>();
	private static ThreadLocal<JSONArray> ORGADMINS = new ThreadLocal<JSONArray>();
	private static ThreadLocal<String> REMOTEUSERIPADDRESS = new ThreadLocal<String>();
	private static ThreadLocal<String> ORGDB = new ThreadLocal<String>();
	private static ThreadLocal<OrgUser> ORGUSER = new ThreadLocal<OrgUser>();
	private static ThreadLocal<GrantedAuthority> AUTHORITIES = new ThreadLocal<GrantedAuthority>();
	private static ThreadLocal<OrgDetail> ORGDETAILS = new ThreadLocal<OrgDetail>();
	/**
	 * @return the owner
	 */
	public static OrgUser getOwner() {
		return OWNER.get();
	}
	/**
	 * @return the userRole
	 */
	public static List getUserRole() {
		return USER_ROLE.get();
	}
	/**
	 * @return the ownerid
	 */
	public static Integer getOwnerid() {
		return OWNERID.get();
	}
	/**
	 * @return the userlid
	 */
	public static Integer getUserlid() {
		return USERLID.get();
	}
	/**
	 * @return the user
	 */
	public static LeafUser getUser() {
		return USER.get();
	}
	/**
	 * @return the orgadmins
	 */
	public static JSONArray getOrgadmins() {
		return ORGADMINS.get();
	}
	/**
	 * @return the remoteuseripaddress
	 */
	public static String getRemoteuseripaddress() {
		return REMOTEUSERIPADDRESS.get();
	}
	/**
	 * @return the orgdb
	 */
	public static String getOrgdb() {
		return ORGDB.get();
	}
	/**
	 * @param owner the owner to set
	 */
	public static void setOwner(OrgUser owner) {
		OWNER.set(owner);
	}
	/**
	 * @param userRole the userRole to set
	 */
	public static void setUserRole(List userRole) {
		USER_ROLE.set(userRole);
	}
	/**
	 * @param ownerid the ownerid to set
	 */
	public static void setOwnerid(Integer ownerid) {
		OWNERID.set(ownerid);
	}
	/**
	 * @param userlid the userlid to set
	 */
	public static void setUserlid(Integer userlid) {
		USERLID.set(userlid);
	}
	/**
	 * @param user the user to set
	 */
	public static void setUser(LeafUser user) {
		USER.set(user);
	}
	/**
	 * @param orgadmins the orgadmins to set
	 */
	public static void setOrgadmins(ThreadLocal<JSONArray> orgadmins) {
		ORGADMINS = orgadmins;
	}
	/**
	 * @param remoteuseripaddress the remoteuseripaddress to set
	 */
	public static void setRemoteuseripaddress(String remoteuseripaddress) {
		REMOTEUSERIPADDRESS.set(remoteuseripaddress);
	}
	/**
	 * @param orgdb the orgdb to set
	 */
	public static void setOrgdb(String orgdb) {
		ORGDB.set(orgdb);
	}
	
	public static void init(LeafUser currentUser,String orgId,String remoteIp) {
		try {
			OrgUtil.setRemoteuseripaddress(remoteIp);
			if (currentUser == null) {
				return;
			}
			Integer lid =currentUser.getLid();
			
			if(lid != null) {
				setUser(currentUser);
				setUserlid(lid);
				if(orgId!=null) {
					setOrgdb("db"+orgId);
				}
			}
			OrgUtil.setOwnerid(OrgUtil.getOwnerid() != null ? OrgUtil.getOwnerid() :null);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}
	
	public static void cleanup() {
		OrgUtil.setOwner(null);
		OrgUtil.setUser(null);
		OrgUtil.setUserRole(null);
		OrgUtil.setOrgdb(null);
		OrgUtil.setRemoteuseripaddress(null);
		OrgUtil.setUserlid(null);
		OrgUtil.setOrgadmins(null);
		OrgUtil.setOwnerid(null);
		OrgUtil.setOrgUser(null);
	}
	/**
	 * @return the oRG_USER
	 */
	public static OrgUser getOrgUser() {
		return ORGUSER.get();
	}
	/**
	 * @param oRG_USER the oRG_USER to set
	 */
	public static void setOrgUser(OrgUser oRGUSER) {
		ORGUSER.set(oRGUSER);
	}
	/**
	 * @return the authorities
	 */
	public static GrantedAuthority getAuthorities() {
		return AUTHORITIES.get();
	}
	/**
	 * @param authorities the authorities to set
	 */
	public static void setAuthorities(List<String> roles) {
        List<GrantedAuthority> listOfAuthorities = new ArrayList<GrantedAuthority>();
        for (String role : roles) {
            listOfAuthorities.add(new GrantedAuthorityImpl(role));
        }
        AUTHORITIES.set((GrantedAuthority)listOfAuthorities);
    }
	/**
	 * @return the oRGDETAILS
	 */
	public static OrgDetail getOrgDetails() {
		return ORGDETAILS.get();
	}
	/**
	 * @param oRGDETAILS the oRGDETAILS to set
	 */
	public static void setOrgDetails(OrgDetail oRGDETAILS) {
		ORGDETAILS.set(oRGDETAILS);
	}
	
	public static boolean setCurrentUser(HttpServletRequest request) {
		try {
			OrgUser orgUser = getCurrentUser();
			if(orgUser == null) {
				Cookie[] cookie_jar = request.getCookies();
				System.out.print("sessionid"+request.getUserPrincipal());
				HttpSession session = request.getSession();
				System.out.print("sessionid"+session.getId());
			// Check to see if any cookies exists
				if (cookie_jar != null)
				{
					for (int i =0; i< cookie_jar.length; i++)
					{
						Cookie aCookie = cookie_jar[i];
						System.out.println ("Name : " + aCookie.getName());
						System.out.println ("Value: " + aCookie.getValue());
						if(!aCookie.getValue().equals(request.getRequestedSessionId()))
							try {
								//String response = HttpUtil.sendGet("http://http://localhost:6060/loginUsers", aCookie.getValue());
								String response = "{\"enabled\":1,\"lid\":1000005,\"username\":\"admin\",\"email\":\"ramesh@gmail.com\",\"dob\":\"1111-11-11 00:00:00.0\"}";
								JSONObject sessionUser = new JSONObject(response);
								LeafUser leafuser = CommonUtil.getLeafUserFromSessionJson(sessionUser);
								OrgUtil.init(leafuser, request.getParameter("orgid"), request.getRemoteAddr());
		//						SecurityContext securityContext = SecurityContextHolder.getContext();
		//						session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
								return true;
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
						}
						
					}
				}
			} else {
				OrgUtil.setOwner(orgUser);
				OrgUtil.setOwnerid(orgUser.getLuid());
				OrgUtil.setUserlid(orgUser.getLid());
				return true;
			}
				
			} catch (Exception e) {
				LOGGER.log(Level.INFO, e.getMessage(), e);
			}
	    return false;
	}
	
	public static OrgUser getCurrentUser() {
		OrgUser orguser = null;
		User user = null;
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		try{
			if(a.getPrincipal() != null) {
				user = (User) a.getPrincipal();
			} else {
				return null;
			}
		}catch(Exception e) {
			return null;
		}
		if(user!=null) {
			DriverManagerDataSource datasource = new JdbcUtil().getAccountsDataSource(); 
			OrgUsersDao userDAO = new OrgUsersDaoImpl();
			userDAO.setDataSource(datasource);
			orguser = userDAO.loadUserByUsername(user.getUsername());
			
		}
		return orguser;
	}
	
}
