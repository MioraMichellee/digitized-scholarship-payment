<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.xadmin.etudiant.bean.Etudiant" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des étudiants par niveau et établissement</title>
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
      <a class="nav-item nav-link" href="${pageContext.request.contextPath}/listMontant">Montant</a>
      <a class="nav-item nav-link" href="${pageContext.request.contextPath}/listPayer">Payer <span class="sr-only">(current)</span></a>
    </div>
  </div>
</nav>
<br> <br>
<div class="container">
    <h3 class="text-center">Liste des étudiants par niveau et établissement</h3>
    <table class="table table-bordered">
        <thead style="background-color: #f8f9fa;">
            <tr>
                <th>Matricule</th>
                <th>Nom</th>
                <th>Sexe</th>
                <th>Date de naissance</th>
                <th>Institution</th>
                <th>Niveau</th>
                <th>Email</th>
                <th>Année Universitaire</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Etudiant> listEtudiants = (List<Etudiant>) request.getAttribute("listEtudiant");
                if (listEtudiants != null && !listEtudiants.isEmpty()) {
                    for (Etudiant etudiant : listEtudiants) {
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
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="8" class="text-center">Aucun étudiant trouvé</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</div>
</body>
</html>
