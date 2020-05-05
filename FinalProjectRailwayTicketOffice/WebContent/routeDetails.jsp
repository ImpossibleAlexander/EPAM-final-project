<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
	<fmt:setLocale value="${sessionScope.language}"/>
	<fmt:setBundle basename='property'/>
<html>
<head>
	<meta charset="UTF-8">
	<title>Route details</title>
	<link rel="stylesheet" type="text/css"
		href="resources/style/navbar.css" />
	<link rel="stylesheet" type="text/css" href="resources/style/style.css" />
</head>
<body>
		<nav class="two">
		<ul>
			<li>
				<a class="active" href="main_page.jsp"><fmt:message key='timetable'/></a>
			</li>
				<c:choose>
    			<c:when test="${userRole == 'CLIENT'}">
    				<li>
       				 <a href="controller?command=mapping&page=basket"><fmt:message key='basket'/></a>
       				</li>
       				<li>
       				 <a href="controller?command=mapping&page=user_page"><fmt:message key='user_page'/></a>
       				</li>
    			</c:when>
			</c:choose>
					<li>
       				 <a href="controller?command=changeLanguage&language=en">English</a>
       				</li>		
       				<li>
       				 <a href="controller?command=changeLanguage&language=ru">Русский</a>
       				</li>			
			<li style="float: right">
				<c:choose>
  			  	<c:when test="${userRole == 'ADMIN'||userRole == 'CLIENT'}">
       				<a href="controller?command=logout"><fmt:message key='logout'/></a>
    			</c:when>
   			 	<c:otherwise>
        			<a href="login.jsp"><fmt:message key='login'/></a>
   				 </c:otherwise>
			</c:choose>
			</li>
		</ul>
	</nav>

	<div style="margin: auto; text-align: center">
		<table border="1" width="10%" cellpadding="5"  align="center">
				<tr>
					<th><fmt:message key='train_number'/></th>
					<th><fmt:message key='departure_station'/></th>
					<th><fmt:message key='dep_date_and_time'/></th>
					<th><fmt:message key='dest_date_and_time'/></th>
					<th><fmt:message key='arrival_station'/></th>
				</tr>
				<c:forEach var="route" items="${routes}">
					<tr>
						<td>${route.trainNumber}</td>
						<td>${route.stationName}</td>
						<td>${route.destinationDateAndTime}</td>
						<td>${route.departureDateAndTime}</td>
						<td>${route.destinationStationName}</td>				
					</tr>
				</c:forEach>
			</table>			
		<a href="javascript:history.back()"><fmt:message key='history_back'/></a>
	</div>
</body>
</html>