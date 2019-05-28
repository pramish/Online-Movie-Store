/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oms.controller.registration;

import MODEL.DAO.DatabaseManager;
import MODEL.User;
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
 * @author Photato
 */
@WebServlet(name = "Delete", urlPatterns = {"/myaccount/cancel"})
public class Delete extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get Session
        HttpSession session = request.getSession();
        
        // Get user from session
        User user = (User) session.getAttribute("user");
        
        //If user does not exist redirect to index
        if(user == null)
            response.sendRedirect("/login");
        
        if("ACTIVE".equals(user.getStatus())){
            try {
                DatabaseManager manager = (DatabaseManager)session.getAttribute("manager");
                manager.updateUser(user.getID(), user.getEmail(), user.getName(), user.getPassword(), user.getPhoneNumber(), "CANCELLED");
                session.setAttribute("user", new User());
            } catch (SQLException ex) {
                Logger.getLogger(Delete.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        RequestDispatcher view = request.getRequestDispatcher("/Registration/CancelRegistration.jsp");
        view.forward(request, response);
    }
}