
package com.xadmin.etudiant.web;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//import javax.swing.text.Document;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.text.Document;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import service.NotifierRetardataires;
import util.EmailSender;



import com.xadmin.equipement.bean.Equipement;
import com.xadmin.equipement.dao.EquipementDao;
import com.xadmin.etudiant.bean.Etudiant;
import com.xadmin.etudiant.dao.EtudiantDao;
import com.xadmin.montant.bean.Montant;
import com.xadmin.montant.dao.MontantDao;
import com.xadmin.payer.bean.Payer;
import com.xadmin.periodePayement.bean.PeriodePayement;
import com.xadmin.periodePayement.dao.PeriodePayementDao;
//
//import jakarta.servlet.ServletConfig;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * Servlet implementation class EtudiantSERVLET
// */
//public class EtudiantSERVLET extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public EtudiantSERVLET() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see Servlet#init(ServletConfig)
//	 */
//	public void init(ServletConfig config) throws ServletException {
//		// TODO Auto-generated method stub
//	}
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
//
//}
import com.xadmin.payer.dao.PayerDao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
@WebServlet("/")
public class EtudiantSERVLET extends HttpServlet {
    private EtudiantDao etudiantDao;
    private MontantDao montantDao;
    private EquipementDao equipementDao;
    private PayerDao payerDao;
    private PeriodePayementDao periodePayementDao;

