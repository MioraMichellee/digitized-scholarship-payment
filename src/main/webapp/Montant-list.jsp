
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.xadmin.montant.bean.Montant" %>
<%@ page import="com.xadmin.equipement.bean.Equipement" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des étudiants</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">Bourse</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div class="navbar-nav">
      <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/list">Etudiant </a>
      <a class="nav-item nav-link" href="${pageContext.request.contextPath}/listMontant">Montant <span class="sr-only">(current)</span></a>
      <a class="nav-item nav-link" href="${pageContext.request.contextPath}/listPayer">Payer</a>
    </div>
  </div>
</nav>
<br> <br>
<div class="container">
    <h3 class="text-center">Liste des Montants</h3>
    <a href="${pageContext.request.contextPath}/newMontant" class="btn btn-success">Ajouter montants</a>
    <table class="table table-bordered">
        <thead style="background-color: #f8f9fa;">
            <tr>
              	<th>ID</th>
					<th>Niveau</th>
					<th>Montant</th>
					<th>Actions</th>
         
            </tr>
        </thead>
        <tbody>
            <%
                List<Montant> listMontant = (List<Montant>) request.getAttribute("listMontant");
                if (listMontant != null && !listMontant.isEmpty()) {
                    for (Montant montant : listMontant) {
            %>
            <tr>

                <td><%= montant.getIdNiv() %></td>
						<td><%= montant.getNiveau() %></td>
						<td><%= montant.getMontant() %></td>
                <td>
                    <a class="btn btn-info" href="${pageContext.request.contextPath}/editMontant?idNiv=<%= montant.getIdNiv() %>">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a class="btn btn-danger" href="${pageContext.request.contextPath}/deleteM?idNiv=<%= montant.getIdNiv() %>">Delete</a>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="9" class="text-center">Aucun étudiant trouvé</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
    <h3 class="text-center">Equipement</h3>
        <table class="table table-bordered">
        <thead style="background-color: #f8f9fa;">
            <tr>
              	
					
					<th>Montant  Equipement</th>
					<th>Actions</th>
         
            </tr>
        </thead>

		        <tbody>
            <%
                List<Equipement> listEquipement = (List<Equipement>) request.getAttribute("listEquipement");
                if (listEquipement != null && !listEquipement.isEmpty()) {
                    for (Equipement equipement : listEquipement) {
            %>
            <tr>


						<td><%= equipement.getMontantEq() %></td>
                <td>
                    <a href="${pageContext.request.contextPath}/editEquipement?idEq=<%= equipement.getIdEq() %>" class="btn btn-primary">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="9" class="text-center">Aucun étudiant trouvé</td>
            </tr>
            <%
                }
            %>
        </tbody>
		


    </table>
</div>
</body>
</html>


