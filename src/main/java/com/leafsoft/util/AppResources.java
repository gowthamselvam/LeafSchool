package com.leafsoft.util;

import java.util.Properties;

import com.leafsoft.security.AESencrp;

public class AppResources {

	private static final Properties DATABASE_RESOURCES = new CommonUtil().getPropertyFile("properties/database.properties");
	private static final AppResources APPRESOURCES = new AppResources();
	private static final Properties APP_PROPERTIES = new CommonUtil().getPropertyFile("properties/application.properties");
	
	public static AppResources getInstance() {
		return APPRESOURCES;
	}
	
	public String getOrgDBConnectionUrl() {
		return DATABASE_RESOURCES.getProperty("spring.jdbc.connectionurl");
	}
	public String getOrgDBDatabase() {
		return DATABASE_RESOURCES.getProperty("spring.jdbc.accountsdatabase");
	}
	public String getMysqlDriverClassName() {
		return DATABASE_RESOURCES.getProperty("spring.jdbc.driverclass");
	}
	public String getOrgDBUserName() {
		return DATABASE_RESOURCES.getProperty("spring.jdbc.username");
	}
	public String getOrgDBPassword() {
		return DATABASE_RESOURCES.getProperty("spring.jdbc.password");
	}
	public String getTokeSecret() {
		return APP_PROPERTIES.getProperty("token.secret");
	}
	public String getFileSecret() {
		return APP_PROPERTIES.getProperty("token.secret");
	}
	public String getSmtpHost() {
		return APP_PROPERTIES.getProperty("mail.smtp.host");
	}
	public String getSmtpPort() {
		return APP_PROPERTIES.getProperty("mail.smtp.port");
	}
	public String getSmtpAuth() {
		return APP_PROPERTIES.getProperty("mail.smtp.auth");
	}
	public String getSmtpEnable() {
		return APP_PROPERTIES.getProperty("mail.smtp.starttls.enable");
	}
	public String getSmtpUser() {
		return APP_PROPERTIES.getProperty("mail.smtp.user");
	}
	public String getServer() {
		return APP_PROPERTIES.getProperty("leafsoft.server");
	}
	public String getSmtpPassword() {
		String password = "";
		try {
			password = AESencrp.decrypt(APP_PROPERTIES.getProperty("mail.smtp.password"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password;
	}
	public String getLeafSoftUrl() {
		return APP_PROPERTIES.getProperty("leafsoft.url").trim();
	}
	public String isDevelopmentMode() {
		return APP_PROPERTIES.getProperty("development.mode").trim();
	}
	public String getAccountsUrl() {
		return APP_PROPERTIES.getProperty("accounts.url").trim();
	}
}
