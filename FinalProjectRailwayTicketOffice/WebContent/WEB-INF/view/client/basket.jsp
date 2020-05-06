<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

	<fmt:setLocale value="${sessionScope.language}"/>
	<fmt:setBundle basename='property'/>
	
<html>
<head>

	<meta charset="UTF-8">
	<title>Timetable</title>
	<link rel="stylesheet" type="text/css"
		href="resources/style/navbar.css" />
	<link rel="stylesheet" type="text/css" href="resources/style/style.css" />
</head>
<body>
    
	<c:set var="routesForBasket" scope="page" value="${sessionScope.routesForBasket}"/>
	<nav>
		<ul>
			<li>
				<a href="main_page.jsp"><fmt:message key='timetable'/></a>
			</li>
				<c:choose>
    			<c:when test="${userRole == 'CLIENT'}">
    				<li>
       				 <a class="active" href="controller?command=mapping&page=basket"><fmt:message key='basket'/></a>
       				</li>
       				<li>
       				 <a href="controller?command=mapping&page=user_page"><fmt:message key='user_page'/></a>
       				</li>
    			</c:when>
			</c:choose>
				<li>
				<select>
		 						<option onclick="changeLanguage('changeLanguage', 'en', 'controller?command=mapping&page=basket')"  <c:if test = "${sessionScope.language == 'en'}">selected</c:if>>English</option>
		  						<option onclick="changeLanguage('changeLanguage', 'ru', 'controller?command=mapping&page=basket')" <c:if test =  "${empty sessionScope.language || sessionScope.language == 'ru'}">selected</c:if>>Русский</option>
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
		<c:when test="${empty routesForBasket}">	
			<h3><fmt:message key='empty_basket'/></h3>
		</c:when> 
		<c:otherwise>
			<table border="1" width="10%" cellpadding="5" align="center">
				<tr>
					<th><fmt:message key='train_number'/></th>
					<th><fmt:message key='departure_station'/></th>
					<th><fmt:message key='dep_date_and_time'/></th>
					<th><fmt:message key='arrival_station'/></th>
					<th><fmt:message key='dest_date_and_time'/></th>
					<th><fmt:message key='price_coupe'/></th>
					<th><fmt:message key='price_reserved_seat'/></th>
					<th><fmt:message key='price_common'/></th>
				</tr>
				<c:forEach var="route" items="${routesForBasket}">
					<tr>
						<td>${route.trainNumber}</td>
						<td>${route.stationName}</td>
						<td>${route.departureDateAndTime}</td>
						<td>${route.destinationStationName}</td>
						<td>${route.destinationDateAndTime}</td>
						<td>${route.coupePrice}</td>
						<td>${route.reservedSeatPrice}</td>
						<td>${route.commonPrice}</td>							
							<td>
							<form action="controller" method="post">
									<input type="hidden" name="command" value="buyTicket">
									<input type="hidden" name="trainNumber" value="${route.trainNumber}">
									<input type="hidden" name="departureStation" value="${route.stationName}">
									<input type="hidden" name="departureDateAndTime" value="${route.departureDateAndTime}">
									<input type="hidden" name="destinationStation" value="${route.destinationStationName}">
									<input type="hidden" name="destinationDateAndTime" value="${route.destinationDateAndTime}">
									<select name="place">
		 								 <option value="coupe"><fmt:message key='coupe_basket'/></option>
		  								<option value="reserved"><fmt:message key='reserved_basket'/></option>
		  								<option value="common"><fmt:message key='common_basket'/></option>
									</select>
									<input type="hidden" name="coupePrice" value="${route.coupePrice}">
									<input type="hidden" name="reservedSeatPrice" value="${route.reservedSeatPrice}">
									<input type="hidden" name="commonPrice" value="${route.commonPrice}">
									<input type="submit" value="<fmt:message key='buy_ticket'/>" class="button-accept">
						</form>
						</td>							
						<td>
							<form action="controller" method="get">
								<input type="hidden" name="command" value="deleteFromBasket">
								<input type="hidden" name="trainNumber" value="${route.trainNumber}">
								<input type="submit" value="<fmt:message key='delete_ticket'/>" class="button-accept">
							</form>
						</td>
						<td>
							<form action="controller" method="get">
								<input type="hidden" name="command" value="routeDetails">
								<input type="hidden" name="trainNumber" value="${route.trainNumber}">
								<input type="hidden" name="departureStation" value="${route.stationName}">
								<input type="hidden" name="destinationStation" value="${route.destinationStationName}">
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