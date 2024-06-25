package com.xadmin.equipement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.xadmin.equipement.bean.Equipement;

public class EquipementDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/bourse";
    private String jdbcUsername = "root";
    private String jdbcPassword = "mdp";
//    private String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    private static final String INSERT_EQUIPEMENT_SQL = "INSERT INTO equipement " + "(montantEq) VALUES" + "(?);";
    private static final String SELECT_EQUIPEMENT_BY_ID = "SELECT* FROM equipement where idEq = ?;";
 
    private static final String SELECT_ALL_EQUIPEMENT = "SELECT * FROM equipement;";

    private static final String DELETE_EQUIPEMENT_SQL = "DELETE FROM equipement WHERE idEq = ? ;";
    private static final String UPDATE_EQUIPEMENT_SQL = "UPDATE equipement set montantEq= ? WHERE idEq=?;";

    
    public EquipementDao() {
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
    public void insertEquipement(Equipement equipement) {
        System.out.println(INSERT_EQUIPEMENT_SQL);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EQUIPEMENT_SQL)) {
            preparedStatement.setLong(1, equipement.getMontantEq());
//            preparedStatement.setLong(2, montant.getMontant());
           
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
    public Equipement selectEquipement(int idEq) {
    	Equipement equipement = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EQUIPEMENT_BY_ID);) {
            preparedStatement.setInt(1, idEq);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
//                String niveau = rs.getString("niveau");
                int montantEq = rs.getInt("montantEq") ;
                
               equipement = new Equipement(idEq, montantEq);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return equipement;
    }


 // select all montant
    public List<Equipement> selectAllEquipement() {
        List<Equipement> equipements = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EQUIPEMENT);) {
            System.out.println("Executing query: " + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idEq = rs.getInt("idEq");
//                String niveau = rs.getString("niveau");
                int montantEq = rs.getInt("montantEq");
                Equipement equipement = new Equipement(idEq, montantEq);
                equipements.add(equipement);
                System.out.println("Etudiant added: " + equipement);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        System.out.println("Total etudiants retrieved: " + equipements.size());
        return equipements;
    }

    //update etudiant
    public boolean updateEquipement(Equipement equipement){
        boolean rowUpdated = false;
        try(Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EQUIPEMENT_SQL);){
            System.out.println("updated etudiant:"+ preparedStatement);
//            preparedStatement.setString(1, montant.getNiveau());
            preparedStatement.setInt(1, equipement.getMontantEq());
            preparedStatement.setInt(2, equipement.getIdEq());
           

            rowUpdated = preparedStatement.executeUpdate() > 0;

        }catch (SQLException e) {
            printSQLException(e);
        }

        return rowUpdated;
    }

    //delete etudiant

    public boolean deleteEquipement (int idEq) throws SQLException{
        boolean rowDeleted = false;
        try (Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_EQUIPEMENT_SQL);){
            statement.setInt(1,idEq);
            rowDeleted = statement.executeUpdate() >0;
        }
        return rowDeleted;
    }


}
