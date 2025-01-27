<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.xadmin.etudiant.bean.Etudiant" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<title>Gestion des Etudiants</title>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark" style="background-color:blue">
			<div>
				<a href="#" class="navbar-brand">Application Bourse</a>
			</div>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list" class="nav-link">Etudiant List</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<%
					Etudiant etudiant = (Etudiant) request.getAttribute("etudiant");
					
					boolean isUpdate = etudiant != null;
				%>
				<form action="<%= isUpdate ? "update" : "insert" %>" method="post">
					<h2>
						<%= isUpdate ? "Modifier Etudiant" : "Ajouter Etudiant" %>
						
					</h2>
				
					 <% if (isUpdate) { %>
                        <input type="hidden" name="id" value="<%= etudiant.getMatricule() %>"/>
                    <% } %>
					
					<fieldset class="form-group">
						<label>Nom</label>
						<input type="text" name="nom" class="form-control" value="<%= isUpdate ? etudiant.getNom() : "" %>" required="required">
					</fieldset>
					<fieldset class="form-group">
						<label>Sexe</label>
						<select name="sexe" class="form-control" required="required">
							<option value="M" <%= isUpdate && "M".equals(etudiant.getSexe()) ? "selected" : "" %>>Masculin</option>
							<option value="F" <%= isUpdate && "F".equals(etudiant.getSexe()) ? "selected" : "" %>>Féminin</option>
						</select>
					</fieldset>
					<fieldset class="form-group">
						<label>Date de Naissance</label>
						<!-- <input type="date" name="dateNais" class="form-control" value="<%= isUpdate ? etudiant.getDateNais() : "" %>" required="required"> -->
						<input type="text" id="dateNais" name="dateNais" class="form-control datepicker" value="<%= isUpdate ? etudiant.getDateNais() : "" %>" required="required">
					</fieldset>
					<fieldset class="form-group">
						<label>Institution</label>
						<input type="text" name="institution" class="form-control" value="<%= isUpdate ? etudiant.getInstitution() : "" %>" required="required">
					</fieldset>
					<fieldset class="form-group">
						<label>Niveau</label>
						
						<select name="niveau" class="form-control" required="required">
							<option value="L1" <%= isUpdate && "L1".equals(etudiant.getNiveau()) ? "selected" : "" %>>L1</option>
							<option value="L2" <%= isUpdate && "L2".equals(etudiant.getNiveau()) ? "selected" : "" %>>L2</option>
							<option value="L3" <%= isUpdate && "L3".equals(etudiant.getNiveau()) ? "selected" : "" %>>L3</option>
							<option value="M1" <%= isUpdate && "M1".equals(etudiant.getNiveau()) ? "selected" : "" %>>M1</option>
							<option value="M2" <%= isUpdate && "M2".equals(etudiant.getNiveau()) ? "selected" : "" %>>M2</option>
						</select>
					</fieldset>
					<fieldset class="form-group">
						<label>Mail</label>
						<input type="email" name="mail" class="form-control" value="<%= isUpdate ? etudiant.getMail() : "" %>" required="required">
					</fieldset>
					<fieldset class="form-group">
						<label>Annee universitaire</label>
						<select name="anneeUniv" class="form-control" required="required">
							<option value="2023-2024" <%= isUpdate && "2023-2024".equals(etudiant.getAnneeUniv()) ? "selected" : "" %>>2023-2024</option>
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
