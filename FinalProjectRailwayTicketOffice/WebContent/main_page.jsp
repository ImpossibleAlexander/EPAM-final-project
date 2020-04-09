<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>

	<meta charset="UTF-8">
	<title>Расписание</title>
	<link rel="stylesheet" type="text/css"
		href="resources/style/navbar.css" />
	<link rel="stylesheet" type="text/css" href="resources/style/style.css" />
</head>
<body>

	<jsp:useBean id="trainStationBean" scope="application"
    class="ua.nure.kaplin.SummaryTask4.db.bean.TrainStationBean"/>
    
	<c:set var="userRole" scope="page" value="${sessionScope.userRole}"/>
	<nav>
		<ul>
			<li>
				<a href="#">Расписание</a>
			</li>
			<li>
				<a href="main_page.jsp">Контакты</a>
			</li>
			<li>
				<c:choose>
  			  	<c:when test="${userRole == 'ADMIN'}">
       				 <a href="controller?command=mapping&page=admin">Редактировать</a>
    			</c:when>
			</c:choose>
			</li>
			<li style="float: right">
				<c:choose>
  			  	<c:when test="${userRole == 'ADMIN'}">
       				<a href="controller?command=logout">Выйти</a>
    			</c:when>
    			<c:when test="${userRole == 'CLIENT'}">
       				<a href="controller?command=logoutp">Выйти</a>
    			</c:when>
   			 	<c:otherwise>
        			<a href="login.jsp">Войти</a>
   				 </c:otherwise>
			</c:choose>
			</li>		
		</ul>
	</nav>
			<c:choose>
  			  	<c:when test="${userRole == 'ADMIN'}">
       				 <form action="controller" method="post">
						<input type="hidden" name="command" value="editRoute">
						<input type="hidden" name="action" value="createRoute">
						<fieldset>
						<legend><h3>Добавление/Редактирование поезда</h3></legend>
							<legend>
								№ поезда <input type="text" name="trainNumber" />
							</legend>
							<legend>
								Станция отправления: <input type="text" name="stationName" />
							</legend>
							<legend>
								Дата/Время отправления: <input type="text" name="departureDateAndTime" />
							</legend>
							<legend>
								Станция прибытия: <input type="text" name="destinationStationName" />
							</legend>
							<legend>
								Дата/Время прибытия: <input type="text" name="destinationDateAndTime" />
							</legend>
							<legend>
								Купе (свободно): <input type="text" name="coupe" />
							</legend>
							<legend>
								Плацкарт (свободно): <input type="text" name="reservedSeat" />
							</legend>
							<legend>
								Общий (свободно): <input type="text" name="common" />
							</legend>
							<legend>
								Стоимость (купе): <input type="text" name="coupePrice" />
							</legend>
							<legend>
								Стоимость (плацкарт): <input type="text" name="reservedSeatPrice" />
							</legend>
							<legend>
								Стоимость (общий): <input type="text" name="commonPrice" />
							</legend>
						</fieldset>
						<br /> <input type="submit" value="Добавить" class="button-accept">
					</form>
    			</c:when>
			</c:choose>
			
	<form action="controller" method="get">
		<input type="hidden" name="command" value="route">
		<fieldset>
			<legend><h3>Поиск поезда</h3></legend>
			<legend>
				Станция отправления: <input type="text" name="departureStation" />
			</legend>
			<legend>
				Станция прибытия: <input type="text" name="arrivalStation" />
			</legend>
		</fieldset>
		<br /> <input type="submit" value="Найти" class="button-accept">
	</form>
	<c:choose>
		<c:when test="${empty routes}"/> 
		<c:otherwise>
			<table border="1" width="10%" cellpadding="5">
				<tr>
					<th>№ поезда</th>
					<th>Станция отправления</th>
					<th>Дата/Время отправления</th>
					<th>Станция прибытия</th>
					<th>Дата/Время прибытия</th>
					<th>Купе (свободно)</th>
					<th>Плацкарт (свободно)</th>
					<th>Общий (свободно)</th>
					<th>Стоимость (купе)</th>
					<th>Стоимость (плацкарт)</th>
					<th>Стоимость (общий)</th>
				</tr>
				<c:forEach var="route" items="${routes}">

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
						<td>
							<form action="controller" method="get">
								<input type="hidden" name="command" value="routeDetails">
								<input type="hidden" name="trainNumber" value="${route.trainNumber}">
								<input type="hidden" name="departureStation" value="${route.stationName}">
								<input type="hidden" name="destinationStation" value="${route.destinationStationName}">
								<input type="submit" value="Подробнее" class="button-accept">
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	
	
	<table border="1" width="10%" cellpadding="5" class="tableStations">
				<tr>
					<th>Станция</th>
				</tr>
				<c:forEach var="station" items="${trainStationBean.getStations()}">

					<tr>
						<td>${station.stationName}</td>
						<td>
							<c:choose>
  			  					<c:when test="${userRole == 'ADMIN'}">
       								<form action="controller" method="get">
										<input type="hidden" name="command" value="routeDetails">
										<input type="hidden" name="stationName" value="${station.stationName}">
										<input type="submit" value="Редактировать" class="button-accept">																														
									</form>
    							</c:when>
    							<c:otherwise>
        							<form action="controller" method="get">
										<input type="hidden" name="command" value="routeDetails">
										<input type="hidden" name="stationName" value="${station.stationName}">
										<input type="submit" value="Выбрать" class="button-accept">																														
									</form>
   								 </c:otherwise>
    						</c:choose>		
						</td>
					</tr>
				</c:forEach>
			</table>
	
	
</body>
</html>