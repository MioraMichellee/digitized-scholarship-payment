<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.xadmin.periodePayement.bean.PeriodePayement" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
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

                SimpleDateFormat inputFormat = new SimpleDateFormat("MM-dd-yyyy"); // Ajustez le format si nécessaire
                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

                Date dateDebut = inputFormat.parse(periodePayement.getDateDebut());

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dateDebut);
                calendar.add(Calendar.DAY_OF_MONTH, 21); // Ajouter 21 jours (3 semaines)

                Date dateFin = calendar.getTime();

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
                        <input type="date" id="dateDebut" name="dateDebut" class="form-control datepicker" value="<%= formattedDateDebut %>" required="required">
                    </fieldset>
                    <input type="hidden" id="dateFin" name="dateFin" value="<%= formattedDateFin %>">
                    <button type="submit" class="btn btn-success">Enregistrer</button>
                </form>
            </div>
        </div>
    </div>
    
    <script>
        document.getElementById('dateDebut').addEventListener('change', function() {
            var dateDebut = new Date(this.value);
            var dateFin = new Date(dateDebut);
            dateFin.setDate(dateFin.getDate() + 21); // Ajouter 21 jours (3 semaines)

            var year = dateFin.getFullYear();
            var month = ("0" + (dateFin.getMonth() + 1)).slice(-2); // Ajouter 1 car les mois commencent à 0
            var day = ("0" + dateFin.getDate()).slice(-2);

            var formattedDateFin = year + "-" + month + "-" + day;
            document.getElementById('dateFin').value = formattedDateFin;
        });
    </script>
</body>
</html>

