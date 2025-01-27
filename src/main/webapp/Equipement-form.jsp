<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.xadmin.equipement.bean.Equipement" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title> Modifier le montant de l'equipent</title>
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
				<%Equipement equipement = (Equipement) request.getAttribute("equipement"); %>
				<form action= "updateEquipement"  method="post">
				
					<h2>Modifier equipement" </h2>
					<fieldset class="form-group">
						<input type="hidden" name="idEq" value="<%= equipement.getIdEq() %>"/>
						<label>Equipement</label>
						<input type="number" name="montantEq" class="form-control" value="<%= equipement.getMontantEq() %>" required="required">
					</fieldset>
					<button type="submit" class="btn btn-success">Enregistrer</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
