package com.xadmin.payer.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.payer.bean.Payer;

public class PayerDao {


    private String jdbcURL = "jdbc:mysql://localhost:3306/bourse";
    private String jdbcUsername = "root";
    private String jdbcPassword = "mdp";
//    private String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    private static final String INSERT_PAYER_SQL = "INSERT INTO payer " + "(matricule, anneeUniv, date, nbrMois) VALUES" + "(?,?,?,?);";
    private static final String SELECT_PAYER_BY_ID = "SELECT* FROM payer where idPayer = ?;";
 
    private static final String SELECT_ALL_PAYER = "SELECT * FROM payer;";

    private static final String DELETE_PAYER_SQL = "DELETE FROM payer WHERE idPayer = ? ;";
    private static final String UPDATE_PAYER_SQL = "UPDATE payer set matricule= ? , anneeUniv= ?, date= ?, nbrMois= ? WHERE idPayer=?;";

    public PayerDao() {
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
    public void insertPayer(Payer payer) {
        System.out.println(INSERT_PAYER_SQL);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PAYER_SQL)) {
            preparedStatement.setInt(1, payer.getMatricule());
            preparedStatement.setString(2, payer.getAnneeUniv());
            preparedStatement.setString(3, payer.getDate());
            preparedStatement.setInt(4, payer.getNbMois());
       
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
    public Payer selectPayer(int idPayer) {
        Payer payer = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PAYER_BY_ID);) {
            preparedStatement.setInt(1, idPayer);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int matricule = rs.getInt("matricule");
                String anneeUniv = rs.getString("anneeUNiv");
                String date = rs.getString("date");
                int nbMois = rs.getInt("nbrMois");
              
                payer = new Payer(idPayer,matricule, anneeUniv, date, nbMois);
                System.out.println("selected payement"+ payer);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return payer;
    }


 // select all etudiant
    public List<Payer> selectAllPayer() {
        List<Payer> payers = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PAYER);) {
            System.out.println("Executing query: " + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idPayer = rs.getInt("idPayer");
                int matricule = rs.getInt("matricule");
                String anneeUniv = rs.getString("anneeUNiv");
                String date = rs.getString("date");
                int nbMois = rs.getInt("nbrMois");
                Payer payer = new Payer(idPayer, matricule,  anneeUniv, date, nbMois);
                payers.add(payer);
                System.out.println("Etudiant added: " + payer);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        System.out.println("Total etudiants retrieved: " + payers.size());
        return payers;
    }

    //update etudiant
    public boolean updatePayer(Payer payer){
        boolean rowUpdated = false;
        try(Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PAYER_SQL);){
            System.out.println("updated etudiant:"+ preparedStatement);
            preparedStatement.setInt(1, payer.getMatricule());
            preparedStatement.setString(2, payer.getAnneeUniv());
            preparedStatement.setString(3, payer.getDate());
            preparedStatement.setInt(4, payer.getNbMois());
          
            preparedStatement.setInt(5, payer.getIdPaye());

            rowUpdated = preparedStatement.executeUpdate() > 0;

        }catch (SQLException e) {
            printSQLException(e);
        }

        return rowUpdated;
    }

    //delete etudiant

    public boolean deletePayer (int idPaye) throws SQLException{
        boolean rowDeleted = false;
        try (Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_PAYER_SQL);){
            statement.setInt(1,idPaye);
            rowDeleted = statement.executeUpdate() >0;
        }
        return rowDeleted;
    }

}
