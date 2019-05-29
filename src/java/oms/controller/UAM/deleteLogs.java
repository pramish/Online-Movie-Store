/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oms.controller.UAM;

import MODEL.DAO.DatabaseManager;
import MODEL.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import oms.controller.registration.Delete;

/**
 *
 * @author jonny
 */
@WebServlet(name = "deleteLogs", urlPatterns = {"/deleteLogs"})
public class deleteLogs extends HttpServlet {

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        // Get Session
        HttpSession session = request.getSession();
        
        // Get user from session
        String id = request.getParameter("id");
        
        if(id != null){
            DatabaseManager manager = (DatabaseManager)session.getAttribute("manager");
            try {
                manager.deleteUserAccessLog(id);
            } catch (SQLException ex) {
                Logger.getLogger(deleteLogs.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        response.sendRedirect("/listAccessLogs");
        
    }


}
