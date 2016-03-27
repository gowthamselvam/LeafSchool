package com.leafsoft.school.dao.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.leafsoft.school.dao.OrganizationDao;
import com.leafsoft.school.model.Course;
import com.leafsoft.util.JdbcUtil;

public class CoursesDaoImpl{
	
		private static final Logger LOGGER = Logger.getLogger(OrganizationDao.class.getName());
		
		private DataSource dataSource;
		
		private JdbcTemplate jdbcTemplate;
		
		public CoursesDaoImpl() {
			this.dataSource = JdbcUtil.getUserDataSource();
			jdbcTemplate = new JdbcTemplate(dataSource);
		}
		
		public Course loadCourseByCourse(String coursename) {
			Course course = null;
			try {
			String sql = "SELECT * FROM Courses WHERE course = ?";
			course = jdbcTemplate.queryForObject(sql,new Object[]{coursename},  new BeanPropertyRowMapper<Course>(Course.class));
			}catch(Exception e) {
				LOGGER.log(Level.INFO,"findByCustomerId():::"+coursename+e.getMessage(),e);
			}
			return course;
		}
}
