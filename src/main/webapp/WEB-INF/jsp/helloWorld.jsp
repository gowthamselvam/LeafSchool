<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<title>My first example using Spring 3 MVC</title>
	</head>
	<body>
		<h1>Welcome message : <c:out value="${msg}"/></h1>	
	</body>
</html>