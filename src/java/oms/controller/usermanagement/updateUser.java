/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oms.controller.usermanagement;

import MODEL.DAO.DatabaseManager;
import MODEL.User;
import MODEL.controller.Validator;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
import oms.controller.registration.Create;

/**
 *
 * @author pramishluitel
 */
@WebServlet(name = "updateUser", urlPatterns = {"/updateUser"})
public class updateUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        DatabaseManager manager = (DatabaseManager)session.getAttribute("manager");
        
        
        //Get user from querystring
        String id = request.getParameter("id");
        
        
        User editUser = null;
        try {
            editUser = manager.getUserByID(id);
        } catch (SQLException ex) {
            Logger.getLogger(updateUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(editUser == null){
            response.sendRedirect("/users");
        }else{
            
        // Set editMyAccountUser as user
        session.setAttribute("editUser", editUser);
        session.setAttribute("editUserErrors", new ArrayList<>());
        
        RequestDispatcher view = request.getRequestDispatcher("/UserManagement/updateUser.jsp");
        view.forward(request, response);
    }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        DatabaseManager manager = (DatabaseManager) session.getAttribute("manager");
        
        // Get User from Attributes
        User editUser = (User)session.getAttribute("editUser");
        if(editUser == null){response.sendRedirect("/users");}
        
        // get values from form and put into user object
        editUser.setEmail(request.getParameter("email"));
        editUser.setName(request.getParameter("name"));
        editUser.setPassword(request.getParameter("password"));
        editUser.setPhoneNumber(request.getParameter("phone"));
        editUser.setStatus(request.getParameter("status"));
        
        // Validate values
        List<String> errors = new ArrayList<>();
        Validator v = new Validator();
        if(!v.validateEmail(editUser.getEmail()))       
            errors.add("Please enter a valid email.");
        if(!v.validateName(editUser.getName()))         
            errors.add("Please enter a valid name.");
        if(!v.validatePhoneNumber(editUser.getPhoneNumber())) 
            errors.add("Please enter a valid phonenumber.");
        if(!v.validatePassword(editUser.getPassword())) 
            errors.add("Please enter a valid password.");
        
        // check if new email already exists in database
        try {
            User userEmailCheck = manager.getUser(editUser.getID());
            
            if(!userEmailCheck.getEmail().equals(editUser.getEmail())){
                if(manager.checkEmail(editUser.getEmail())){
                    errors.add("This email address is registered to another user!");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
            //errors.add(ex.getMessage());
        }
        
        
        if(errors.size() > 0){
            
            //Store errors in session
            session.setAttribute("editUserErrors", errors);
            
            // Redirect to Update view
            RequestDispatcher view = request.getRequestDispatcher("/UserManagement/updateUser.jsp");
            view.forward(request, response);
        }else{
            try {
                // udate user
                manager.updateUser(editUser.getID(), editUser.getEmail(), editUser.getName(), editUser.getPassword(), editUser.getPhoneNumber(), editUser.getStatus());
                session.setAttribute("editUser", null);
                response.sendRedirect("/users");
                
            } catch (SQLException ex) {
                Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
                errors.add(ex.getMessage());
                session.setAttribute("editUserErrors", errors);
                // Redirect to Create view
                RequestDispatcher view = request.getRequestDispatcher("/UserManagement/updateUser.jsp");
                view.forward(request, response);
            }
        }
        
    }
}
