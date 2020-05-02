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
	<c:set var="route" scope="page" value="${route}"/>
	<nav>
		<ul>
			<li>
				<a class="active" href="#">Расписание</a>
			</li>
			<li>
				<a href="main_page.jsp">Контакты</a>
			</li>
			<li>	
       				 <a href="controller?command=mapping&page=admin">Редактировать</a>
			</li>	
			<li style="float: right">
				<c:choose>
  			  	<c:when test="${userRole == 'ADMIN'}">
       				<a href="controller?command=logout">Выйти</a>
    			</c:when>
   			 	<c:otherwise>
        			<a href="login.jsp">Войти</a>
   				 </c:otherwise>
			</c:choose>
			</li>		
		</ul>
	</nav>
	
	
	<div>
		<table border="1" width="200px" cellpadding="5" align="right" style="background-color: white">
							<tr>
								<th><h3>Станции</h3></th>
								<th><h3>Редактировать</h3></th>
							</tr>
							<c:forEach var="station" items="${trainStationBean.getStations()}">
			
								<tr>
									<td>${station.stationName}</td>
										<c:choose>
			  			  					<c:when test="${userRole == 'ADMIN'}">
			  			  					<td>
			       								<form action="controller" method="get">
													<input type="hidden" name="command" value="routeDetails">
													<input type="hidden" name="stationName" value="${station.stationName}">
													<input type="submit" value="Выбрать" class="button-accept">																														
												</form>
												</td>
			    							</c:when>
			    						</c:choose>							
								</tr>
							</c:forEach>
				</table>
	</div>
	
	<div>
		<div style="width: 450px">
       				 <form action="controller" method="post" id="theForm1">
       				 	<input type="hidden" name="command" id="commandId" value="">
						<input type="hidden" name="trainID" value="${route.trainId}" >		
						<fieldset style="background-color: white">
						<legend><h3>Добавление/Редактирование поезда</h3></legend>
							<legend>
								№ поезда: <input type="text" name="trainNumber" required value="${route.trainNumber}" pattern="^\d+$"/>
							</legend>
							<legend>
								Станция отправления: <input type="text" name="stationName" required value="${route.stationName}"/>
							</legend>
							<legend>
								Дата/Время отправления: <input type="datetime"  name="departureDateAndTime" required value="${route.departureDateAndTime}" 
								placeholder="ГГГГ-ММ-ДД чч:мм" pattern="\d[0-9]\d[0-9]-\d[0-9]-\d[0-9]\s\d[0-9]:\d[0-9]:\d[0-9]"/>
							</legend>
							<legend>
								Станция прибытия: <input type="text" name="destinationStationName" required value="${route.destinationStationName}"/>
							</legend>
							<legend>
								Дата/Время прибытия: <input type="datetime" name="destinationDateAndTime" required value="${route.destinationDateAndTime}" 
								placeholder="ГГГГ-ММ-ДД чч:мм" pattern="\d[0-9]\d[0-9]-\d[0-9]-\d[0-9]\s\d[0-9]:\d[0-9]:\d[0-9]"/>
							</legend>
							<legend>
								Купе (свободно): <input type="text" name="coupe" required value="${route.coupe}"/>
							</legend>
							<legend>
								Плацкарт (свободно): <input type="text" name="reservedSeat" required value="${route.reservedSeat}"/>
							</legend>
							<legend>
								Общий (свободно): <input type="text" name="common" required value="${route.common}"/>
							</legend>
							<legend>
								Стоимость (купе): <input type="text" name="coupePrice" required value="${route.coupePrice}"/>
							</legend>
							<legend>
								Стоимость (плацкарт): <input type="text" name="reservedSeatPrice" required value="${route.reservedSeatPrice}"/>
							</legend>
							<legend>
								Стоимость (общий): <input type="text" name="commonPrice" required value="${route.commonPrice}"/>
							</legend>
							<legend>
								Статус (ACTIVE): <input type="radio" name="trainStatus"  value="active" <c:if test = "${route.trainStatus == 'active'}">checked</c:if>/>
							</legend>
							<legend>
								Статус (CANCELED): <input type="radio" name="trainStatus"  value="canceled" <c:if test = "${route.trainStatus == 'canceled'}">checked</c:if>/>
							</legend>
						</fieldset>
						<input type="button" name="create" value="Create" class="button-accept"  onclick="doCommand('createRouteCommand')">
						<input type="button" name="update" value="Update" class="button-accept"  onclick="doCommand('updateTrain')">
					</form>
			</div>

	<div style="width: 450px">
	<form action="controller" method="get">
		<input type="hidden" name="command" value="route">
		<fieldset style="background-color: white">
			<legend><h3>Поиск поезда</h3></legend>
			<legend>
				№ поезда: <input type="text" name="trainNumber" />
			</legend>
			<legend>
				Станция отправления: <input type="text" name="departureStation" />
			</legend>
			<legend>
				Станция прибытия: <input type="text" name="arrivalStation" />
			</legend>
		</fieldset>
		<br /> <input type="submit" value="Найти" class="button-accept">
	</form>
	</div>
	<c:choose>
		<c:when test="${empty routes}"/> 
		<c:otherwise>
			<table border="1" width="10%" cellpadding="5" style="background-color: white">
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
					<th>Статус поезда</th>
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
								<input type="submit" value="Подробнее" class="button-accept">
							</form>
						</td>

		  			  			<td>
								<form action="controller" method="get">
									<input type="hidden" name="command" value="setValuesForRouteUpdate">
									<input type="hidden" name="trainId" value="${route.trainId}">
									<input type="hidden" name="trainNumber" value="${route.trainNumber}">
									<input type="hidden" name="departureStation" value="${route.stationName}">
									<input type="hidden" name="departureDateAndTime" value="${route.departureDateAndTime}">
									<input type="hidden" name="destinationStationName" value="${route.destinationStationName}">
									<input type="hidden" name="destinationDateAndTime" value="${route.destinationDateAndTime}">
									
									<input type="hidden" name="coupe" value="${route.coupe}">
									<input type="hidden" name="reservedSeat" value="${route.reservedSeat}">
									<input type="hidden" name=common value="${route.common}">
									
									<input type="hidden" name="coupePrice" value="${route.coupePrice}">
									<input type="hidden" name="reservedSeatPrice" value="${route.reservedSeatPrice}">
									<input type="hidden" name="commonPrice" value="${route.commonPrice}">
									
									<input type="hidden" name="trainStatus" value="${route.trainStatus}">
												
									<input type="submit" value="Выбрать" class="button-accept">
								</form>
								</td>
								</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	</div>
	
	<script language="javascript">
function doCommand(command) {
    document.getElementById('commandId').value = command;
    document.getElementById('theForm1').submit();
}
</script>
	
</body>
</html>