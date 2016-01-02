<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css">
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/common.js"></script>
	<head>
		<title>School</title>
	</head>
	<body>
	<img src="images/leafsoft.png" alt="LeafSoft" >
		<h2><c:out value="${message}"/></h2>
		<a href="<c:out value='${leafsofturl}'/>" > Visit account.Leafsoft.com!</a>
	</body>
</html>