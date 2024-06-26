package com.xadmin.montant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.xadmin.montant.bean.Montant;

public class MontantDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/bourse";
    private String jdbcUsername = "root";
    private String jdbcPassword = "mdp";
//    private String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    private static final String INSERT_MONTANT_SQL = "INSERT INTO montant " + "(niveau,montant) VALUES" + "(?,?);";
    private static final String SELECT_MONTANT_BY_ID = "SELECT* FROM montant where idNiv = ?;";
    private static final String SELECT_MONTANT_NIVEAU = "SELECT* FROM montant where niveau = ?;";
    
 
    private static final String SELECT_ALL_MONTANT = "SELECT * FROM montant;";

    private static final String DELETE_MONTANT_SQL = "DELETE FROM montant WHERE idNiv = ? ;";
    private static final String UPDATE_MONTANT_SQL = "UPDATE montant set niveau= ? , montant= ? WHERE idNiv=?;";

    
    public MontantDao() {
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
    public void insertMontant(Montant montant) {
        System.out.println(INSERT_MONTANT_SQL);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MONTANT_SQL)) {
            preparedStatement.setString(1, montant.getNiveau());
            preparedStatement.setLong(2, montant.getMontant());
           
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
    public Montant selectMontant(int idNiv) {
    	Montant montant = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MONTANT_BY_ID);) {
            preparedStatement.setInt(1, idNiv);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String niveau = rs.getString("niveau");
                int montantValue = rs.getInt("montant") ;
                
                montant = new Montant(idNiv, niveau, montantValue);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return montant;
    }

    public Montant selectMontantByNiveau(String niveau) {
    	Montant montant = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MONTANT_NIVEAU);) {
            preparedStatement.setString(1, niveau);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
//                String niveau = rs.getString("niveau");
                int montantValue = rs.getInt("montant") ;
                
                montant = new Montant(niveau, montantValue);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return montant;
    }

 // select all montant
    public List<Montant> selectAllMontant() {
        List<Montant> montants = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MONTANT);) {
            System.out.println("Executing query: " + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idNiv = rs.getInt("idNiv");
                String niveau = rs.getString("niveau");
                int montantValue = rs.getInt("montant");
                Montant montant = new Montant(idNiv, niveau, montantValue);
                montants.add(montant);
                System.out.println("Etudiant added: " + montant);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        System.out.println("Total etudiants retrieved: " + montants.size());
        return montants;
    }

    //update etudiant
    public boolean updateMontant(Montant montant){
        boolean rowUpdated = false;
        try(Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MONTANT_SQL);){
            System.out.println("updated etudiant:"+ preparedStatement);
            preparedStatement.setString(1, montant.getNiveau());
            preparedStatement.setInt(2, montant.getMontant());
            preparedStatement.setInt(3, montant.getIdNiv());
           

            rowUpdated = preparedStatement.executeUpdate() > 0;

        }catch (SQLException e) {
            printSQLException(e);
        }

        return rowUpdated;
    }

    //delete etudiant

    public boolean deleteMontant (int idNiv) throws SQLException{
        boolean rowDeleted = false;
        try (Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_MONTANT_SQL);){
            statement.setInt(1,idNiv);
            rowDeleted = statement.executeUpdate() >0;
        }
        return rowDeleted;
    }


}
