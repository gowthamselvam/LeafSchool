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
  <script>
  $(function() {
	  $("#dob" ).val("");
	  $("#dob" ).datepicker({ dateFormat: 'mm/dd/yy' });
  });
  </script>
<title>Registration</title>
</head>
<body onload='document.registration.username.focus();'>
<div id='box' align="center">
<img src="images/leafsoft.png" alt="LeafSoft" >
        <form:form action="schoolRegister" method="post" commandName="orgForm" name='registration'>
            <table border="0">
                <tr>
                    <td colspan="2" align="center"><h2>School Management- Registration</h2></td>
                </tr>
                <tr>
                 <td colspan="2" align="center">
		            <c:if test="${not empty error}">
					<div id="error" class="error">${error}</div>
					</c:if>
					</td>
		        </tr>
                <tr>
                    <td>Organization Name:</td>
                    <td><form:input id='orgname' path="orgname" class ='tb5'/></td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td><form:textarea id='address' path="address" class ='ta10'/></td>
                </tr>
                <tr>
                    <td>Country:</td>
                    <td><form:input id='country' path="country" class ='tb5'/></td>
                </tr>
                <tr>
                    <td>State:</td>
                    <td><form:input id='state' path="state" class ='tb5'/></td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td><form:input id='city' path="city" class ='tb5'/></td>
                </tr>
                <tr>
                    <td>Zipcode:</td>
                    <td><form:input id='zipcode' path="zipcode" class ='tb5'/></td>
                </tr>
                <tr>
                    <td>DateFormat:</td>
                    <td><form:input id='dateformat' path="dateformat" class ='tb5'/></td>
                </tr>
                 <tr>
                    <td>CurrencyCode:</td>
                    <td><form:input id='currencycode' path="currencycode" class ='tb5'/></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input class = 'fb9' type="submit" id='register' value="Register" /></td>
                </tr>
            </table>
            <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
        </form:form>
    </div>
</body>
</html>