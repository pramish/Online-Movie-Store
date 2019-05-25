/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oms.controller.userAccessManagement;

import MODEL.DAO.DatabaseManager;
import MODEL.User;
import MODEL.controller.Validator;
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
@WebServlet(name = "Update", urlPatterns = {"/myaccount/edit"})
public class Update extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get user from session
        User user = getSessionUser(request);
        User editUser = new User(user.getID(), user.getEmail(), user.getName(), user.getPassword(), user.getPhoneNumber(), user.getStatus());
        
        // Redirect to register if user is not registered.
        if("".equals(user.getStatus())) response.sendRedirect("/login");
        else{
            HttpSession session = request.getSession();
        
            // Set editMyAccountUser as user
            session.setAttribute("editMyAccountUser", editUser);

            // Clear errors list
            session.setAttribute("editMyAccountErrors", new ArrayList<>());

            // Process Request
            processRequest(request, response);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get Session
        HttpSession session = request.getSession();
        
        // Get User
        User user = getSessionUser(request);
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
        
        // IF there are errors
        if(errors.size() > 0){
            
            //Store errors in session
            session.setAttribute("editMyAccountErrors", errors);
            processRequest(request, response);
        }else{
            try {
                // Add user
                DatabaseManager manager = (DatabaseManager)session.getAttribute("manager");
                manager.updateUser(editUser.getID(), editUser.getEmail(), editUser.getName(), editUser.getPassword(), editUser.getPhoneNumber(), editUser.getStatus());
                
                user = manager.getUser(editUser.getID());
                session.setAttribute("user", user);
                response.sendRedirect("/myaccount");
                
            } catch (SQLException ex) {
                Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
                errors.add(ex.getMessage());
                session.setAttribute("editMyAccountErrors", errors);
                processRequest(request, response);
            }
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
    //</editor-fold>

    
    // <editor-fold defaultstate="collapsed" desc="Helpful functions. Click on the + sign on the left to edit the code.">
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Redirect to Create view
        RequestDispatcher view = request.getRequestDispatcher("/UserAccessManagement/UpdateRegistration.jsp");
        view.forward(request, response);
    }

    private User getSessionUser(HttpServletRequest request){
        
        
        HttpSession session = request.getSession();
        
        // Get user from session
        User user = (User) session.getAttribute("user");
        
        //If user does not exist Create new user
        if(user == null){
            user = new User();
            
            // Store user in session
            session.setAttribute("user", user);
        }
        
        return user;
    }
    //</editor-fold>
    
}
