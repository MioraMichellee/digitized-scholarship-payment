<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.xadmin.payer.bean.Payer" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des bourses Payee</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h3 class="text-center">Liste des bourse payer</h3>
    <a href="${pageContext.request.contextPath}/newPayer" class="btn btn-success">Ajouter payement</a>
    <table class="table table-bordered">
        <thead>
            <tr>
           
                <th>ID payement</th>
                <th>Matricule</th>
                <th>Annee universitaire </th>
                <th>Date</th>
                <th>Nombre de mois</th>
                
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Payer> listPayer = (List<Payer>) request.getAttribute("listPayer");
                if (listPayer != null && !listPayer.isEmpty()) {
                    for (Payer payer : listPayer) {
            %>
            <tr>
           
                <td><%= payer.getIdPaye() %></td>
                <td><%= payer.getMatricule() %></td>
                <td><%= payer.getAnneeUniv() %></td>
                <td><%= payer.getDate() %></td>
                <td><%= payer.getNbMois() %></td>
                
                <td>
                    <a href="${pageContext.request.contextPath}/editPayer?idPayer=<%= payer.getIdPaye() %>">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/deletePayer?idPayer=<%= payer.getIdPaye() %>">Delete</a>
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
    