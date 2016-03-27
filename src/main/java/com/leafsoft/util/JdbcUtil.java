package com.leafsoft.util;

import java.util.logging.Level;
import java.util.logging.Logger;


import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import com.leafsoft.org.OrgUtil;

public class JdbcUtil {
	private static Logger LOGGER = Logger.getLogger(JdbcUtil.class.getName());
	public DriverManagerDataSource getOrgDBDataSource() {
		AppResources jdbc = new AppResources();
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(jdbc.getMysqlDriverClassName());
        dataSource.setUrl(jdbc.getOrgDBConnectionUrl()+jdbc.getOrgDBDatabase());
        dataSource.setUsername(jdbc.getOrgDBUserName());
        dataSource.setPassword(jdbc.getOrgDBPassword());
        return dataSource;
	}
	
	public static void cleanUp(DriverManagerDataSource datasource) {
        try {
            if (!datasource.getConnection().isClosed()) {
            	datasource.getConnection().close();
            }
        } catch (Exception e) {
        	LOGGER.log(Level.SEVERE,"Exception While cleanup:::"+e);
        }
    }
	
	public static void createDatabase(String dbname) {
		try {
			AppResources jdbc = new AppResources();
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName(jdbc.getMysqlDriverClassName());
	        dataSource.setUrl(jdbc.getOrgDBConnectionUrl());
	        dataSource.setUsername(jdbc.getOrgDBUserName());
	        dataSource.setPassword(jdbc.getOrgDBPassword());
	        String sql = "CREATE DATABASE IF NOT EXISTS "+dbname;
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			jdbcTemplate.update(sql, new Object[] {});
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE,"Exception while create database"+e.getMessage(),e);
		}
	}
	
	public static DriverManagerDataSource getUserDataSource() {
		AppResources jdbc = new AppResources();
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(jdbc.getMysqlDriverClassName());
        dataSource.setUrl(jdbc.getOrgDBConnectionUrl()+OrgUtil.getOrgdb());
        dataSource.setUsername(jdbc.getOrgDBUserName());
        dataSource.setPassword(jdbc.getOrgDBPassword());
        return dataSource;
	}
	
    public static void executeQueryFromFile(DriverManagerDataSource dataSource) {
        Resource resource = new ClassPathResource("sqlfile/OrgTables.sql");
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setSeparator(";");
        databasePopulator.setScripts(resource);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);
    }

}
