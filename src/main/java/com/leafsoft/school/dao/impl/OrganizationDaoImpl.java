package com.leafsoft.school.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.leafsoft.org.OrgUtil;
import com.leafsoft.school.dao.OrganizationDao;
import com.leafsoft.school.model.OrgDetail;
import com.leafsoft.school.model.OrgUser;

public class OrganizationDaoImpl implements OrganizationDao{
	
	private static final Logger LOGGER = Logger.getLogger(OrganizationDao.class.getName());
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public int insert(OrgDetail org){
		
		
		final OrgDetail finalOrg = org;
		
		final String sql = "INSERT INTO OrgDetails " +
				"(orgname, address, country, state, city, zipcode,timetype,dateformat,currencycode,createdtime,status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		final String sql1 = "INSERT INTO OrgUserRoles " +
				"(orgid, luid, rolename) VALUES (?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
			JdbcTemplate insert = new JdbcTemplate(dataSource);
			insert.update(
				    new PreparedStatementCreator() {
				        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				            PreparedStatement ps =
				                connection.prepareStatement(sql, new String[] {"orgid"});
				            ps.setString(1, finalOrg.getOrgname());
				            ps.setString(2, finalOrg.getAddress());
				            ps.setString(3, finalOrg.getCountry());
				            ps.setString(4, finalOrg.getState());
				            ps.setString(5, finalOrg.getCity());
				            ps.setString(6, finalOrg.getZipcode());
				            ps.setString(7, finalOrg.getTimetype());
				            ps.setString(8, finalOrg.getDateformat());
				            ps.setString(9, finalOrg.getCurrencycode());
				            ps.setLong(10, System.currentTimeMillis());
				            ps.setInt(11, finalOrg.getStatus());
				            return ps;
				        }
				    },
				    keyHolder);
			
		    insert.update(sql1,
			        new Object[] { keyHolder.getKey(), OrgUtil.getOwnerid(), "ROLE_ADMIN"});
		    return  keyHolder.getKey().intValue();
			
	}
	
	public OrgDetail loadOrgDetailByLid(long orgId) {
		OrgDetail org = null;
		try {
		String sql = "SELECT * FROM OrgUsers WHERE lid = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		org = jdbcTemplate.queryForObject(sql,new Object[]{orgId},  new BeanPropertyRowMapper<OrgDetail>(OrgDetail.class));
		}catch(Exception e) {
			LOGGER.log(Level.INFO,"findByCustomerId():::"+orgId+e.getMessage(),e);
		}
		return org;
	}
	
	public boolean hasOrg(String orgId) {
		String sql = "SELECT * FROM OrgDetails WHERE orgid = ?";  
		try {
		Connection conn = dataSource.getConnection();
	    PreparedStatement ps = conn.prepareStatement(sql);
	    ps.setString(1,orgId);
	    ResultSet rs = ps.executeQuery();
	    return rs.next();
		} catch(Exception e) {
			LOGGER.log(Level.INFO,"hasOrg():::"+orgId+e.getMessage(),e);
		}
		return false;
	    
	}

}
