/* $Id$ JSONUtil.java */
package com.leafsoft.util;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;


public class JSONUtil {
	
	private static final String DEBUG_JSON = "debug.json";//NO I18N

	private JSONObject debug;

	private static final JSONUtil JSON_UTIL = new JSONUtil(); 
	
	public static JSONUtil getInstance() {
		return JSON_UTIL;
	}
	
	private JSONObject getJSONObject(String resource,String path)
			throws Exception {
		String json = getJSONString(resource,path);
		if (json != null) {
			return new JSONObject(json);
		}
		return new JSONObject();
	}

	private JSONArray getJSONArray(String resource,String path)
			throws Exception {
		return new JSONArray(getJSONString(resource,path));
	}

	private String getJSONString(String resource,String path)
			throws Exception {
		if(path == null) {
			path = Constants.JSON_PATH;
		}
		ClassLoader classLoader = getClass().getClassLoader();
		File resourceFile = null;
		if (resourceFile == null || !resourceFile.exists()) {
			resourceFile = new File(path);
			if (!resourceFile.exists()) {
				resourceFile = new File(classLoader.getResource(path + "/" + resource).getFile());
			}
		}
		return IOUtils.toString(new FileInputStream(resourceFile));
	}
	
	public JSONObject getDebugJson() throws Exception {
		if (debug == null) {
			debug = getJSONObject(DEBUG_JSON,null);
		}
		return debug;
	}
}