    @Override
    public void init() throws ServletException{
        etudiantDao = new EtudiantDao();
        montantDao = new MontantDao();
        equipementDao = new EquipementDao();
        payerDao = new PayerDao();
        periodePayementDao = new PeriodePayementDao();
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
    	String action = req.getServletPath();

        try {
            switch (action) {
                case "/insert":
                    insertEtudiant(req, resp);
                    break;
                case "/update":
                    updateEtudiant(req, resp);
                    break;
                default:
                    doGet(req, resp);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
        
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        System.out.println("Action: " + action);  // Debugging log
        switch (action)
        {
        
        	case "/":
            welcome(req, resp);
            break;
            case "/new":
                showNewForm(req, resp);
                break;
            case "/insert":
                insertEtudiant(req,resp);
                break;
            case "/delete":
                deleteEtudiant(req,resp);
                break;
            case "/edit":
                showEditForm(req,resp);
                break;
            case "/update":
                updateEtudiant(req, resp);
                break;
            case "/list":
                listEtudiant(req, resp);
                break;
                
//                -------------------------------
            case "/newMontant":
                showNewFormMontant(req, resp);
                break;
            case "/insertMontant":
                insertMontant(req,resp);
                break;
            case "/deleteM":
            	System.out.println("HELLO");
                deleteMontant(req,resp);
                break;
            case "/editMontant":
                showEditFormMontant(req,resp);
                break;
            case "/updateMontant":
                updateMontant(req, resp);
                break;
            case "/listMontant":
                listMontant(req, resp);
                break;
                
//                --------------------------------------
            case "/editEquipement":
                showEditFormEquipement(req, resp);
                break;
            case "/updateEquipement":
                updateEquipement(req, resp);
                break;
//                ---------------------------------------
            case "/newPayer":
                showNewFormPayer(req, resp);
                break;
            case "/insertPayer":
                insertPayer(req,resp);
                break;
            case "/deletePayer":
            	System.out.println("HELLO");
                deletePayer(req,resp);
                break;
            case "/editPayer":
                showEditFormPayer(req,resp);
                break;
            case "/updatePayer":
            	System.out.println("ICI "); 
                updatePayer(req, resp);
                break;
            case "/listPayer":
            	   
                listPayer(req, resp);
                           
                break;
            case "/payerServlet":
            	generatePdf(req, resp);
            	break;
            	
//            	----------------------------------------------
            case "/searchEtudiant":
            	searchEtudiant(req,resp);
            	break;
            case "/listMineur":
            	listMineur(req,resp);
            	break;
            	
            case "/listEtudiantParNE":
                listEtudiantsParNE(req, resp);
                break;
//                --------------------------------------------------
            case "/editPeriodePayement":
            	showEditFormPeriodePayememnt(req, resp);
                break;
            case "/updatePeriodePayement":
                updatePeriodePayement(req, resp);
                break;
//            ------------------------------------------------------
            case "/listRetardataire":
                listRetardataire(req, resp);
                break;
//                ------------------------------------------------
            case "/notifierRetardataires":
                notifierRetardataires(req, resp);
                break;
        }
    }
    
    private void welcome (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");

    }
    
    public void showNewForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        RequestDispatcher dispatcher = req.getRequestDispatcher("Etudiant-form.jsp");
        dispatcher.forward(req, res);
    }

    // insert etudiant method
    public void insertEtudiant (HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException{
        String nom = req.getParameter("nom");
        String sexe = req.getParameter("sexe");
        String dateNais = req.getParameter("dateNais");
        String institution = req.getParameter("institution");
        String niveau = req.getParameter("niveau");
        String  mail = req.getParameter("mail");
        String  anneeUniv = req.getParameter("anneeUniv");
        Etudiant newEtudiant = new Etudiant(nom, sexe,dateNais,institution,niveau, mail, anneeUniv);

        etudiantDao.insertEtudiant(newEtudiant);
        resp.sendRedirect("list");
    }

    // delete etudiant
    private void deleteEtudiant (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int matricule = Integer.parseInt(req.getParameter("id"));
        try{
            etudiantDao.deleteEtudiant(matricule);
        } catch (Exception e){
            e.printStackTrace();
        }
        resp.sendRedirect("list");
    }

    //edit etudiant
    public void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int matricule = Integer.parseInt(req.getParameter("id"));
        Etudiant existingEtudiant;
        try {
        	existingEtudiant = etudiantDao.selectEtudiant(matricule);
            System.out.println("Etudiant a modifier" + matricule);
            RequestDispatcher dispatcher = req.getRequestDispatcher("Etudiant-form.jsp");
            req.setAttribute("etudiant", existingEtudiant);
            dispatcher.forward(req, resp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // update
    public void updateEtudiant(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int matricule = Integer.parseInt(req.getParameter("id"));
        
        
        String nom = req.getParameter("nom");
        String sexe = req.getParameter("sexe");
        String dateNais = req.getParameter("dateNais");
        String institution = req.getParameter("institution");
        String niveau = req.getParameter("niveau");
        String  mail = req.getParameter("mail");
        String  anneeUniv = req.getParameter("anneeUniv");
        Etudiant etudiant = new Etudiant(matricule,nom, sexe,dateNais,institution,niveau, mail, anneeUniv);
        

        System.out.println("TY LE ETUDIANT UPDATE"+ etudiant );
        
        etudiantDao.updateEtudiant(etudiant);
        resp.sendRedirect("list");
    }

    private void listEtudiant(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Etudiant> listEtudiant = etudiantDao.selectAllEtudiant();
            System.out.println("Nombre d'étudiants: " + listEtudiant);  // Debugging log
            req.setAttribute("listEtudiant", listEtudiant);
            RequestDispatcher dispatcher = req.getRequestDispatcher("Etudiant-list.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
//    --------------------------------------------------------------------
//    POUR TABLE MONTANT 
    public void showNewFormMontant(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        RequestDispatcher dispatcher = req.getRequestDispatcher("Montant-form.jsp");
        dispatcher.forward(req, res);
    }

    // insert montant method
    public void insertMontant (HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException{
        String niveau = req.getParameter("niveau");
        int montantValue = Integer.parseInt(req.getParameter("montant"));
    
        Montant newMontant = new Montant(niveau, montantValue);

        montantDao.insertMontant(newMontant);
        resp.sendRedirect("listMontant");
    }

    // delete montant
   

     private void deleteMontant(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	    int idNiv = Integer.parseInt(req.getParameter("idNiv"));
    	    System.out.println("Deleting montant with idNiv: " + idNiv); // Assurez-vous que ce log s'affiche

    	    try {
    	        montantDao.deleteMontant(idNiv);
    	        System.out.println("Montant deleted successfully with idNiv: " + idNiv); // Assurez-vous que ce log s'affiche après la suppression
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        System.err.println("Failed to delete montant with idNiv: " + idNiv);
    	    }

    	    resp.sendRedirect("listMontant");
    	}


    //edit 
    public void showEditFormMontant(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int idNiv = Integer.parseInt(req.getParameter("idNiv"));
        Montant existingMontant;
        try {
            existingMontant = montantDao.selectMontant(idNiv);
            RequestDispatcher dispatcher = req.getRequestDispatcher("Montant-form.jsp");
            req.setAttribute("montant", existingMontant);
            dispatcher.forward(req, resp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // update
    public void updateMontant(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int idNiv = Integer.parseInt(req.getParameter("idNiv"));
        String niveau = req.getParameter("niveau");
        int montantValue = Integer.parseInt(req.getParameter("montant"));
       
        Montant montant = new Montant(idNiv,niveau, montantValue);
        System.out.println("TY LE montant UPDATE"+ montant );
        montantDao.updateMontant(montant);
        resp.sendRedirect("listMontant");
    }


    private void listMontant(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Montant> listMontant = montantDao.selectAllMontant();
            List<Equipement> listEquipement = equipementDao.selectAllEquipement();
            System.out.println("Nombre montant: " + listMontant);  // Debugging log
            System.out.println("Liste eq:"+ listEquipement);
            req.setAttribute("listMontant", listMontant);
            req.setAttribute("listEquipement", listEquipement);
            RequestDispatcher dispatcher = req.getRequestDispatcher("Montant-list.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
//    ---------------------------------------------------
//    pour equipement
    public void showEditFormEquipement(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int idEq = Integer.parseInt(req.getParameter("idEq"));
        Equipement existingEquipement;
        try {
            existingEquipement = equipementDao.selectEquipement(idEq);
            RequestDispatcher dispatcher = req.getRequestDispatcher("Equipement-form.jsp");
            req.setAttribute("equipement", existingEquipement);
            dispatcher.forward(req, resp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void updateEquipement(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int idEq = Integer.parseInt(req.getParameter("idEq"));
//        String niveau = req.getParameter("niveau");
        int montantEq = Integer.parseInt(req.getParameter("montantEq"));
       
        Equipement equipement = new Equipement(idEq, montantEq);
        System.out.println("TY LE montant UPDATE"+ equipement );
        equipementDao.updateEquipement(equipement);
        resp.sendRedirect("listMontant");
    }
    
//    ----------------------------------------------
//    pour payer 
    public void showNewFormPayer(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        RequestDispatcher dispatcher = req.getRequestDispatcher("Payer-form.jsp");
        dispatcher.forward(req, res);
    }

    // insert etudiant method
    public void insertPayer (HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException{
        int matricule = Integer.parseInt(req.getParameter("matricule"));
        String date = req.getParameter("date");
        String  anneeUniv = req.getParameter("anneeUniv");
        int nbMois = 0;
        String tranche = req.getParameter("tranche");
        if (tranche.equals("1ere")) {
        	nbMois = 5;
        }
        else {
        	nbMois = 3;
        }
        
        Payer newPayer = new Payer(matricule,anneeUniv,date,nbMois,tranche);

        payerDao.insertPayer(newPayer);
        resp.sendRedirect("listPayer");
    }

    // delete etudiant
    private void deletePayer (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int idPayer = Integer.parseInt(req.getParameter("idPayer"));
        try{
            payerDao.deletePayer(idPayer);
        } catch (Exception e){
            e.printStackTrace();
        }
        resp.sendRedirect("listPayer");
    }

    //edit etudiant
    public void showEditFormPayer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int idPayer = Integer.parseInt(req.getParameter("idPayer"));
        Payer existingPayer;
        try {
        	existingPayer = payerDao.selectPayer(idPayer);
            System.out.println("Payement a modifier" + existingPayer.getIdPaye());
            RequestDispatcher dispatcher = req.getRequestDispatcher("Payer-form.jsp");
            req.setAttribute("payer", existingPayer);
            dispatcher.forward(req, resp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // update
    public void updatePayer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
       
    	
//    	int idPayer = Integer.parseInt(req.getParameter("id"));
    	String id = req.getParameter("id");
    	int idPayer = Integer.parseInt(id);
    	System.out.println("ity ny idString"+ idPayer);
       
        String matriculeS = req.getParameter("matricule");
        int matricule = Integer.parseInt(matriculeS);
        System.out.println("matricule modifie: "+matricule );
        
        String anneeUniv = req.getParameter("anneeUniv");
        String date = req.getParameter("date");
        
        
        String tranche = req.getParameter("tranche");
        int nbrMois = 0;
        if (tranche.equals("1ere")) {
        	nbrMois = 5;
        }
        else {
        	nbrMois = 3;
        }
//        System.out.println("ito ny idP"+ idP);
      
        Payer payer = new Payer(idPayer,matricule,anneeUniv, date,nbrMois,tranche);
        

        System.out.println("TY LE idpayer a modifier"+ idPayer );
        
        payerDao.updatePayer(payer);
        resp.sendRedirect("listPayer");
    }

    private void listPayer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Payer> listPayer = payerDao.selectAllPayer();
            List<PeriodePayement> listPeriodePayement = periodePayementDao.selectAllPeriodePayement();
            System.out.println("Nombre de payment recu: " + listPayer);  // Debugging log
            req.setAttribute("listPayer", listPayer);
            req.setAttribute("listPeriodePayement", listPeriodePayement);
            RequestDispatcher dispatcher = req.getRequestDispatcher("Payer-list.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void generatePdf(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int idPayer = Integer.parseInt(request.getParameter("idPayer"));
        Payer payer = payerDao.selectPayer(idPayer);
        int matricule = payer.getMatricule();
        Etudiant etudiant = etudiantDao.selectEtudiant(matricule);
        String niveau = etudiant.getNiveau();
        System.out.println(niveau);
        Montant montant = montantDao.selectMontantByNiveau(niveau);
        Equipement equipement = equipementDao.selectEquipement(1);
        String tranche = payer.getTranche();
        int montantEq = equipement.getMontantEq();
        
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=receipt.pdf");

        try {
//            OutputStream out = response.getOutputStream();
//            Document document = new Document();
//            PdfWriter.getInstance(document, out);
//            document.open();
//            document.add(new Paragraph("Receipt for Payment"));
//            document.add(new Paragraph("Date: " + payer.getDate()));
////            document.add(new Paragraph("ID Payement: " + payer.getIdPaye()));
//            document.add(new Paragraph("Matricule: " + payer.getMatricule()));
//            document.add(new Paragraph(etudiant.getNom()));
//            document.add(new Paragraph("Nee le: "+ etudiant.getDateNais()));
//            document.add(new Paragraph("Sexe: "+etudiant.getSexe()));
//            document.add(new Paragraph("Institution: "+ etudiant.getInstitution()));
//            document.add(new Paragraph("Niveau: "+ etudiant.getNiveau()));
//            document.add(new Paragraph("Montant: "+ montant.getMontant()));
//            
//            
//            
//            document.add(new Paragraph("Nombre de Mois: " + payer.getNbMois()));
//            document.close();
        	
        	 OutputStream out = response.getOutputStream();
             Document document = new Document();
             PdfWriter.getInstance(document, out);
             document.open();
             
             // Formatage de la date
             String dateStr = new SimpleDateFormat("dd MMMM yyyy").format(new Date());
             document.add(new Paragraph("Aujourd’hui le " + dateStr));
             
             document.add(new Paragraph("Matricule : " + etudiant.getMatricule()));
             document.add(new Paragraph(etudiant.getNom()));
             document.add(new Paragraph("Née le " + etudiant.getDateNais()));
             document.add(new Paragraph(etudiant.getSexe()));
             document.add(new Paragraph("Institution : " + etudiant.getInstitution() + " / Niveau : " + etudiant.getNiveau()));
             
             // Ajouter un tableau pour les paiements
             PdfPTable table = new PdfPTable(2); // 2 colonnes
             table.setWidthPercentage(100);
             table.setSpacingBefore(10f);
             table.setSpacingAfter(10f);

             // Ajouter les en-têtes du tableau
             Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
             PdfPCell cell1 = new PdfPCell(new Phrase("Mois", boldFont));
             PdfPCell cell2 = new PdfPCell(new Phrase("Montant", boldFont));
             cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
             cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
             table.addCell(cell1);
             table.addCell(cell2);

             int total = 0;

//             for (Payer p : listPayer) {
//                 table.addCell(p.getDate()); // suppose que la date est le mois de paiement
//                 table.addCell(String.valueOf(p.getNbMois() * 23000)); // calcul du montant basé sur le nombre de mois
//                 total += p.getNbMois() * 23000;
//             }
             
             if (tranche.equals("1ere")) {
            	 table.addCell("Equipement");
                 table.addCell(String.valueOf(montantEq));
            	 table.addCell("Janvier");
            	 table.addCell(String.valueOf(montant.getMontant()));
            	 table.addCell("Fevrier");
            	 table.addCell(String.valueOf(montant.getMontant()));
            	 table.addCell("Mars");
            	 table.addCell(String.valueOf(montant.getMontant()));
            	 table.addCell("Avril");
            	 table.addCell(String.valueOf(montant.getMontant()));
            	 total = 4* montant.getMontant() + montantEq;
            	 table.addCell("Total");
            	 table.addCell(String.valueOf(total));
            	}
             else if (tranche.equals("2eme")) {
            	 table.addCell("Mai");
            	 table.addCell(String.valueOf(montant.getMontant()));
            	 table.addCell("Juin");
            	 table.addCell(String.valueOf(montant.getMontant()));
            	 table.addCell("Juillet");
            	 table.addCell(String.valueOf(montant.getMontant()));
            	 
            	 total = 3* montant.getMontant() ;
            	 table.addCell("Total");
            	 table.addCell(String.valueOf(total));
             }
             else {
            	 table.addCell("Aout");
            	 table.addCell(String.valueOf(montant.getMontant()));
            	 table.addCell("Septembre");
            	 table.addCell(String.valueOf(montant.getMontant()));
            	 table.addCell("Octobre");
            	 table.addCell(String.valueOf(montant.getMontant()));
            	 
            	 total = 3* montant.getMontant() ;
            	 table.addCell("Total");
            	 table.addCell(String.valueOf(total));
             }
             
//             total += 110000;

             // Ajouter le tableau au document
             document.add(table);

             // Ajouter le total
             document.add(new Paragraph("Total: " + total + " Ariary"));
             document.add(new Paragraph("Total Payé : " + total + " Ariary"));

             document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
//    -------------------------------------------------------------
    
    private void searchEtudiant(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("query");
        List<Etudiant> listEtudiant = etudiantDao.selectEtudiantLike(query);
        req.setAttribute("listEtudiant", listEtudiant);
        RequestDispatcher dispatcher = req.getRequestDispatcher("Etudiant-list.jsp");
        dispatcher.forward(req, resp);
    }
    
    private void listMineur(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String query = req.getParameter("query");
        List<Etudiant> listEtudiant = etudiantDao.selectMineur();
        req.setAttribute("listEtudiant", listEtudiant);
        RequestDispatcher dispatcher = req.getRequestDispatcher("Etudiant-list.jsp");
        dispatcher.forward(req, resp);
    }
    
    private void listEtudiantsParNE(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String niveau = req.getParameter("niveau");
        String etablissement = req.getParameter("etablissement");

        try {
            List<Etudiant> listEtudiants = etudiantDao.selectEtudiantsByNE(niveau, etablissement);
            req.setAttribute("listEtudiant", listEtudiants);
            RequestDispatcher dispatcher = req.getRequestDispatcher("EtudiantParNE-list.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    ------------------------------------------------------------------------------------
//    pour periode 
    public void showEditFormPeriodePayememnt(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int idPeriode = Integer.parseInt(req.getParameter("idPeriode"));
        PeriodePayement existingPeriodePayement;
        try {
            existingPeriodePayement = periodePayementDao.selectPeriodePayement(idPeriode);
            RequestDispatcher dispatcher = req.getRequestDispatcher("PeriodePayement-form.jsp");
            req.setAttribute("periodePayement", existingPeriodePayement);
            dispatcher.forward(req, resp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void updatePeriodePayement(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int idPeriode = Integer.parseInt(req.getParameter("idPeriode"));
//        String niveau = req.getParameter("niveau");
        String tranche = req.getParameter("tranche");
        String dateDebut = req.getParameter("dateDebut");
        String dateFin = req.getParameter("dateFin");
       
        PeriodePayement periodePayement = new PeriodePayement(idPeriode, tranche, dateDebut, dateFin);
        System.out.println("TY LE montant UPDATE"+ periodePayement );
        periodePayementDao.updatePeriodePayement(periodePayement);
        resp.sendRedirect("listPayer");
    }
    private void listRetardataire(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String tranche = req.getParameter("tranche");
    	 try {
             List<Payer> listRetardataire = payerDao.selectRetardataire(tranche);
             List<PeriodePayement> listPeriodePayement = periodePayementDao.selectAllPeriodePayement();
             
             req.setAttribute("listPeriodePayement", listPeriodePayement);
             System.out.println("Nombre de retardataire recu: " + listRetardataire);  // Debugging log
             req.setAttribute("listRetardataire", listRetardataire);
             RequestDispatcher dispatcher = req.getRequestDispatcher("Payer-list.jsp");
             dispatcher.forward(req, resp);
         } catch (Exception e) {
             e.printStackTrace();
         }
  }
    
    private void notifierRetardataires(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String mail = req.getParameter("mail");
        NotifierRetardataires notifier = new NotifierRetardataires();
        notifier.notifierRetardataires(mail);
        resp.sendRedirect("listPayer");
    }

}


