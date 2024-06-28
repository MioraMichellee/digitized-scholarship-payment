<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.xadmin.periodePayement.bean.PeriodePayement" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>Modifier les périodes de paiement</title>
</head>
<body>
    <header>
        <nav class="navbar navbar-expand-md navbar-dark" style="background-color:blue">
            <div>
                <a href="#" class="navbar-brand">Application Bourse</a>
            </div>
            <ul class="navbar-nav">
                <li><a href="listPayer" class="nav-link">Liste des périodes de paiement</a></li>
            </ul>
        </nav>
    </header>
    <br>
    <div class="container col-md-5">
        <div class="card">
            <div class="card-body">
                <% 
                PeriodePayement periodePayement = (PeriodePayement) request.getAttribute("periodePayement"); 

                SimpleDateFormat inputFormat = new SimpleDateFormat("MM-dd-yyyy");
                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

                Date dateDebut = inputFormat.parse(periodePayement.getDateDebut());
                Date dateFin = inputFormat.parse(periodePayement.getDateFin());

                String formattedDateDebut = outputFormat.format(dateDebut);
                String formattedDateFin = outputFormat.format(dateFin);
                %>
                <form action="updatePeriodePayement" method="post">
                    <input type="hidden" name="idPeriode" value="<%= periodePayement.getIdPeriode() %>"/>
                    <h2>Modifier période de paiement</h2>
                    <fieldset class="form-group">
                        <label>Tranche</label>
                        <select name="tranche" class="form-control" required="required">
                            <option value="1ere" <%= periodePayement.getTranche().equals("1ere") ? "selected" : "" %>>1ère tranche</option>
                            <option value="2eme" <%= periodePayement.getTranche().equals("2eme") ? "selected" : "" %>>2ème tranche</option>
                            <option value="3eme" <%= periodePayement.getTranche().equals("3eme") ? "selected" : "" %>>3ème tranche</option>
                        </select>
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Date de début</label>
                        <input type="date" name="dateDebut" class="form-control" value="<%= formattedDateDebut %>" required="required">
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Date de fin</label>
                        <input type="date" name="dateFin" class="form-control" value="<%= formattedDateFin %>" required="required">
                    </fieldset>
                    <button type="submit" class="btn btn-success">Enregistrer</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
