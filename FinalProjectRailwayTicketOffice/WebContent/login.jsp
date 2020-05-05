<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	
	<fmt:setLocale value="${sessionScope.language}"/>
	<fmt:setBundle basename='property'/>
	
	
<!DOCTYPE html>
<html>
  <head>
    <title>Login</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="resources/style/style.css" />
    <link rel="stylesheet" type="text/css" href="resources/style/login.css" />
  </head>

  <body>
		<form action="controller" method="post"  class="login-form">
				<input type="hidden" name="command" value="login">
		        <h1><fmt:message key='login_h1'/></h1>
		
		        <div class="txtb">
		          <input type="text" name="login" required oninvalid="this.setCustomValidity('Please Enter valid email')">
		          <span data-placeholder="<fmt:message key='username'/>"></span>
		        </div>
		
		        <div class="txtb">
		          <input type="password" name="password" required oninvalid="this.setCustomValidity('Заполните поле')">
		          <span data-placeholder="<fmt:message key='password'/>"></span>
		        </div>
		        
		        <input type="submit" class="logbtn" value="<fmt:message key='sign_in'/>">
		
		        <div class="bottom-text">
		           <fmt:message key='new_user'/>   <a href="registration.jsp"><fmt:message key='sign_up'/></a>
		        </div>  
		        
		        <div class="bottom-text">
		        	<a href="javascript:history.back()"><fmt:message key='history_back'/></a>
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

