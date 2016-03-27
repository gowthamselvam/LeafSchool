<%@page import="com.leafsoft.school.dao.impl.CoursesDaoImpl"%>
<%@page import="org.springframework.core.io.Resource"%>
<%@page import="org.springframework.jdbc.datasource.init.ResourceDatabasePopulator"%>
<%@page import="org.springframework.core.io.ResourceLoader"%>
<%@page import="org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType"%>
<%@page import="org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactory"%>
<%@page import="org.springframework.jdbc.datasource.embedded.EmbeddedDatabase"%>
<%@page import="org.springframework.jdbc.datasource.init.DatabasePopulatorUtils"%>
<%@page import="org.springframework.core.io.ClassPathResource"%>
<%@page import="com.leafsoft.school.model.OrgUserRole"%>
<%@page import="com.leafsoft.school.dao.OrgUsersDao"%>
<%@page import="com.leafsoft.util.JdbcUtil"%>
<%@page import="org.springframework.jdbc.datasource.DriverManagerDataSource"%>
<%@page import="com.leafsoft.school.dao.OrgUserRolesDao"%>
<%@page import="com.leafsoft.school.dao.impl.OrgUserRolesDaoImpl"%>
<%!

%>
<% 
out.print(new CoursesDaoImpl().loadCourseByCourse("1").getSection());
%>
