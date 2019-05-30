/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oms.controller.staffmanagement;

import MODEL.DAO.DatabaseManager;
import MODEL.Staff;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pramishluitel
 */
@WebServlet(name = "deleteStaff", urlPatterns = {"/deleteStaff"})
public class deleteStaff extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet deleteStaff</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet deleteStaff at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        DatabaseManager manager = (DatabaseManager) session.getAttribute("manager");
        
        //Get staff from session attributes.
        Staff editStaff = (Staff) session.getAttribute("editStaff");
        if(editStaff!=null){
            try {
                manager.deleteStaff(editStaff.getID());
            } catch (SQLException ex) {
                Logger.getLogger(deleteStaff.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        session.setAttribute("editStaff", null);
        response.sendRedirect("/readStaff");
    }
    
//    HttpSession session = request.getSession();
//        DatabaseManager manager = (DatabaseManager) session.getAttribute("manager");
//        
//        // Get User from Attributes
//        User editUser = (User)session.getAttribute("editUser");
//        if(editUser != null){
//            try {
//                manager.deleteUser(editUser.getID());
//            } catch (SQLException ex) {
//                Logger.getLogger(deleteUser.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        session.setAttribute("editUser", null);
//        response.sendRedirect("/users");

}
