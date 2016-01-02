package com.leafsoft.school.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.leafsoft.org.OrgUtil;
import com.leafsoft.school.dao.OrgUserRolesDao;
import com.leafsoft.school.dao.OrganizationDao;
import com.leafsoft.school.model.OrgDetail;
import com.leafsoft.school.model.OrgUser;
import com.leafsoft.school.model.OrgUserRole;

public class OrgUserRolesDaoImpl implements OrgUserRolesDao {
private static final Logger LOGGER = Logger.getLogger(OrganizationDao.class.getName());
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public int insert(OrgUser orgUser,String role){
		
		final String finalrole = role;
		final OrgUser finalOrgUser = orgUser;
		
		final String sql = "INSERT INTO OrgUserRoles " +
				"(orgid, luid, ,rolename) VALUES (?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
			JdbcTemplate insert = new JdbcTemplate(dataSource);
			insert.update(
				    new PreparedStatementCreator() {
				        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				            PreparedStatement ps =
				                connection.prepareStatement(sql, new String[] {"user_role_id"});
				            ps.setInt(1, finalOrgUser.getLuid());
				            ps.setInt(2, finalOrgUser.getLid());
				            ps.setString(3, finalrole);
				            return ps;
				        }
				    },
				    keyHolder);
			LOGGER.log(Level.INFO, "OrgUserRoles:Id::" + keyHolder.getKey().intValue());
		    return  keyHolder.getKey().intValue();
	}
	
	public OrgUserRole loadOrgUserByLuid(int luid,int orgId) {
		OrgUserRole orgUserrole = null;
		try {
		String sql = "SELECT * FROM OrgUserRoles WHERE luid = ? and orgid = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		orgUserrole = jdbcTemplate.queryForObject(sql,new Object[]{luid,orgId},  new BeanPropertyRowMapper<OrgUserRole>(OrgUserRole.class));
		}catch(Exception e) {
			LOGGER.log(Level.INFO,"findByCustomerId():::"+luid+e.getMessage(),e);
		}
		return orgUserrole;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<OrgUserRole> findAllOrg(int luid) {
		List<OrgUserRole> orgUserrole = null;
		try {
		//String sql = "SELECT our.orgid,our.orgname,od.rolename FROM OrgUserRoles our inner join OrgDetails od on our.orgid = od.orgid WHERE our.luid = ? and our.orgid = ?";
		String sql = "SELECT * FROM OrgUserRoles WHERE luid = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		orgUserrole = jdbcTemplate.query(sql,new Object[]{luid},  new BeanPropertyRowMapper(OrgUserRole.class));
		}catch(Exception e) {
			LOGGER.log(Level.INFO,"findByCustomerId():::"+luid+e.getMessage(),e);
		}
		return orgUserrole;
	}
	
	public OrgUserRole getSingleOrg(int luid) {
		OrgUserRole orgUserrole = null;
		try {
		String sql = "SELECT * FROM OrgUserRoles WHERE luid = ? limit 1";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		orgUserrole = jdbcTemplate.queryForObject(sql,new Object[]{luid},  new BeanPropertyRowMapper<OrgUserRole>(OrgUserRole.class));
		}catch(Exception e) {
			LOGGER.log(Level.INFO,"findByCustomerId():::"+luid+e.getMessage(),e);
		}
		return orgUserrole;
	}
	
	public int getTotalNumberOfOrgForUser(int luid) {
		int count = 0;
		try {
			String sql = "SELECT count(*) FROM OrgUserRoles WHERE luid = ?";
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			count = jdbcTemplate.queryForObject(sql, new Object[]{luid},Integer.class);
		}catch(Exception e) {
			LOGGER.log(Level.INFO,"findByCustomerId():::"+luid+e.getMessage(),e);
		}
		return count;
	}
	
}
