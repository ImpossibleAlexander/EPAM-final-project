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
<body>

	<jsp:useBean id="userOrderBean" scope="application"
	    class="ua.nure.kaplin.SummaryTask4.db.bean.UserOrderBean"/>

	<c:set var="user" scope="page" value="${sessionScope.user}"/>	
	<jsp:setProperty name="userOrderBean" property="user" value="${user}"/>
	
		<nav>
		<ul>
			<li>
				<a href="main_page.jsp">Расписание</a>
			</li>
			<li>
				<a href="main_page.jsp">Контакты</a>
			</li>

    		<li>
       			<a href="controller?command=mapping&page=basket">Корзина</a>
       		</li>
       		<li>
       			<a class="active" href="#">Личный кабинет</a>
       		</li>	
			<li style="float: right">
				<c:choose>
  			  	<c:when test="${userRole == 'CLIENT'}">
       				<a href="controller?command=logout">Выйти</a>
    			</c:when>
   			 	<c:otherwise>
        			<a href="login.jsp">Войти</a>
   				 </c:otherwise>
			</c:choose>
			</li>		
		</ul>
	</nav>
	
	<div style="margin: auto; text-align: center">	
	<c:choose>
		<c:when test="${empty userOrderBean.findUserTickets()}">	
			<h3>Билетов нет</h3>
		</c:when> 
		<c:otherwise>
			<table border="1" width="10%" cellpadding="5" align="center">
				<tr>
					<th>№ поезда</th>
					<th>№ билета</th>
					<th>Станция отправления</th>
					<th>Дата/Время отправления</th>
					<th>Станция прибытия</th>
					<th>Дата/Время прибытия</th>
					<th>Место</th>
					<th>Стоимость</th>
				</tr>
				<c:forEach var="ticket" items="${userOrderBean.findUserTickets()}">
					<tr>
						<td>${ticket.trainNumber}</td>
						<td>${ticket.ticketNumber}</td>
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
								<input type="submit" value="Подробнее" class="button-accept">
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>	
	</div>
</body>
</html>