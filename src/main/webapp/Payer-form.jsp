<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.xadmin.payer.bean.Payer" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
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
					
					<fieldset type= "hidden" class="form-group">
						<label>Annee universitaire</label>
						<select name="anneeUniv" class="form-control" required="required">
							<option value="2023-2024" <%= isUpdate && "2023-2024".equals(payer.getAnneeUniv()) ? "selected" : "" %>>2023-2024</option>
						</select>
					</fieldset>
					<fieldset class="form-group">
						<label>Date de Payement</label>
					<!-- 	<input type="date" name="date" class="form-control datepicker" value="<%= isUpdate ? payer.getDate() : "" %>" required="required"> -->
						<input type="text" id="date" name="date" class="form-control datepicker" value="<%= isUpdate ? payer.getDate() : "" %>" required="required">
					</fieldset>

					<fieldset class="form-group">
						<label>Tranche</label>
						<select name="tranche" class="form-control" required="required">
							<option value="1ere" <%= isUpdate && "1ere".equals(payer.getTranche()) ? "selected" : "" %>>1ere tranche</option>
							<option value="2eme" <%= isUpdate && "2eme".equals(payer.getTranche()) ? "selected" : "" %>>2eme tranche</option>
							<option value="3eme" <%= isUpdate && "3eme".equals(payer.getTranche()) ? "selected" : "" %>>3eme tranche</option>
						</select>
					</fieldset>
					<button type="submit" class="btn btn-success">Enregistrer</button>
				</form>
			</div>
		</div>
	</div>
	<script>
        $(function() {
            $(".datepicker").datepicker({
                dateFormat: "yy/mm/dd"
            });
        });
    </script>
</body>
</html>
    