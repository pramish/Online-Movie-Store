/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oms.controller.usermanagement;

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
 * @author pramishluitel
 */
@WebServlet(name = "userList", urlPatterns = {"/users"})
public class userList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get Session
        HttpSession session = request.getSession();
        
        // Get user from session
        //User user = (User) session.getAttribute("user");
        
       
        
        String search = request.getParameter("search");
        
        
        DatabaseManager manager = (DatabaseManager)session.getAttribute("manager");
        
        List<User> list = new ArrayList<>();
        String userlisterror = "";
        try {
            list = manager.searchUsersByNameAndPhone(search);
        } catch (SQLException ex) {
            Logger.getLogger(userList.class.getName()).log(Level.SEVERE, null, ex);
            userlisterror = ex.getMessage();
            session.setAttribute("userlisterror", userlisterror);
            
        }
        
        session.setAttribute("userList", list);
                
        
        
        RequestDispatcher view = request.getRequestDispatcher("/UserManagement/userlist.jsp");
            view.forward(request, response); 
        
    }
}
