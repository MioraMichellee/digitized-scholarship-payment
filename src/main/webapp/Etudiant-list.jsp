<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.xadmin.etudiant.bean.Etudiant" %>
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
      <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/list">Etudiant <span class="sr-only">(current)</span></a>
      <a class="nav-item nav-link" href="${pageContext.request.contextPath}/listMontant">Montant</a>
      <a class="nav-item nav-link" href="${pageContext.request.contextPath}/listPayer">Payer</a>
        <form action="searchEtudiant" method="get" style="padding-left:880PX">
    <input type="text" name="query" style="height:37px">
    <input class="btn btn-primary" type="submit" value="Rechercher">
</form>

    </div>
  </div>
</nav>
<br> <br>

<div class="container">
    <h3 class="text-center">Liste des étudiants</h3>
    <a href="${pageContext.request.contextPath}/new" class="btn btn-success">Ajouter étudiants</a>
    <a href="${pageContext.request.contextPath}/listMineur" class="btn btn-success">Liste étudiants mineur</a>
    <a href="${pageContext.request.contextPath}/listGrouper" class="btn btn-success">Liste grouper</a>
  
    
    <form action="listEtudiantParNE" method="get" style="width: 37%;display: flex;margin-left: auto;">
    <div class="form-group">
        <label for="niveau">Niveau</label>
        <input type="text" class="form-control" id="niveau" name="niveau" required>
    </div>
    <div class="form-group">
        <label for="etablissement">Établissement</label>
        <input type="text" class="form-control" id="etablissement" name="etablissement" required>
    </div>
    <button type="submit" class="btn btn-primary" style="height: 10%; margin: auto;margin-top: 8%;">Rechercher</button>
</form>
    
    <table class="table table-bordered" style="width: 125%;margin-left: -12%;">
        <thead style="background-color: #f8f9fa;">
            <tr>
                <th>Matricule</th>
                <th>Nom</th>
                <th>Sexe</th>
                <th>Date de naissance</th>
                <th>Institution</th>
                <th>Niveau</th>
                <th>Mail</th>
                <th>Année universitaire</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Etudiant> listEtudiant = (List<Etudiant>) request.getAttribute("listEtudiant");
                if (listEtudiant != null && !listEtudiant.isEmpty()) {
                    for (Etudiant etudiant : listEtudiant) {
            %>
            <tr>
                <td><%= etudiant.getMatricule() %></td>
                <td><%= etudiant.getNom() %></td>
                <td><%= etudiant.getSexe() %></td>
                <td><%= etudiant.getDateNais() %></td>
                <td><%= etudiant.getInstitution() %></td>
                <td><%= etudiant.getNiveau() %></td>
                <td><%= etudiant.getMail() %></td>
                <td><%= etudiant.getAnneeUniv() %></td>
                <td>
                    <a class="btn btn-info" href="${pageContext.request.contextPath}/edit?id=<%= etudiant.getMatricule() %>">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a class="btn btn-danger"href="${pageContext.request.contextPath}/delete?id=<%= etudiant.getMatricule() %>">Delete</a>
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

