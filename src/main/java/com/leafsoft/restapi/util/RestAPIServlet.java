/* $Id : $*/
package com.leafsoft.restapi.util;


import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.leafsoft.util.Constants;
import com.leafsoft.util.JSONUtil;
import com.leafsoft.util.UtilMethods;

/**
 *
 * @author udhaya-zu282
 */
public class RestAPIServlet extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        PrintWriter out = null;
        try {
        	JSONObject restapiConfigJson = JSONUtil.getInstance().getRestAPIConfigJSON();
            String uri = RestAPIUtil.getRequestURI(request);
            String[] uriSplit = uri.split("/"); 
            String urikey = uriSplit[uriSplit.length-1];//NO i18n
            boolean isValidURI = restapiConfigJson.has(urikey);
            String apiclass = "";JSONArray apimethod = null;
            if(isValidURI)
            {
            	apiclass = restapiConfigJson.getJSONObject(urikey).getString(Constants.APICLASS);
            	apimethod = restapiConfigJson.getJSONObject(urikey).getJSONArray(Constants.APIMETHOD);
            }
            String requestMethod = RestAPIUtil.getRequestMethod(request);
            boolean isValidMethod = UtilMethods.contains(requestMethod, apimethod);
            //String contentType = RestAPIUtil.getContentType(request);
            String contentType = "text/json";//NO I18N
            boolean isValidContentType = RestAPIUtil.validateContentType(contentType);
            ErrorCode errorCode = null;
            JSONObject error = new JSONObject("{RESULT:'12'}");//No I18N
            boolean isValidParams = requestParamsValidator(request,response);
            if(isValidURI) {
                if(isValidMethod) {
                    if(isValidContentType) {
                        if(isValidParams) {
                            RestAPIHandler restapi = null;
                            try {
                            	restapi = (RestAPIHandler) Class.forName(apiclass).newInstance();
                            } catch (InstantiationException ex) {
                                Logger.getLogger(RestAPIServlet.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IllegalAccessException ex) {
                                Logger.getLogger(RestAPIServlet.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            Logger.getLogger(RestAPIServlet.class.getName()).log(Level.SEVERE, "URI : "+uri+",Method : "+requestMethod);

                            if(requestMethod.equalsIgnoreCase("GET")){
                                restapi.get(request, response);
                            } else if(requestMethod.equalsIgnoreCase("POST")){
                                restapi.create(request, response);
                            } else if(requestMethod.equalsIgnoreCase("PUT")){
                                restapi.update(request, response);
                            } else if(requestMethod.equalsIgnoreCase("DELETE")){
                                restapi.delete(request, response);
                            }
                        }
                    } else {
                            out = RestAPIUtil.getWriter(response);
                            errorCode = ErrorCode.TYPE;
                            error.put("CODE", errorCode.getId());
                            error.put("ERRORMSG", errorCode.getMessage());
                            out.print(error);
                    }
                } else {
                        out = RestAPIUtil.getWriter(response);
                        errorCode = ErrorCode.METHOD;
                        error.put("CODE", errorCode.getId());
                        error.put("ERRORMSG", errorCode.getMessage());
                        out.print(error);
                }
            } else {
                    out = RestAPIUtil.getWriter(response);
                    errorCode = ErrorCode.URI;
                    error.put("CODE", errorCode.getId());
                    error.put("ERRORMSG", errorCode.getMessage());
                    out.print(error);
            }
        } catch (Exception ex) {
            Logger.getLogger(RestAPIServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(out != null) {
                out.close();
            }
        }
    }
    
    
    private boolean requestParamsValidator(HttpServletRequest request,HttpServletResponse response) {
        boolean isValidParams = false;
        PrintWriter out = null;
        try {
            ErrorCode errorCode = null;
            JSONObject error = new JSONObject("{RESULT:'12'}");//No I18N
            String jsonString = request.getParameter("JSONString");
            if((jsonString == null) || ("".equals(jsonString))) {
                out = RestAPIUtil.getWriter(response);
                errorCode = ErrorCode.INVALID_PARAM;
                error.put("CODE", errorCode.getId());
                error.put("ERRORMSG", errorCode.getMessage());
                out.print(error);
            } else {
                try { 
                    JSONObject jsonObject = new JSONObject(jsonString);
                    isValidParams = true;
                } catch(JSONException ex) {
                    out = RestAPIUtil.getWriter(response);
                    errorCode = ErrorCode.PARSING_JSON;
                    error.put("CODE", errorCode.getId());
                    error.put("ERRORMSG", errorCode.getMessage());
                    out.print(error);
                    Logger.getLogger(RestAPIServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        } catch(Exception ex) {
            Logger.getLogger(RestAPIServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(out !=  null) {
                out.close();
            }
        }
        return isValidParams;
    }
}
