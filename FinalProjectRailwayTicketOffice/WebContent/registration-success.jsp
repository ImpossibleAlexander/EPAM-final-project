<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
	
	<fmt:setLocale value="${sessionScope.language}"/>
	<fmt:setBundle basename='property'/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Registration success</title>
<link rel="stylesheet" type="text/css" href="resources/style/style.css"/>
 <link rel="stylesheet" type="text/css" href="resources/style/login.css" />
</head>
<body>
<form  class="login-form">
				 <h1><fmt:message key='registration_success'/>!</h1>
				 <div class="logbtn">
		         <a href="login.jsp" style="color: #FFFFFF; text-align: center"><h1><fmt:message key='login'/></a>
		         </div>			
</form>
</body>
</html>