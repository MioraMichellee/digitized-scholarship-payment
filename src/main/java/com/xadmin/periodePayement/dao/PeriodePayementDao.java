package com.xadmin.periodePayement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.xadmin.periodePayement.bean.PeriodePayement;

public class PeriodePayementDao {
	  private String jdbcURL = "jdbc:mysql://localhost:3306/bourse";
	    private String jdbcUsername = "root";
	    private String jdbcPassword = "mdp";
//	    private String jdbcDriver = "com.mysql.cj.jdbc.Driver";

//	    private static final String INSERT_EQUIPEMENT_SQL = "INSERT INTO equipement " + "(montantEq) VALUES" + "(?);";
	    private static final String SELECT_PERIODE_BY_TRANCHE = "SELECT* FROM periodePayement where idPeriode = ?;";
	 
	    private static final String SELECT_ALL_PERIODE = "SELECT * FROM periodePayement;";

//	    private static final String DELETE_EQUIPEMENT_SQL = "DELETE FROM equipement WHERE idEq = ? ;";
	    private static final String UPDATE_PERIODE = "UPDATE periodePayement set tranche=? ,dateDebut= ?, dateFin=? WHERE idPeriode=?;";

	    
	    public PeriodePayementDao() {
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
	    public PeriodePayement selectPeriodePayement(int idPeriode) {
	    	PeriodePayement periodePayement = null;
	        try (Connection connection = getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PERIODE_BY_TRANCHE);) {
	            preparedStatement.setInt(1, idPeriode);
	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
//	                String niveau = rs.getString("niveau");
	                String tranche = rs.getString("tranche") ;
	                String dateDebut = rs.getString("dateDebut");
	                String dateFin = rs.getString("dateFin");
	                
	                
	                periodePayement = new PeriodePayement(idPeriode,tranche ,dateDebut,dateFin);
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return periodePayement;
	    }


	 // select all montant
	    public List<PeriodePayement> selectAllPeriodePayement() {
	        List<PeriodePayement> periodePayements = new ArrayList<>();
	        try (Connection connection = getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PERIODE);) {
	            System.out.println("Executing query: " + preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	                int idPeriode = rs.getInt("idPeriode");
	                String tranche = rs.getString("tranche");
	                String dateDebut = rs.getString("dateDebut");
	                String dateFin = rs.getString("dateFin");
	                
	                PeriodePayement periodePayement = new PeriodePayement(idPeriode, tranche, dateDebut, dateFin);
	                periodePayements.add(periodePayement);

	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        System.out.println("Total etudiants retrieved: " + periodePayements.size());
	        return periodePayements;
	    }

	    //update etudiant
	    public boolean updatePeriodePayement(PeriodePayement periodePayement){
	        boolean rowUpdated = false;
	        try(Connection connection = getConnection();
	        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PERIODE);){
	            System.out.println("updated Periode:"+ preparedStatement);
//	            preparedStatement.setString(1, montant.getNiveau());
	            preparedStatement.setString(1, periodePayement.getTranche());
	            preparedStatement.setString(2, periodePayement.getDateDebut());
	            preparedStatement.setString(3, periodePayement.getDateFin());
	            preparedStatement.setInt(4,periodePayement.getIdPeriode());

	            rowUpdated = preparedStatement.executeUpdate() > 0;

	        }catch (SQLException e) {
	            printSQLException(e);
	        }

	        return rowUpdated;
	    }

	   

}
