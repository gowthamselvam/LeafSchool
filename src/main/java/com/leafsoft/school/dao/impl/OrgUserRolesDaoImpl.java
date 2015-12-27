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
	
}
