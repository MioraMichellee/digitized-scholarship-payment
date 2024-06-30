
package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.periodePayement.dao.PeriodePayementDao;

import util.EmailSender;

public class NotifierRetardataires {
    private final PeriodePayementDao periodePayementDao = new PeriodePayementDao();
    private final EmailSender emailSender = new EmailSender();

    private String jdbcURL = "jdbc:mysql://localhost:3306/bourse";
    private String jdbcUsername = "root";
    private String jdbcPassword = "mdp";
    
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
    
    public void notifierRetardataires() {
        String query = "SELECT e.matricule, e.nom, e.sexe, e.institution, e.niveau, e.mail, e.anneeUniv, pp.tranche " +
                       "FROM etudiant e " +
                       "JOIN periodePayement pp " +
                       "LEFT JOIN payer p ON e.matricule = p.matricule " +
                       "AND e.anneeUniv = p.anneeUniv " +
                       "AND pp.tranche = p.tranche " +
                       "WHERE (p.date IS NULL OR p.date > pp.dateFin) AND pp.dateFin < NOW() - INTERVAL 3 WEEK;";

        try (Connection connection = getConnection();
            	
//        		Connection connection = periodePayementDao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet rs = preparedStatement.executeQuery()) {

            List<String> emails = new ArrayList<>();
            while (rs.next()) {
                String email = rs.getString("mail");
                emails.add(email);
            }

            for (String email : emails) {
                String subject = "Notification de retard de paiement";
                String message = "Cher étudiant,\n\nVous avez un retard de paiement de plus de trois semaines. Veuillez régulariser votre situation dans les plus brefs délais.\n\nCordialement,\nAdministration";
                emailSender.sendEmail(email, subject, message);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

