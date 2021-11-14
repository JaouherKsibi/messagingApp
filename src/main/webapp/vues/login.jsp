<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="webjars/bootstrap/4.0.0-2/css/bootstrap.min.css" rel="stylesheet">
<link href="webjars/font-awesome/4.6.2/css/font-awesome.css" type="text/css" rel="stylesheet"/>
</head>
<body>
	
	<div class="container-fluid ">
   <br><br><br>
    <div class="row mt-2">
      <div class="offset-4 col-4 bg-light border border-primary">
        <h1 style="text-align:center">Messagerie@ENIG</h1>
		<h3 style="text-align:center">Connexion</h3>
        <form:form method="POST" action ="login1" modelAttribute="client">
			<div class="form-group">
				<form:input path="login" class="form-control" type="email" placeholder="login" required="true"/>
				<span class="glyphicon glyphicon-user form-control-feedback"></span>
			</div>
			<div class="form-group">
				<form:input path="password" class="form-control" type="password" placeholder="password" required="true"/>
				<span class="glyphicon glyphicon-user form-control-feedback"></span>
			</div>
			<input type="submit" class="btn btn-primary btn-block mb-1" id="_submit" name="_submit" value="Log in">
		</form:form>
      </div>
      
    </div>
  </div>
	
	
	<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
	    <script src="webjars/bootstrap/4.0.0-2/js/bootstrap.min.js"></script>
</body>
</html>