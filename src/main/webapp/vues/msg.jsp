<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="../webjars/bootstrap/4.0.0-2/css/bootstrap.min.css" rel="stylesheet">
<link href="../webjars/font-awesome/4.6.2/css/font-awesome.css" type="text/css" rel="stylesheet"/>
</head>
<body>




<div class="container">

    <div class="row" style="margin-top:10px;">
        <div class="col-12">
            <div class="card bg-primary text-white">
                <div class="card-header">
                    <h1>Boite de Messagerie @ENIG</h1><br/>
                </div>
            </div>
        </div>
    </div>

    <div class="row" style="margin-top:10px;">
        <div class="col">
            <div class="alert alert-info">
                <h5>Bienvenue Mr ${sessionScope.client1.firstName } <a href="../logout">(se déconnecter)</a></h5>
            </div>
        </div>
    </div>


    <div class="row" style="margin-top:5px;">
       <div class="col-3">
            <div class="list-group">
                <a href="../home" class="list-group-item list-group-item-action active">
                    <span class="fa fa-envelope"></span>
                    Messages réçus
                    <span class="badge badge-pill badge-warning"> ${receidNb }</span>
                </a>
                <a href="../allSentMessages" class="list-group-item list-group-item-action">
                    <span class="fa fa-paper-plane"></span>
                    Messages envoyés
                    <span class="badge badge-pill badge-info">${sentNb }</span>
                </a>
                <a href="../sendMessage" class="list-group-item list-group-item-action">
                    <span class="fa fa-plus"></span>
                    envoyer message
                </a>
            </div>
        </div>

        <div class="col-9">
            
            <div class="alert alert-warning">
                <h3> De : ${ msg.sender.firstName } </h3>
                <h5> Sujet :  ${msg.subject }</h5>
                <h6> Date :  ${msg.date } </h6>
            </div>
            <div class="alert alert-info">
                <p> ${msg.contenu }</p>
                

            </div>


        </div>

    </div>

</div>





<script src="../webjars/jquery/1.9.1/jquery.min.js"></script>
	    <script src="../webjars/bootstrap/4.0.0-2/js/bootstrap.min.js"></script>
</body>
</html>