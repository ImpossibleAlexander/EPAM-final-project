<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <title>Login</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="resources/style/style.css" />
    <link rel="stylesheet" type="text/css" href="resources/style/login.css" />
  </head>

  <body>
		<form action="controller" method="post"  class="login-form">
		<input type="hidden" name="command" value="login">
        <h1>Login</h1>

        <div class="txtb">
          <input type="text" name="login" required>
          <span data-placeholder="Username"></span>
        </div>

        <div class="txtb">
          <input type="password" name="password" required>
          <span data-placeholder="Password"></span>
        </div>
        <input type="submit" class="logbtn" value="Sign In">

        <div class="bottom-text">
          Don't have account? <a href="registration.jsp">Sign up</a>
        </div>  
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

