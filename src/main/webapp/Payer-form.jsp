<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.xadmin.payer.bean.Payer" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>Gestion des Payements</title>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark" style="background-color:blue">
			<div>
				<a href="#" class="navbar-brand">Application Bourse</a>
			</div>
			<ul class="navbar-nav">
				<li><a href="listPayer" class="nav-link">Liste des Payements</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<%
					Payer payer = (Payer) request.getAttribute("payer");
					boolean isUpdate = payer != null;
				%>
				<form action="<%= isUpdate ? "updatePayer" : "insertPayer" %>" method="post">
					<h2><%= isUpdate ? "Modifier Payement" : "Ajouter Payement" %></h2>
					<% if (isUpdate) { %>
						<input type="hidden" name="id" value="<%= payer.getIdPaye() %>"/>
					<% } %>
					<fieldset class="form-group">
						<label>Matricule</label>
						<input type="text" name="matricule" class="form-control" value="<%= isUpdate ? payer.getMatricule() : "" %>" required="required">
					</fieldset>
					<fieldset class="form-group">
						<label>Annee univ</label>
						<input type="text" name="anneeUniv" class="form-control" value="<%= isUpdate ? payer.getAnneeUniv() : "" %>" required="required">
					</fieldset>
					<fieldset class="form-group">
						<label>Date de Payement</label>
						<input type="date" name="date" class="form-control" value="<%= isUpdate ? payer.getDate() : "" %>" required="required">
					</fieldset>
					<fieldset class="form-group">
						<label>Nombre de mois</label>
						<input type="number" name="nbMois" class="form-control" value="<%= isUpdate ? payer.getNbMois() : "" %>" required="required">
					</fieldset>
					<button type="submit" class="btn btn-success">Enregistrer</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
    