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
import com.leafsoft.school.dao.OrgUsersDao;
import com.leafsoft.school.dao.OrganizationDao;
import com.leafsoft.school.model.OrgUser;
import com.leafsoft.user.LeafUser;
import com.leafsoft.util.JdbcUtil;

public class OrgUsersDaoImpl implements OrgUsersDao{
private static final Logger LOGGER = Logger.getLogger(OrganizationDao.class.getName());
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public int insert(OrgUser orgUser){
		
		final OrgUser finalOrgUser = orgUser;
		
		final String sql = "INSERT INTO OrgUsers " +
				"(lid, username ,email, createtime, defaultorgid) VALUES (?, ?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
			JdbcTemplate insert = new JdbcTemplate(dataSource);
			insert.update(
				    new PreparedStatementCreator() {
				        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				            PreparedStatement ps =
				                connection.prepareStatement(sql, new String[] {"luid"});
				            ps.setInt(1, finalOrgUser.getLid());
				            ps.setString(2, finalOrgUser.getUsername());
				            ps.setString(3, finalOrgUser.getEmail());
				            ps.setLong(4, System.currentTimeMillis());
				            ps.setInt(5, finalOrgUser.getDefaultorgid());
				            return ps;
				        }
				    },
				    keyHolder);
			LOGGER.log(Level.INFO, "New Org User" + keyHolder.getKey().intValue());
			
		    return  keyHolder.getKey().intValue();
			
	}
	
	public OrgUser loadOrgUserByLid(int lid) {
		OrgUser orgUser = null;
		try {
		String sql = "SELECT * FROM OrgUsers WHERE lid = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		orgUser = jdbcTemplate.queryForObject(sql,new Object[]{lid},  new BeanPropertyRowMapper<OrgUser>(OrgUser.class));
		}catch(Exception e) {
			LOGGER.log(Level.INFO,"findByCustomerId():::"+lid+e.getMessage(),e);
		}
		return orgUser;
	}
	
	public boolean hasUser(int lid) {
		String sql = "SELECT * FROM OrgUsers WHERE lid = ?";  
		try {
		Connection conn = dataSource.getConnection();
	    PreparedStatement ps = conn.prepareStatement(sql);
	    ps.setInt(1,lid);
	    ResultSet rs = ps.executeQuery();
	    return rs.next();
		} catch(Exception e) {
			LOGGER.log(Level.INFO,"hasOrg():::"+lid+e.getMessage(),e);
		}
		return false;
	    
	}
	
	@Override
	public OrgUser loadUserByUsername(String userName) {
		OrgUser user = null;
		try {
		String sql = "SELECT * FROM OrgUsers WHERE username = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		user = jdbcTemplate.queryForObject(sql,new Object[]{userName},  new BeanPropertyRowMapper<OrgUser>(OrgUser.class));
		}catch(Exception e) {
			LOGGER.log(Level.INFO,"loadUserByUsername():::"+userName+e.getMessage(),e);
		}
		return user;
	}
}
