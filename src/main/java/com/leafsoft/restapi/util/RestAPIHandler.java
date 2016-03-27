/* $Id : $*/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leafsoft.restapi.util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author udhaya-zu282
 */
public interface RestAPIHandler {
    
    public void get(HttpServletRequest request,HttpServletResponse response);
    public void create(HttpServletRequest request,HttpServletResponse response);
    public void update(HttpServletRequest request,HttpServletResponse response);
    public void delete(HttpServletRequest request,HttpServletResponse response);
    
}
