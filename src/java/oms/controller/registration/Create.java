package oms.controller.registration;

import MODEL.DAO.DatabaseManager;
import MODEL.User;
import MODEL.controller.Validator;
import java.io.IOException;
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

@WebServlet(name = "Create", urlPatterns = {"/register"})
public class Create extends HttpServlet {

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
        User registerUser = new User(user.getID(), user.getEmail(), user.getName(), user.getPassword(), user.getPhoneNumber(), user.getStatus());
        
        // Set registeruser as user
        session.setAttribute("registerUser", registerUser);
        
        // Clear errors list
        session.setAttribute("registerErrors", new ArrayList<>());
        
        // Process Request
        RequestDispatcher view = request.getRequestDispatcher("/Registration/Register.jsp");
        view.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get Session
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
        
        User registerUser =  (User) session.getAttribute("registerUser");
        if(registerUser == null)
        {
            registerUser = new User(user.getID(), user.getEmail(), user.getName(), user.getPassword(), user.getPhoneNumber(), user.getStatus());
            session.setAttribute("registerUser", registerUser);
        }
        
        // Get form variables
        registerUser.setEmail(request.getParameter("email"));
        registerUser.setName(request.getParameter("name"));
        registerUser.setPassword(request.getParameter("password"));
        registerUser.setPhoneNumber(request.getParameter("phone"));
        
        // Validate variables
        List<String> errors = new ArrayList<>();
        Validator v = new Validator();
        
        if(!v.validateEmail(registerUser.getEmail()))       
            errors.add("Email is not a valid email.");
        if(!v.validateName(registerUser.getName()))         
            errors.add("Name is not a valid name.");
        if(!v.validatePhoneNumber(registerUser.getPhoneNumber())) 
            errors.add("Phone number is not valid number.");
        if(!v.validatePassword(registerUser.getPassword())) 
            errors.add("Password is not valid.");
        
        try {
            if(manager.getUserByEmail(registerUser.getEmail()) != null){
                errors.add("This email address is already registered.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
            errors.add(ex.getMessage());
        }
        
        // IF there are errors
        if(errors.size() > 0){
            
            //Store errors in session
            session.setAttribute("registerErrors", errors);
            RequestDispatcher view = request.getRequestDispatcher("/Registration/Register.jsp");
            view.forward(request, response);
        }else{
            try {
                // Add user
                manager.registerUser(registerUser.getID(), registerUser.getEmail(), registerUser.getName(), registerUser.getPassword(), registerUser.getPhoneNumber());
                
                user = manager.getUser(registerUser.getID());
                session.setAttribute("user", user);
                response.sendRedirect("/");
                
            } catch (SQLException ex) {
                Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
                errors.add(ex.getMessage());
                session.setAttribute("registerErrors", errors);
                RequestDispatcher view = request.getRequestDispatcher("/Registration/Register.jsp");
                view.forward(request, response);
            }
        }
    }
}
