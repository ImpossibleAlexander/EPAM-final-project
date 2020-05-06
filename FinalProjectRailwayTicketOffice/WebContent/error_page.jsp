<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
	<fmt:setLocale value="${sessionScope.language}"/>
	<fmt:setBundle basename='property'/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error Page</title>
	<link rel="stylesheet" type="text/css"
		href="resources/style/navbar.css" />
	<link rel="stylesheet" type="text/css" href="resources/style/style.css" />
</head>
	
<body>
<div style="text-align: center">
		<h2><fmt:message key='message'/></h2>
		<h3><fmt:message key='${errorMessage}'/></h3>
		<a href="javascript:history.back()"><fmt:message
					key='history_back' /></a>
</div>
</body>
</html>