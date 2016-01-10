/* $Id$*/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leafsoft.restapi.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RestAPIUtil {
	private static final Logger LOGGER = Logger.getLogger(RestAPIUtil.class.getName());
    public static String getRequestURI(HttpServletRequest request) {
        return request.getRequestURI();
    }
    
    public static String getRequestMethod(HttpServletRequest request) {
        return request.getMethod();
    }
    
    public static String getContentType(HttpServletRequest request) {
        return request.getContentType();
    }
    
    public static boolean validateContentType(String contentType) {
        boolean result = false;
        if(contentType != null && !"".equals(contentType)) {
            if(contentType.equalsIgnoreCase("text/json")) {
                result = true;
            } else {
                result = false;
            }
        } else {
            result = false;
        }
        return result;
    }
    
    public static PrintWriter getWriter(HttpServletResponse response) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch(IOException ex) {
            Logger.getLogger(RestAPIUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }
    
}
