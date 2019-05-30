/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oms.controller.registration;

import MODEL.DAO.DatabaseManager;
import MODEL.User;
import MODEL.controller.Validator;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
@WebServlet(name = "Update", urlPatterns = {"/myaccount/edit"})
public class Update extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        DatabaseManager manager = (DatabaseManager)session.getAttribute("manager");
        
        // Get user from session
        User user = (User) session.getAttribute("user");
        
        //If user does not exist Create new user
        if(user == null){
            user = new User();
            
            boolean userIDExists = true;
            try {
                while(userIDExists)
                {
                    user.setID(""+((new Random()).nextInt(900000000)+ + 100000000));
                    userIDExists = manager.getUser(user.getID()) != null;
                }
                manager.addAnonymousUser(user.getID());
            } catch (SQLException ex) {
                Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Store user in session
            session.setAttribute("user", user);
        }
        
        User editUser = new User(user.getID(), user.getEmail(), user.getName(), user.getPassword(), user.getPhoneNumber(), user.getStatus());
        
        // Redirect to register if user is not registered.
        if("".equals(user.getStatus())) response.sendRedirect("/login");
        else{
            // Set editMyAccountUser as user
            session.setAttribute("editMyAccountUser", editUser);

            // Clear errors list
            session.setAttribute("editMyAccountErrors", new ArrayList<>());

            // Process Request
            // Redirect to Create view
            RequestDispatcher view = request.getRequestDispatcher("/Registration/UpdateRegistration.jsp");
            view.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get Session
        HttpSession session = request.getSession();
        DatabaseManager manager = (DatabaseManager)session.getAttribute("manager");
        
        // Get User
        User user = (User) session.getAttribute("user");
        
        //If user does not exist Create new user
        if(user == null){
            user = new User();
            
            boolean userIDExists = true;
            try {
                while(userIDExists)
                {
                    user.setID(""+((new Random()).nextInt(900000000)+ + 100000000));
                    userIDExists = manager.getUser(user.getID()) != null;
                }
                manager.addAnonymousUser(user.getID());
            } catch (SQLException ex) {
                Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Store user in session
            session.setAttribute("user", user);
        }
        
        if("".equals(user.getStatus())) response.sendRedirect("/login");
        
        User editUser =  (User) session.getAttribute("editMyAccountUser");
        if(editUser == null)
        {
            editUser = new User(user.getID(), user.getEmail(), user.getName(), user.getPassword(), user.getPhoneNumber(), user.getStatus());
            session.setAttribute("editMyAccountUser", editUser);
        }
        
        // Get form variables
        editUser.setEmail(request.getParameter("email"));
        editUser.setName(request.getParameter("name"));
        editUser.setPassword(request.getParameter("password"));
        editUser.setPhoneNumber(request.getParameter("phone"));
        
        // Validate variables
        List<String> errors = new ArrayList<>();
        Validator v = new Validator();
        if(!v.validateEmail(editUser.getEmail()))       
            errors.add("Email is not a valid email.");
        if(!v.validateName(editUser.getName()))         
            errors.add("Name is not a valid name.");
        if(!v.validatePhoneNumber(editUser.getPhoneNumber())) 
            errors.add("Phone number is not valid number.");
        if(!v.validatePassword(editUser.getPassword())) 
            errors.add("Password is not valid.");
        
        
        try {
            if(!editUser.getEmail().toLowerCase().equals(user.getEmail().toLowerCase()) && manager.getUserByEmail(editUser.getEmail()) != null){
                errors.add("This email address is registered to another user!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
            errors.add(ex.getMessage());
        }
        
        // IF there are errors
        if(errors.size() > 0){
            
            //Store errors in session
            session.setAttribute("editMyAccountErrors", errors);
            // Redirect to Create view
            RequestDispatcher view = request.getRequestDispatcher("/Registration/UpdateRegistration.jsp");
            view.forward(request, response);
        }else{
            try {
                // Add user
                manager.updateUser(editUser.getID(), editUser.getEmail(), editUser.getName(), editUser.getPassword(), editUser.getPhoneNumber(), editUser.getStatus());
                
                user = manager.getUser(editUser.getID());
                session.setAttribute("user", user);
                response.sendRedirect("/myaccount");
                
            } catch (SQLException ex) {
                Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
                errors.add(ex.getMessage());
                session.setAttribute("editMyAccountErrors", errors);
                // Redirect to Create view
                RequestDispatcher view = request.getRequestDispatcher("/Registration/UpdateRegistration.jsp");
                view.forward(request, response);
            }
        }
    }
}
