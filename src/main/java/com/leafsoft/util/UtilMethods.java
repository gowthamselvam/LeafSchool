//$Id$
package com.leafsoft.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;

public class UtilMethods {
	private static final Logger LOGGER = Logger.getLogger(UtilMethods.class.getName());
	
	public static boolean contains(String id, JSONArray dest_arr) {
    	boolean isExists = false;
		try {
			int len = dest_arr.length();
			for(int i=0; i<len; i++) {
				String currId = dest_arr.getString(i);
				if(id.equalsIgnoreCase(currId)) {
					isExists = true;
					break;
				}
			}
		} catch (Exception e) {
			LOGGER.log(Level.INFO,"Method : contains(String,JSONArr) : "+e.getMessage(),e);
		}		
		return isExists;
	}
    
    public static boolean contains(String id, String[] dest_arr) {
    	boolean isExists = false;
		try {
			int len = dest_arr.length;
			for(int i=0; i<len; i++) {
				String currId = dest_arr[i];
				if(id.equalsIgnoreCase(currId)) {
					isExists = true;
					break;
				}
			}
		} catch (Exception e) {
			LOGGER.log(Level.INFO,"Method : contains(String,StringArr) : "+e.getMessage(),e);
		}		
		return isExists;
	}
    
    public static boolean isNumericLong(String str)
    {  
      try  
      {  
        long l = Long.parseLong(str);  
      }  
      catch(NumberFormatException nfe)  
      {  
        return false;  
      }  
      return true;  
    }
    
    public static boolean isNumber(String str) {
        int size = str.length();

        for (int i = 0; i < size; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        return size > 0;
    }
    
    public static Cookie getCookie(HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }
    
    public static JSONObject constructJSONObject(Properties prop) {
    	JSONObject json = new JSONObject();
    	try {
	    	if(prop != null && prop.size() > 0) {
	    		Enumeration e = prop.propertyNames();
	    		while (e.hasMoreElements()) {
			      String key = (String) e.nextElement();
			      json.put(key, prop.getProperty(key));
			    }
	    	}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return json;
    }
}
