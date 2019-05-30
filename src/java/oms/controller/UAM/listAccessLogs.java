/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oms.controller.UAM;

import MODEL.DAO.DatabaseManager;
import MODEL.Movie;
import MODEL.User;
import MODEL.UserAccessLogs;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jonny
 */
@WebServlet(name = "listAccessLogs", urlPatterns = {"/listAccessLogs"})
public class listAccessLogs extends HttpServlet {

  

    
   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        HttpSession session = request.getSession();
        DatabaseManager manager = (DatabaseManager)session.getAttribute("manager");
        
        
         String userDate = request.getParameter("date");
         List<UserAccessLogs> logList = new ArrayList<>();
        
        
        try {
          
         
           
           logList = manager.searchLogsByDate(userDate);
        } catch (SQLException ex) {
            Logger.getLogger(listAccessLogs.class.getName()).log(Level.SEVERE, null, ex);
        }
        
          session.setAttribute("logList", logList);
        
        RequestDispatcher view = request.getRequestDispatcher("/UserAccessManagement/myAccessLogs.jsp");
        view.forward(request, response);
        
        
        //session.setAttribute("logList",logList);
//        try {
//            User user = (User) session.getAttribute("user");
//            String userID = user.getID();
//            
//            DatabaseManager manager = (DatabaseManager)session.getAttribute("manager");
//            logList = manager.searchLog(userID);
//            
//            session.setAttribute("logList",logList);
//             RequestDispatcher view = request.getRequestDispatcher("/UserAccessManagement/myAccessLogs.jsp");
//        view.forward(request, response);
//        } catch (SQLException ex) {
//            Logger.getLogger(listAccessLogs.class.getName()).log(Level.SEVERE, null, ex);
//        }
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
       
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
