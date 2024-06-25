
package com.xadmin.etudiant.dao;

import com.xadmin.etudiant.bean.Etudiant;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDao {

    private String jdbcURL = "jdbc:mysql://localhost:3306/bourse";
    private String jdbcUsername = "root";
    private String jdbcPassword = "mdp";
//    private String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    private static final String INSERT_ETUDIANT_SQL = "INSERT INTO etudiant " + "(nom, sexe, datenais, institution, niveau, mail, anneeuniv) VALUES" + "(?,?,?,?,?,?,?);";
    private static final String SELECT_ETUDIANT_BY_ID = "SELECT* FROM etudiant where matricule = ?;";
 
    private static final String SELECT_ALL_ETUDIANT = "SELECT * FROM etudiant;";

    private static final String DELETE_ETUDIANT_SQL = "DELETE FROM etudiant WHERE matricule = ? ;";
    private static final String UPDATE_ETUDIANT_SQL = "UPDATE etudiant set nom= ? , sexe= ?, datenais= ?, institution= ?, niveau=?, mail=?, anneeuniv=? WHERE matricule=?;";

    public EtudiantDao() {
    }

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

    //insert
    public void insertEtudiant(Etudiant etudiant) {
        System.out.println(INSERT_ETUDIANT_SQL);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ETUDIANT_SQL)) {
            preparedStatement.setString(1, etudiant.getNom());
            preparedStatement.setString(2, etudiant.getSexe());
            preparedStatement.setString(3, etudiant.getDateNais());
            preparedStatement.setString(4, etudiant.getInstitution());
            preparedStatement.setString(5, etudiant.getNiveau());
            preparedStatement.setString(6, etudiant.getMail());

            preparedStatement.setString(7, etudiant.getAnneeUniv());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("Message:" + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause:" + t);
                    t = t.getCause();
                }
            }
        }
    }

    //    select
    public Etudiant selectEtudiant(int matricule) {
        Etudiant etudiant = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ETUDIANT_BY_ID);) {
            preparedStatement.setInt(1, matricule);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("nom");
                String sexe = rs.getString("sexe");
                String dateNais = rs.getString("dateNais");
                String institution = rs.getString("institution");
                String niveau = rs.getString("niveau");
                String mail = rs.getString("mail");
                String anneUniv = rs.getString("anneeUniv");
                etudiant = new Etudiant(matricule, name, sexe, dateNais, institution, niveau, mail, anneUniv);
                System.out.println("selected etudiant"+ etudiant);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return etudiant;
    }


 // select all etudiant
    public List<Etudiant> selectAllEtudiant() {
        List<Etudiant> etudiants = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ETUDIANT);) {
            System.out.println("Executing query: " + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int matricule = rs.getInt("matricule");
                String name = rs.getString("nom");
                String sexe = rs.getString("sexe");
                String dateNais = rs.getString("dateNais");
                String institution = rs.getString("institution");
                String niveau = rs.getString("niveau");
                String mail = rs.getString("mail");
                String anneeUniv = rs.getString("anneeUniv");
                Etudiant etudiant = new Etudiant(matricule, name, sexe, dateNais, institution, niveau, mail, anneeUniv);
                etudiants.add(etudiant);
                System.out.println("Etudiant added: " + etudiant);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        System.out.println("Total etudiants retrieved: " + etudiants.size());
        return etudiants;
    }

    //update etudiant
    public boolean updateEtudiant(Etudiant etudiant){
        boolean rowUpdated = false;
        try(Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ETUDIANT_SQL);){
            System.out.println("updated etudiant:"+ preparedStatement);
            preparedStatement.setString(1, etudiant.getNom());
            preparedStatement.setString(2, etudiant.getSexe());
            preparedStatement.setString(3, etudiant.getDateNais());
            preparedStatement.setString(4, etudiant.getInstitution());
            preparedStatement.setString(5, etudiant.getNiveau());
            preparedStatement.setString(6, etudiant.getMail());
            preparedStatement.setString(7, etudiant.getAnneeUniv());
            preparedStatement.setInt(8, etudiant.getMatricule());

            rowUpdated = preparedStatement.executeUpdate() > 0;

        }catch (SQLException e) {
            printSQLException(e);
        }

        return rowUpdated;
    }

    //delete etudiant

    public boolean deleteEtudiant (int matricule) throws SQLException{
        boolean rowDeleted = false;
        try (Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_ETUDIANT_SQL);){
            statement.setInt(1,matricule);
            rowDeleted = statement.executeUpdate() >0;
        }
        return rowDeleted;
    }

}
