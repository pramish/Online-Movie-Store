/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oms.controller.UAM;

import MODEL.DAO.DatabaseConnector;
import MODEL.DAO.DatabaseManager;
import MODEL.User;
import MODEL.controller.Validator;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
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
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        RequestDispatcher view = request.getRequestDispatcher("/UserAccessManagement/login.jsp");
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
            
            String userEmail = request.getParameter("userEmail");
            String userPassword = request.getParameter("userPassword");
            
            String logID = ""+((new Random()).nextInt(900000000)+ + 100000000);
            Date date= new Date();
            long time = date.getTime();
            Timestamp isTime = new Timestamp(time);
            String timeStamp = "" + isTime;
            String acessType = "LOGIN";
            
            
            Validator v = new Validator();
            if (!v.validateEmail(userEmail)){
                session.setAttribute("existErr", "Invalid email.");
                response.sendRedirect("/login");
            }else if (!v.validatePassword(userPassword)){
                session.setAttribute("existErr", "Invalid password.");
                response.sendRedirect("/login");
            }else {
              
                User user = manager.findUserLogin(userEmail, userPassword);

                if (user != null) {
                    session.setAttribute("user", user);
                    manager.addUserLogs(logID, user.getID(), acessType, timeStamp);
                    response.sendRedirect("/");

                }else{
                    session.setAttribute("existErr", "This account does not exist.");
                    response.sendRedirect("/login");
            }             

            }
            
            
                
          
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            PrintWriter out = response.getWriter();
            out.println(ex.getMessage() + "1");
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
