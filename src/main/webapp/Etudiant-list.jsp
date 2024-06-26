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
<div class="container">
    <h3 class="text-center">Liste des étudiants</h3>
    <a href="${pageContext.request.contextPath}/new" class="btn btn-success">Ajouter étudiants</a>
    <table class="table table-bordered">
        <thead>
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
                    <a href="${pageContext.request.contextPath}/edit?id=<%= etudiant.getMatricule() %>">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/delete?id=<%= etudiant.getMatricule() %>">Delete</a>
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
