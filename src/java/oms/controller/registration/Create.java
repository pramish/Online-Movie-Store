package oms.controller.registration;

import MODEL.DAO.DatabaseManager;
import MODEL.User;
import MODEL.controller.Validator;
import java.io.IOException;
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

@WebServlet(name = "Create", urlPatterns = {"/register"})
public class Create extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get user from session
        User user = getSessionUser(request);
        User registerUser = new User(user.getID(), user.getEmail(), user.getName(), user.getPassword(), user.getPhoneNumber(), user.getStatus());
        HttpSession session = request.getSession();
        
        // Set registeruser as user
        session.setAttribute("registerUser", registerUser);
        
        // Clear errors list
        session.setAttribute("registerErrors", new ArrayList<>());
        
        // Process Request
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get Session
        HttpSession session = request.getSession();
        
        // Get User
        User user = getSessionUser(request);
        
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
        
        // IF there are errors
        if(errors.size() > 0){
            
            //Store errors in session
            session.setAttribute("registerErrors", errors);
            processRequest(request, response);
        }else{
            try {
                // Add user
                DatabaseManager manager = (DatabaseManager)session.getAttribute("manager");
                manager.addUser(registerUser.getID(), registerUser.getEmail(), registerUser.getName(), registerUser.getPassword(), registerUser.getPhoneNumber());
                
                
                user = manager.getUser(registerUser.getID());
                session.setAttribute("user", user);
                response.sendRedirect("/");
                
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
                errors.add(ex.getMessage());
                session.setAttribute("registerErrors", errors);
                processRequest(request, response);
            }
            
            
            
        }
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Helpful functions. Click on the + sign on the left to edit the code.">
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Redirect to Create view
        RequestDispatcher view = request.getRequestDispatcher("/Register/Register.jsp");
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
