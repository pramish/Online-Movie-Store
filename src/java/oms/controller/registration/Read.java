/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oms.controller.registration;

/**
 *
 * @author Photato
 */
import MODEL.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Read", urlPatterns = {"/myaccount"})
public class Read extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get Session
        HttpSession session = request.getSession();
        
        // Get user from session
        User user = (User) session.getAttribute("user");
        
        // Redirect to register if user is not registered.
        if(user == null || "".equals(user.getStatus())) response.sendRedirect("/login");
        else{
            RequestDispatcher view = request.getRequestDispatcher("/Registration/ViewRegistration.jsp");
            view.forward(request, response);    
        }
    }
}
