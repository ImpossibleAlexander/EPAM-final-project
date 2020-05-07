<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename='property' />

<!DOCTYPE html>
<html>
<head>
<title>Registration</title>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"
	charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="resources/style/style.css" />
<link rel="stylesheet" type="text/css"
	href="resources/style/registration.css" />
</head>
<body>

	<form action="controller" method="post" class="registration-form">
		<input type="hidden" name="command" value="registerUser">
		<h1>
			<fmt:message key='register' />
		</h1>
		<div class="txtb">
			<input type="text" name="login" required 
				oninvalid="this.setCustomValidity('<fmt:message key='username_empty'/>')"
				oninput="setCustomValidity('')" /> <span
				data-placeholder="<fmt:message key='username'/>"></span>
		</div>
		<div class="txtb">
			<input type="email" name="email" required /> <span
				data-placeholder="email"></span>
		</div>
		<div class="txtb">
			<input type="password" name="password" required
				oninvalid="this.setCustomValidity('<fmt:message key='password_empty'/>')"
				oninput="setCustomValidity('')" /> <span
				data-placeholder="<fmt:message key='password'/>"></span>
		</div>
		<div class="txtb">
			<input type="password" name="passwordConfirm" required
				oninvalid="this.setCustomValidity('<fmt:message key='password_empty'/>')"
				oninput="setCustomValidity('')" /> <span
				data-placeholder="<fmt:message key='password_confirm'/>"></span>
		</div>
		<div class="txtb">
			<input name="surname" type="text" size="35" maxlength="35" required />
			<span data-placeholder="<fmt:message key='last_name'/>"></span>
		</div>
		<div class="txtb">
			<input name="name" type="text" size="35" maxlength="35" required />
			<span data-placeholder="<fmt:message key='first_name'/>"></span>
		</div>
		<input type="submit" class="regbtn"
			value="<fmt:message key='register_user'/>">
		<div class="bottom-text">
			<a href="javascript:history.back()"><fmt:message
					key='history_back' /></a>
		</div>
	</form>
	<script type="text/javascript">
		$(".txtb input").on("focus", function() {
			$(this).addClass("focus");
		});

		$(".txtb input").on("blur", function() {
			if ($(this).val() == "")
				$(this).removeClass("focus");
		});
	</script>
</body>
</html>