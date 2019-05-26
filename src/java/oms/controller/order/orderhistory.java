/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oms.controller.order;

import MODEL.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ian
 */
@WebServlet(name = "orderhistory", urlPatterns = {"/order/history"}, initParams = {
    @WebInitParam(name = "Name", value = "Value")})
public class orderhistory extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        User user = (User) session.getAttribute("user");
        
        //If user does not exist Create new user
        if(user == null){
            user = new User();
            
            // Store user in session
            session.setAttribute("user", user);
        }
        
        
        
        
        
        
        RequestDispatcher view = request.getRequestDispatcher("/Order/history.jsp");
            view.forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        
        
    }

}
