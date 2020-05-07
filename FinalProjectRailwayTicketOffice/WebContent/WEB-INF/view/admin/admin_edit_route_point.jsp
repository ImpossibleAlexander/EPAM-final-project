<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
	<fmt:setLocale value="${sessionScope.language}"/>
	<fmt:setBundle basename='property'/>
<html>
<head>
	<title>Edit route</title>
	<link rel="stylesheet" type="text/css"
		href="resources/style/navbar.css" />
	<link rel="stylesheet" type="text/css" href="resources/style/style.css" />
		<link rel="stylesheet" type="text/css"
		href="resources/style/navbar.css" />
</head>
	<c:set var="userRole" scope="page" value="${sessionScope.userRole}"/>
	<nav>
		<ul>
			<li>
				<a href="controller?command=mapping&page=admin_main_page"><fmt:message key='timetable'/></a>
			</li>
			<li>	
       			<a class="active" href="controller?command=mapping&page=admin"><fmt:message key='edit'/></a>
			</li>
				<li>
				<select onchange="changeLanguage('changeLanguage', this.value, 'controller?command=mapping&page=admin')">
		 						<option value="en" <c:if test = "${sessionScope.language == 'en'}">selected</c:if>>English</option>
		  						<option value="ru" <c:if test =  "${empty sessionScope.language || sessionScope.language == 'ru'}">selected</c:if>>Русский</option>
				</select>
			</li>	
			<li style="float: right">
				<c:choose>
  			  	<c:when test="${userRole == 'ADMIN'}">
       				<a href="controller?command=logout"><fmt:message key='logout'/></a>
    			</c:when>
   			 	<c:otherwise>
        			<a href="login.jsp"><fmt:message key='login'/></a>
   				 </c:otherwise>
			</c:choose>
			</li>		
		</ul>
	</nav>
	
	<form action="controller" method="get" id="theForm">
		<input type="hidden" name="command" id="commandId" value="">
		<input type="hidden" name="language" id="languageId" value="">
		<input type="hidden" name="url" id="urlId" value="">
	</form>
	
	<div style="width: 450px">
		<form action="controller" method="post" id="theForm1">		
			<input type="hidden" name="command" id="commandEditStationId" value="">
			<input type="hidden" name="action" id="actionId" value="">
			
			<fieldset style="background-color: white">
			<legend><h3><fmt:message key='edit_station'/></h3></legend>
			<legend>
				<fmt:message key='station_name'/>: <input type="text" name="stationName1" value="${oldStationName}"/>
			</legend>
			<legend>
				<fmt:message key='new_station_name'/>: <input type="text" name="stationName2"/>
			</legend>
		</fieldset>	
			<input type="button" name="create" value="<fmt:message key='create'/>" class="button-accept"  onclick="doCommandEditStation('adminEditStation', 'create')">
			<input type="button" name="update" value="<fmt:message key='update'/>" class="button-accept"  onclick="doCommandEditStation('adminEditStation', 'update')">
			<input type="button" name="delete" value="<fmt:message key='delete'/>" class="button-accept"  onclick="doCommandEditStation('adminEditStation', 'delete')">
		</form>
		</div>
		
		<div style="width: 450px">
		<form action="controller" method="post">		
			<fieldset style="background-color: white">
			<legend><h3><fmt:message key='add_route_point'/></h3></legend>
			<legend>
				<fmt:message key='train_number'/>: <input type="text" name="trainNumber" value="${trainNumber}" required  pattern="^\d+$" oninvalid="this.setCustomValidity('<fmt:message key='invalid_train_number'/>')" oninput="setCustomValidity('')"/>
			</legend>
			<legend>
				<fmt:message key='departure_station'/>: <input type="text" name="stationName" required oninvalid="this.setCustomValidity('<fmt:message key='empty_field'/>')" oninput="setCustomValidity('')"/>
			</legend>
			<legend>
				<fmt:message key='dest_date_and_time'/>: <input type="datetime-local" name="destinationDateAndTime" required oninvalid="this.setCustomValidity('<fmt:message key='empty_field'/>')" oninput="setCustomValidity('')"/>
			</legend>
			<legend>
				<fmt:message key='dep_date_and_time'/>: <input type="datetime-local" name="departureDateAndTime" required oninvalid="this.setCustomValidity('<fmt:message key='empty_field'/>')" oninput="setCustomValidity('')"/>
			</legend>
		</fieldset>	
				<input type="hidden" name="command" value="createRoutePointCommand">			
				<input type="submit" name="create" value="<fmt:message key='create'/>" class="button-accept">	
		</form>
		</div>
		
		<c:choose>
		<c:when test="${empty routes}"/> 
		<c:otherwise>
		
			<table border="1" width="10%" cellpadding="5" style="background-color: white">
				<tr>
					<th><fmt:message key='train_number'/></th>
					<th><fmt:message key='departure_station'/></th>
					<th><fmt:message key='dest_date_and_time'/></th>
					<th><fmt:message key='dep_date_and_time'/></th>
					<th><fmt:message key='arrival_station'/></th>
				</tr>
				<c:forEach var="route" items="${routes}">
					<form action="controller" method="post">
					<tr>
						<td>${route.trainNumber}<input type="hidden" name="trainId" value="${route.trainId}"></td>
						<td><input type="text" name="stationName" value="${route.stationName}"></td>
						<td><input type="text" name="destinationDateAndTime" value="${route.destinationDateAndTime}"></td>
						<td><input type="text" name="departureDateAndTime" value="${route.departureDateAndTime}"></td>
						<td>${route.destinationStationName}</td>
						<td>		
								<input type="hidden" name="command" value="routePointUpdateCommand">				
								<input type="submit" name="update" value="<fmt:message key='update'/>" class="button-accept">			
						</td>				
						</tr>
					</form>
					</c:forEach>	
				</table>
		</c:otherwise>
	</c:choose>

<script language="javascript">
function doCommandEditStation(command, action) {
    document.getElementById('commandEditStationId').value = command;
    document.getElementById('actionId').value = action;
    document.getElementById('theForm1').submit();
}
function changeLanguage(command, language, url) {
    document.getElementById('commandId').value = command;
    document.getElementById('languageId').value = language;
    document.getElementById('urlId').value = url;
    document.getElementById('theForm').submit();
}
</script>


</body>
</html>