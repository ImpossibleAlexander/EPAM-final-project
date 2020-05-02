<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
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
				<a href="controller?command=mapping&page=admin_main_page">Расписание</a>
			</li>
			<li>
				<a href="main_page.jsp">Контакты</a>
			</li>
			<li>
				<c:choose>
  			  	<c:when test="${userRole == 'ADMIN'}">
       				 <a class="active" href="#">Редактировать</a>
    			</c:when>
			</c:choose>
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
	<div style="width: 450px">
		<form action="controller" method="post" id="theForm1">		
			<input type="hidden" name="command" id="commandId" value="">
			<input type="hidden" name="action" id="actionId" value="">
			
			<fieldset style="background-color: white">
			<legend><h3>Редактирование станций</h3></legend>
			<legend>
				Название станции: <input type="text" name="stationName1" value="${oldStationName}"/>
			</legend>
			<legend>
				Новое название станции: <input type="text" name="stationName2"/>
			</legend>
		</fieldset>	
			<input type="button" name="create" value="Добавить" class="button-accept"  onclick="doCommandEditStation('adminEditStation', 'create')">
			<input type="button" name="update" value="Обновить" class="button-accept"  onclick="doCommandEditStation('adminEditStation', 'update')">
			<input type="button" name="delete" value="Удалить" class="button-accept"  onclick="doCommandEditStation('adminEditStation', 'delete')">
		</form>
		</div>
		
		<div style="width: 450px">
		<form action="controller" method="post">		
			<fieldset style="background-color: white">
			<legend><h3>Добавление точки маршрута</h3></legend>
			<legend>
				№ поезда: <input type="text" name="trainNumber" value="${trainNumber}" required  pattern="^\d+$"/>
			</legend>
			<legend>
				Станция отправления: <input type="text" name="stationName" required/>
			</legend>
			<legend>
				Дата/Время прибытия: <input type="datetime-local" name="destinationDateAndTime"/>
			</legend>
			<legend>
				Дата/Время отправления: <input type="datetime-local" name="departureDateAndTime"/>
			</legend>
		</fieldset>	
				<input type="hidden" name="command" value="createRoutePointCommand">			
				<input type="submit" name="create" value="Добавить" class="button-accept">	
		</form>
		</div>
		
		<c:choose>
		<c:when test="${empty routes}"/> 
		<c:otherwise>
		
			<table border="1" width="10%" cellpadding="5" style="background-color: white">
				<tr>
					<th>№ поезда</th>
					<th>Пункт отправления</th>
					<th>Дата/Время прибытия</th>
					<th>Дата/Время отправления</th>
					<th>Пункт назначения</th>
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
								<input type="submit" name="update" value="Обновить" class="button-accept">			
						</td>				
						<td>
								<input type="hidden" name="command" value="deleteStation">		
								<input type="button" name="delete" value="Удалить" class="button-accept">				
						</td>
					</form>
						</tr>
					</c:forEach>
				</table>
		</c:otherwise>
	</c:choose>

<script language="javascript">
function doCommandEditStation(command, action) {
    document.getElementById('commandId').value = command;
    document.getElementById('actionId').value = action;
    document.getElementById('theForm1').submit();
}
</script>


</body>
</html>