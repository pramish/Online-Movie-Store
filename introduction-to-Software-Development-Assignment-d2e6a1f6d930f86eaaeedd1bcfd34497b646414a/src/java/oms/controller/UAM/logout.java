/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oms.controller.UAM;

import MODEL.DAO.DatabaseConnector;
import MODEL.DAO.DatabaseManager;
import MODEL.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;
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
@WebServlet(name = "logout", urlPatterns = {"/logout"})
public class logout extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        RequestDispatcher view = request.getRequestDispatcher("/UserAccessManagement/loginWelcome.jsp");
        view.forward(request, response);
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
        try {
            HttpSession session = request.getSession();
            
            DatabaseManager manager = (DatabaseManager)session.getAttribute("manager");
           
            
            DatabaseConnector connector = new DatabaseConnector(); //Create a connection and initialize DB conn-field
            Connection conn = connector.openConnection(); //Get the protected connection instance from DB superclass to share for the application classes
            DatabaseManager db = new DatabaseManager(conn); //DBManger instance provide users with access to CRUD operations
            
            
            
            String logID = UUID.randomUUID().toString().replaceAll("-", "");
            Date date= new Date();
            long time = date.getTime();
            Timestamp isTime = new Timestamp(time);
            String timeStamp = "" + isTime;
            String acessType = "LOGOUT";
            
            User user = (User)session.getAttribute("user");
            
//            Student student = new Student("ID","name","email","password","dob","white");
            if (user != null) {
                try {
                    db.addUserLogs(logID, user.getID(), acessType, timeStamp);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                    PrintWriter out = response.getWriter();
            out.println(ex.getMessage() + "3");
                }
    
            }
            session.setAttribute("user",null);
response.sendRedirect("/");
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            PrintWriter out = response.getWriter();
            out.println(ex.getMessage() + "1");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            PrintWriter out = response.getWriter();
            out.println(ex.getMessage() + "2");
        }
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
