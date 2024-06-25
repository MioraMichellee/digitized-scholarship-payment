<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.xadmin.montant.bean.Montant" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>Gestion des Montants</title>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark" style="background-color:blue">
			<div>
				<a href="#" class="navbar-brand">Application Bourse</a>
			</div>
			<ul class="navbar-nav">
				<li><a href="listMontant" class="nav-link">Liste des Montants</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<%
					Montant montant = (Montant) request.getAttribute("montant");
					boolean isUpdate = montant != null;
				%>
				<form action="<%= isUpdate ? "updateMontant" : "insertMontant" %>" method="post">
					<h2><%= isUpdate ? "Modifier Montant" : "Ajouter Montant" %></h2>
					<% if (isUpdate) { %>
						<input type="hidden" name="idNiv" value="<%= montant.getIdNiv() %>"/>
					<% } %>
					<fieldset class="form-group">
						<label>Niveau</label>
						<input type="text" name="niveau" class="form-control" value="<%= isUpdate ? montant.getNiveau() : "" %>" required="required">
					</fieldset>
					<fieldset class="form-group">
						<label>Montant</label>
						<input type="number" name="montant" class="form-control" value="<%= isUpdate ? montant.getMontant() : "" %>" required="required">
					</fieldset>
					<button type="submit" class="btn btn-success">Enregistrer</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
