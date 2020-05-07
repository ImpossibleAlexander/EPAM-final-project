<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
	<fmt:setLocale value="${sessionScope.language}"/>
	<fmt:setBundle basename='property'/>

<html>
<head>
	<link rel="stylesheet" type="text/css"
		href="resources/style/navbar.css" />
	<link rel="stylesheet" type="text/css" href="resources/style/style.css" />
	<link rel="stylesheet" type="text/css"
		href="resources/style/navbar.css" />
</head>
<body>

	<jsp:useBean id="userOrderBean" scope="application"
	    class="ua.nure.kaplin.SummaryTask4.db.bean.UserOrderBean"/>

	<c:set var="user" scope="page" value="${sessionScope.user}"/>	
	<jsp:setProperty name="userOrderBean" property="user" value="${user}"/>
	
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
       				 <a class="active" href="controller?command=mapping&page=user_page"><fmt:message key='user_page'/></a>
       				</li>
    			</c:when>
			</c:choose>
			<li>
				<select onchange="changeLanguage('changeLanguage', this.value, 'controller?command=mapping&page=user_page')">
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
	<form action="controller" method="get" id="theForm">
		<input type="hidden" name="command" id="commandId" value="">
		<input type="hidden" name="language" id="languageId" value="">
		<input type="hidden" name="url" id="urlId" value="">
	</form>
	<div style="margin: auto; text-align: center">	
	<c:choose>
		<c:when test="${empty userOrderBean.findUserTickets()}">	
			<h3><fmt:message key='no_tickets'/></h3>
		</c:when> 
		<c:otherwise>
			<table border="1" width="10%" cellpadding="5" align="center">
				<tr>
					<th><fmt:message key='ticket_number'/></th>
					<th><fmt:message key='train_number'/></th>
					<th><fmt:message key='departure_station'/></th>
					<th><fmt:message key='dep_date_and_time'/></th>
					<th><fmt:message key='arrival_station'/></th>
					<th><fmt:message key='dest_date_and_time'/></th>
					<th><fmt:message key='place'/></th>
					<th><fmt:message key='price'/></th>
				</tr>
				<c:forEach var="ticket" items="${userOrderBean.findUserTickets()}">
					<tr>
						<td>${ticket.ticketNumber}</td>
						<td>${ticket.trainNumber}</td>
						<td>${ticket.departureStation}</td>
						<td>${ticket.departureDateAndTime}</td>
						<td>${ticket.destinationStation}</td>
						<td>${ticket.destinationDateAndTime}</td>
						<td>${ticket.place}</td>
						<td>${ticket.price}</td>
						<td>
							<form action="controller" method="get">
								<input type="hidden" name="command" value="routeDetails">
								<input type="hidden" name="trainNumber" value="${ticket.trainNumber}">
								<input type="hidden" name="departureStation" value="${ticket.departureStation}">
								<input type="hidden" name="destinationStation" value="${ticket.destinationStation}">
								<input type="submit" value="<fmt:message key='details'/>" class="button-accept">
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>	
	</div>
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