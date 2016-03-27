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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.leafsoft.http.HttpUtil;
import com.leafsoft.school.dao.OrgUserRolesDao;
import com.leafsoft.school.dao.OrgUsersDao;
import com.leafsoft.school.dao.OrganizationDao;
import com.leafsoft.school.dao.impl.OrgUserRolesDaoImpl;
import com.leafsoft.school.dao.impl.OrgUsersDaoImpl;
import com.leafsoft.school.dao.impl.OrganizationDaoImpl;
import com.leafsoft.school.model.OrgDetail;
import com.leafsoft.school.model.OrgUser;
import com.leafsoft.school.model.OrgUserRole;
import com.leafsoft.school.util.CommonUtil;
import com.leafsoft.user.LeafUser;
import com.leafsoft.util.AppResources;
import com.leafsoft.util.JSONUtil;


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
	private static ThreadLocal<Integer> ORGID = new ThreadLocal<Integer>();
	private static ThreadLocal<Boolean> VALIDORG = new ThreadLocal<Boolean>();
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
	 * @return the orgid
	 */
	public static Integer getOrgId() {
		return ORGID.get();
	}
	/**
	 * @return the validorg for the user
	 */
	public static Boolean isValidOrg() {
		return VALIDORG.get();
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
	 * @param userlid the orgid to set
	 */
	public static void setOrgId(Integer orgId) {
		ORGID.set(orgId);
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
	/**
	 * @param orgdb the validOrg to set
	 */
	public static void setValidOrg(Boolean isValidOrg) {
		VALIDORG.set(isValidOrg);
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
			HttpSession session = request.getSession();
			OrgUser orgUser = getCurrentUser();
			if(orgUser == null) {
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
								if(Boolean.valueOf(AppResources.getInstance().isDevelopmentMode())) {
									sessionUser = JSONUtil.getInstance().getDebugJson().getJSONObject("1").getJSONObject("userInfo");
								} else {
									String httpResponse = HttpUtil.makeApiCall(AppResources.getInstance().getAccountsUrl()+"/loginUsers", aCookie.getValue(),"GET");
									System.out.print("response:::::::::"+httpResponse);
									sessionUser = new JSONObject(httpResponse);
								} 
								} catch(Exception e) {
									e.printStackTrace();
								}
							}
								
						if(sessionUser!= null && sessionUser.length() <=0) {
							return false;
						} else if(sessionUser!=null){
							LeafUser leafuser = CommonUtil.getLeafUserFromSessionJson(sessionUser);
							OrgUtil.init(leafuser, request.getParameter("orgid"), request.getRemoteAddr());
	//						SecurityContext securityContext = SecurityContextHolder.getContext();
	//						session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
							OrgUtil.setUser(leafuser);
							OrgUtil.setUserlid(leafuser.getLid());
					        // Inject the datasource into the dao
					    	OrgUsersDao userDAO = new OrgUsersDaoImpl();
					    	orgUser = userDAO.loadOrgUserByLid(leafuser.getLid());
					    	if(orgUser == null) {
					    		orgUser = new OrgUser();
					    		orgUser.setEmail(leafuser.getEmail());
					    		orgUser.setLid(leafuser.getLid());
					    		orgUser.setUsername(leafuser.getUsername());
					    		orgUser.setDefaultorgid(-1);
					    		int luid = userDAO.insert(orgUser);
					    		orgUser.setLuid(luid);
					    	}
					    	initOrgDetails(request, orgUser);
					    	
							return true;
						}
						
					}
				} else {
//				Authentication a = SecurityContextHolder.getContext().getAuthentication();
//				a.setAuthenticated(false);
				initOrgDetails(request, orgUser);
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
			if(a.isAuthenticated()) {
				user = (User) a.getPrincipal();
				System.out.print("user::::"+user);
				if(user!=null) {
					OrgUsersDao userDAO = new OrgUsersDaoImpl();
					orguser = userDAO.loadUserByUsername(user.getUsername());
					System.out.print("username::::"+user.getUsername());
				}
			} else {
				return null;
			}
		}catch(Exception e) {
			return null;
		}
		return orguser;
	}
	
	public static void initOrgDetails(HttpServletRequest request, OrgUser orgUser) throws Exception{
		OrgUserRolesDao userRoleDao = new OrgUserRolesDaoImpl();
		OrganizationDao orgDao = new OrganizationDaoImpl();
    	int totalOrg = userRoleDao.getTotalNumberOfOrgForUser(orgUser.getLuid());//Check number orgs assoicated with the current user
    	
    	if(totalOrg == 1) {//if customer has only one org than allow him to access that org details 
    		//OrgUserRole orgUserRole  = userRoleDao.getSingleOrg(orgUser.getLuid());
    		OrgDetail orgDetails = orgDao.loadOrgDetailByOrgId(orgUser.getDefaultorgid());
			OrgUtil.setOrgdb(CommonUtil.getOrgDb(orgDetails.getOrgid()));
			OrgUtil.setOrgDetails(orgDetails);
			OrgUtil.setOrgId(orgDetails.getOrgid());
			OrgUtil.setValidOrg(true);
    	} else if(totalOrg > 1) { //If the customer has multiple orgs than get orgid from the browser if the org id is null than load the default org of the user
    		boolean isValidOrg = false;
    		String orgid = request.getParameter("orgid");
    		JSONArray orgUserRoles = userRoleDao.findAllUserOrg(orgUser.getLuid());
    		if(orgid != null) {
    			int org = Integer.valueOf(orgid);
    			for(int i=0; i<orgUserRoles.length();i++) {
    				OrgUserRole orgUserRole =(OrgUserRole) orgUserRoles.get(i);
    				if(org == orgUserRole.getOrgDetail().getOrgid()) {
    					isValidOrg = true;
    					OrgUtil.setOrgdb(CommonUtil.getOrgDb(orgUserRole.getOrgDetail().getOrgid()));
    					OrgUtil.setOrgDetails(orgUserRole.getOrgDetail());
    					OrgUtil.setOrgId(orgUserRole.getOrgDetail().getOrgid());
    					break;
    				}
    			}
    		} else {
    			OrgDetail orgDetails = orgDao.loadOrgDetailByOrgId(orgUser.getDefaultorgid());
    			OrgUtil.setOrgdb(CommonUtil.getOrgDb(orgDetails.getOrgid()));
				OrgUtil.setOrgDetails(orgDetails);
				OrgUtil.setOrgId(orgDetails.getOrgid());
				isValidOrg = true;
    		}
    		OrgUtil.setValidOrg(isValidOrg);
    	}
    	OrgUtil.setOwner(orgUser);
    	OrgUtil.setOwnerid(orgUser.getLuid());
    	OrgUtil.setUserlid(orgUser.getLid());
    	OrgUtil.setRemoteuseripaddress(request.getRemoteAddr());
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
		OrgUtil.setOrgId(null);
		OrgUtil.setOrgDetails(null);
		OrgUtil.setValidOrg(false);
	}
}
