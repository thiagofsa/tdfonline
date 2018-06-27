<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>


	<c:if test="${not empty msg}">
		    <div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" 
                                aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<strong>${msg}</strong>
		    </div>
		</c:if>
		
		

              <h2>Página de Login </h2>
              <form action="/springtest/efetualogin" method="post">
                  Login: <input type="text" id="login" name="login" /> <br /> 
                  Senha: <input type="password" id="senha" name="senha" /> <br />
                  <input type="submit" value="Entrar no TFD" /> 
              </form>


</body>
</html>