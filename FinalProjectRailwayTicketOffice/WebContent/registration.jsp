<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
 	<title>Registration</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="resources/style/style.css" />
    <link rel="stylesheet" type="text/css" href="resources/style/registration.css" />
</head>
<body>
	
		<form action="controller" method="post"  class="registration-form">
		<input type="hidden" name="command" value="registerUser">
        <h1>Регистрация</h1>
		<div class="txtb">
          <input type="text" name="login" required/>
          <span data-placeholder="Username"></span>
        </div>
		<div class="txtb">
          <input type="email" name="email" required/>
          <span data-placeholder="Email"></span>
        </div>
        <div class="txtb">
          <input type="password" name="password" required/>
          <span data-placeholder="Password"></span>
        </div>
		 <div class="txtb">
          <input type="password" name="passwordConfirm" required/>
          <span data-placeholder="Password"></span>
        </div>
		<div class="txtb">
         <input name="surname" type="text" size="35" maxlength="35" required/>
          <span data-placeholder="Прізвище"></span>
        </div>
		<div class="txtb">
         <input name="name" type="text" size="35" maxlength="35" required />
          <span data-placeholder="Ім'я"></span>
        </div>
        <input type="submit" class="regbtn" value="Регистрация">
        <div class="bottom-text">
        	<a href="javascript:history.back()">вернуться</a>
        </div>	
		</form>
 		<script type="text/javascript">
      	$(".txtb input").on("focus",function(){
        $(this).addClass("focus");
     	});

      	$(".txtb input").on("blur",function(){
        if($(this).val() == "")
        $(this).removeClass("focus");
      	});
      </script>
</body>
</html>