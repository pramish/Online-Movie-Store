/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL.controller;

import MODEL.DAO.DatabaseConnector;
import MODEL.DAO.DatabaseManager;
import MODEL.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import oms.controller.registration.Create;


public class ConnServlet extends HttpServlet {

    private DatabaseConnector db;
    private DatabaseManager manager;
    private Connection conn;
    private User user;

    @Override //Create and instance of DBConnector for the deployment session
    public void init() {
        try {
            db = new DatabaseConnector();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override //Add the DBManager instance to the session 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        conn = db.openConnection();
        try {
            manager = new DatabaseManager(conn);
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Check if user in session. If not then create user.
        session.setAttribute("manager", manager);
        user = (User)session.getAttribute("user");
        
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
    }
    
    @Override //Destroy the servlet and release the resources of the application
    public void destroy() {
        try {
            db.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
