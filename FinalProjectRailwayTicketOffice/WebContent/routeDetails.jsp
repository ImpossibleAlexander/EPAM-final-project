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
<nav>
		<ul>
			<li>
				<a href="main_page.jsp"><fmt:message key='timetable'/></a>
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
       				 <a class="active"><fmt:message key='route_details'/></a>
       				</li>
			<li>
				<select onchange="changeLanguage('changeLanguage', this.value, 'route_details.jsp')">
		 						<option value="en" <c:if test = "${sessionScope.language == 'en'}">selected</c:if>>English</option>
		  						<option value="ru" <c:if test =  "${empty sessionScope.language || sessionScope.language == 'ru'}">selected</c:if>>Русский</option>
				</select>
			</li>		
			<li style="float: right">
				<c:choose>
  			  	<c:when test="${userRole == 'CLIENT'}">
       				<a href="controller?command=logout"><fmt:message key='logout'/></a>
    			</c:when>
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
	
	<form action="controller" method="get" id="theForm">
		<input type="hidden" name="command" id="commandId" value="">
		<input type="hidden" name="language" id="languageId" value="">
		<input type="hidden" name="url" id="urlId" value="">
	</form>
	
		<script language="javascript">
		function changeLanguage(command, language, url) {
		    document.getElementById('commandId').value = command;
		    document.getElementById('languageId').value = language;
		    document.getElementById('urlId').value = url;
		    document.getElementById('theForm').submit();
		}
	</script>
</body>
</html>