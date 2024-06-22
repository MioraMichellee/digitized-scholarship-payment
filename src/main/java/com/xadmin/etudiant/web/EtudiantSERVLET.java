
package com.xadmin.etudiant.web;
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

import com.xadmin.etudiant.bean.Etudiant;
import com.xadmin.etudiant.dao.EtudiantDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class EtudiantSERVLET extends HttpServlet {
    private EtudiantDao etudiantDao;


    @Override
    public void init() throws ServletException{
        etudiantDao = new EtudiantDao();
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
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        PrintWriter pr = resp.getWriter();
//        pr.write("route ngamba ito");
//
//        String action = req.getServletPath();
//        switch (action)
//        {
//            case "/new":
//                showNewForm(req, resp);
//                break;
//            case "/insert":
//                insertEtudiant(req,resp);
//                break;
//            case "/delete":
//                deleteEtudiant(req,resp);
//                break;
//            case "/edit":
//                showEditForm(req,resp);
//                break;
//            case "/update":
//                updateEtudiant(req, resp);
//                break;
//
//            default:
//                listEtudiant(req, resp);
//                break;
//    }
//    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        System.out.println("Action: " + action);  // Debugging log
        switch (action)
        {
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
        }
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
        int matricule = Integer.parseInt(req.getParameter("matricule"));
        Etudiant existingEtudiant;
        try {
            existingEtudiant = etudiantDao.selectEtudiant(matricule);
            RequestDispatcher dispatcher = req.getRequestDispatcher("Etudiant-form.jsp");
            req.setAttribute("etudiant", existingEtudiant);
            dispatcher.forward(req, resp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // update
    public void updateEtudiant(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int matricule = Integer.parseInt(req.getParameter("matricule"));
        String nom = req.getParameter("nom");
        String sexe = req.getParameter("sexe");
        String dateNais = req.getParameter("dateNais");
        String institution = req.getParameter("institution");
        String niveau = req.getParameter("niveau");
        String  mail = req.getParameter("mail");
        String  anneeUniv = req.getParameter("anneeUniv");

        Etudiant etudiant = new Etudiant(matricule,nom, sexe,dateNais,institution,niveau, mail, anneeUniv);
        etudiantDao.updateEtudiant(etudiant);
        resp.sendRedirect("list");
    }

    // default

//    private void listEtudiant(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//        try{
//            List<Etudiant> listEtudiant = etudiantDao.selectAllEtudiant();
//            req.setAttribute("listEtudiant", listEtudiant);
//            RequestDispatcher dispatcher = req.getRequestDispatcher("Etudiant-list.jsp");
//            dispatcher.forward(req, resp);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
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
}
