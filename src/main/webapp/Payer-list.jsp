<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.xadmin.payer.bean.Payer" %>
<%@ page import="com.xadmin.periodePayement.bean.PeriodePayement" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des bourses Payee</title>
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

  
    
     <form action="listRetardataire" method="get" style="width: 37%;display: flex;margin-left: auto;">
   
    <div class="form-group">
        <label for="tranche">Retardataire pour la tranche:</label>
		<fieldset class="form-group">
						
						<select name="tranche" class="form-control" required="required">
							<option value="1ere" >1ere tranche</option>
							<option value="2eme" >2eme tranche</option>
							<option value="3eme">3eme tranche</option>
						</select>
					</fieldset>
    </div>
    <button type="submit" class="btn btn-primary" style="height: 10%; margin: auto;margin-top: 8%;">Rechercher</button>
</form>


<a href="${pageContext.request.contextPath}/newPayer" class="btn btn-success">Ajouter payement</a>

      <%
        List<Payer> listPayer = (List<Payer>) request.getAttribute("listPayer");
        if (listPayer != null && !listPayer.isEmpty()) {
    %>
      
    <h3 class="text-center mt-4">Liste des payers</h3>
    <table class="table table-bordered">
        <thead style="background-color: #f8f9fa;">
           <tr>
           
                <th>ID payement</th>
                <th>Matricule</th>
                <th>Annee universitaire </th>
                <th>Date payement</th>
                <th>Nombre de mois</th>
                <th>Tranche</th>
                
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                for (Payer payer : listPayer) {
            %>
            <tr>
                  <td><%= payer.getIdPaye() %></td>
                <td><%= payer.getMatricule() %></td>
                <td><%= payer.getAnneeUniv() %></td>
                <td><%= payer.getDate() %></td>
                <td><%= payer.getNbMois() %></td>
                <td><%=payer.getTranche() %></td>
                
                <td>
                    <a class="btn btn-info"href="${pageContext.request.contextPath}/editPayer?idPayer=<%= payer.getIdPaye() %>">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a  class="btn btn-danger" href="${pageContext.request.contextPath}/deletePayer?idPayer=<%= payer.getIdPaye() %>">Delete</a>
                	&nbsp;&nbsp;&nbsp;&nbsp;
                	<a class="btn btn-secondary" href="${pageContext.request.contextPath}/payerServlet?action=generatePdf&idPayer=<%= payer.getIdPaye() %>">Generer PDF</a>
                </td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
    <%
        }
    %>
  
    <%
        List<Payer> listRetardataire = (List<Payer>) request.getAttribute("listRetardataire");
        if (listRetardataire != null && !listRetardataire.isEmpty()) {
    %>
    <h3 class="text-center mt-4">Liste des retardataires</h3>
    <table class="table table-bordered">
        <thead style="background-color: #f8f9fa;">
           <tr>
           
               
                <th>Matricule</th>
                <th>Annee universitaire </th>
                
                <th>Nombre de mois</th>
                <th>Tranche</th>
                
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                for (Payer payer : listRetardataire) {
            %>
            <tr>
                 
                <td><%= payer.getMatricule() %></td>
                <td><%= payer.getAnneeUniv() %></td>
                
                <td><%= payer.getNbMois() %></td>
                <td><%=payer.getTranche() %></td>
                
                <td>
                	<a class="btn btn-secondary"href="${pageContext.request.contextPath}/notifierRetardataires?mail=<%= payer.getDate() %>">Notifier</a>
                </td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
    <%
        }
    %>
    
    <br><br><br><br><br><br>
    
     <h3 class="text-center">Les periodes de payements</h3>
        <table class="table table-bordered">
        <thead style="background-color: #f8f9fa;">
            <tr>
              	
					
					<th>Tranche</th>
					<th>Debut payement</th>
					<th>Fin payement</th>
         
            </tr>
        </thead>

		        <tbody>
            <%
                List<PeriodePayement> listPeriodePayement = (List<PeriodePayement>) request.getAttribute("listPeriodePayement");
                if (listPeriodePayement != null && !listPeriodePayement.isEmpty()) {
                    for (PeriodePayement periodePayement : listPeriodePayement) {
            %>
            <tr>


						<td><%= periodePayement.getTranche() %></td>
						<td><%= periodePayement.getDateDebut() %></td>
						<td><%= periodePayement.getDateFin() %></td>
                <td>
                    <a href="${pageContext.request.contextPath}/editPeriodePayement?idPeriode=<%= periodePayement.getIdPeriode() %>" class="btn btn-primary">Edit</a>
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
    