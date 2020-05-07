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

	<jsp:useBean id="trainStationBean" scope="application"
    class="ua.nure.kaplin.SummaryTask4.db.bean.TrainStationBean"/>
    
	<c:set var="userRole" scope="page" value="${sessionScope.userRole}"/>
	<c:set var="route" scope="page" value="${route}"/>
	
	<nav>
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
       				 <a  href="controller?command=mapping&page=user_page"><fmt:message key='user_page'/></a>
       				</li>
    			</c:when> 		
			</c:choose>
			<li>
				<select onchange="changeLanguage('changeLanguage', this.value, 'main_page.jsp')">
		 						<option value="en" <c:if test = "${sessionScope.language == 'en'}">selected</c:if>>English</option>
		  						<option value="ru" <c:if test =  "${empty sessionScope.language || sessionScope.language == 'ru'}">selected</c:if>>Русский</option>
				</select>
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
	
	<div>
		<table border="1" width="200px" cellpadding="5" align="right"  style="background-color: white">
							<tr>
								<th><h3><fmt:message key='stations'/></h3></th>
							</tr>
							<c:forEach var="station" items="${trainStationBean.getStations()}">
								<tr>
									<td>${station.stationName}</td>						
								</tr>
							</c:forEach>
				</table>
	</div>
	
	<div style="width: 450px;">
	<form action="controller" method="get">
		<input type="hidden" name="command" value="route">
		<fieldset style="background-color: white">
			<legend><h3><fmt:message key='train_search'/></h3></legend>
			<legend>
						<fmt:message key='train_number' />
						: <input type="text" name="trainNumber"  pattern="^\d+$" oninvalid="this.setCustomValidity('<fmt:message key='invalid_train_number'/>')" oninput="setCustomValidity('')"/>
					</legend>
			<legend>
				<fmt:message key='departure_station'/>: <input type="text" name="departureStation" />
			</legend>
			<legend>
				<fmt:message key='arrival_station'/>: <input type="text" name="arrivalStation" />
			</legend>
		</fieldset>
		<br /> <input type="submit" value="<fmt:message key='button_search'/>" class="button-accept">
	</form>
	</div>
	
	<form action="controller" method="get" id="theForm">
		<input type="hidden" name="command" id="commandId" value="">
		<input type="hidden" name="language" id="languageId" value="">
		<input type="hidden" name="url" id="urlId" value="">
	</form>
	
	<c:choose>
		<c:when test="${empty routes}"/> 
		<c:otherwise>
			<table border="1" width="10%" cellpadding="5" style="background-color: white">
				<tr>
					<th><fmt:message key='train_number'/></th>
					<th><fmt:message key='departure_station'/></th>
					<th><fmt:message key='dep_date_and_time'/></th>
					<th><fmt:message key='arrival_station'/></th>
					<th><fmt:message key='dest_date_and_time'/></th>
					<th><fmt:message key='coupe'/></th>
					<th><fmt:message key='reserved_seat'/></th>
					<th><fmt:message key='common'/></th>
					<th><fmt:message key='price_coupe'/></th>
					<th><fmt:message key='price_reserved_seat'/></th>
					<th><fmt:message key='price_common'/></th>
					<th><fmt:message key='train_status'/></th>
				</tr>
				<c:forEach var="route" items="${routes}">
					<c:if test = "${(empty route.departureDateAndTime && empty route.destinationDateAndTime) 
												|| (empty route.departureDateAndTime || empty route.destinationDateAndTime)}">
								<c:redirect url="controller?command=mapping&page=error_page&errorMessage=cannotFindRoute" />
					</c:if>
			
					<tr>
						<td>${route.trainNumber}</td>
						<td>${route.stationName}</td>
						<td>${route.departureDateAndTime}</td>
						<td>${route.destinationStationName}</td>
						<td>${route.destinationDateAndTime}</td>
						<td>${route.coupe}</td>
						<td>${route.reservedSeat}</td>
						<td>${route.common}</td>
						<td>${route.coupePrice}</td>
						<td>${route.reservedSeatPrice}</td>
						<td>${route.commonPrice}</td>
						<td>${route.trainStatus}</td>
						<td>
							<form action="controller" method="get">
								<input type="hidden" name="command" value="routeDetails">
								<input type="hidden" name="trainNumber" value="${route.trainNumber}">
								<input type="hidden" name="departureStation" value="${route.stationName}">
								<input type="hidden" name="destinationStation" value="${route.destinationStationName}">
								<input type="submit" value="<fmt:message key='details'/>" class="button-accept">
							</form>
						</td>
						
						<c:choose>
		  			  	<c:when test="${userRole == 'CLIENT'}">	  	
		  			  			<td>
								<form action="controller" method="get">
									<input type="hidden" name="command" value="addToBasket">
									<input type="hidden" name="trainNumber" value="${route.trainNumber}">
									<input type="hidden" name="departureStation" value="${route.stationName}">
									<input type="hidden" name="departureDateAndTime" value="${route.departureDateAndTime}">
									<input type="hidden" name="destinationStationName" value="${route.destinationStationName}">
									<input type="hidden" name="destinationDateAndTime" value="${route.destinationDateAndTime}">
									<input type="hidden" name="coupePrice" value="${route.coupePrice}">
									<input type="hidden" name="reservedSeatPrice" value="${route.reservedSeatPrice}">
									<input type="hidden" name="commonPrice" value="${route.common}">
									<input type="submit" value="<fmt:message key='add_to_basket'/>" class="button-accept">
								</form>
								</td>
		    				</c:when>
						</c:choose>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>

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