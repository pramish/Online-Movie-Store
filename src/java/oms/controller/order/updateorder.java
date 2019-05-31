/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oms.controller.order;

import MODEL.DAO.DatabaseManager;
import MODEL.Movie;
import MODEL.Order;
import MODEL.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
 * @author Ian
 */
@WebServlet(name = "updateorder", urlPatterns = {"/order/update"})
public class updateorder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        
        HttpSession session = request.getSession();
        DatabaseManager manager = (DatabaseManager) session.getAttribute("manager");
        session.setAttribute("orderErrors", new ArrayList<>());
        try {
            Order order = manager.searchOrderById(id);
            if(order == null){
                response.sendRedirect("/order/history");    
            }else{
                Movie movie = manager.getMovieByID(order.getMovieID());
                
                session.setAttribute("buyMovie", movie);
                session.setAttribute("order", order);
                
                RequestDispatcher view = request.getRequestDispatcher("/Order/createorder.jsp");
                view.forward(request, response);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(updateorder.class.getName()).log(Level.SEVERE, null, ex);
         
            response.sendRedirect("/order/history");
        }
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //to get session
        HttpSession session = request.getSession();
        DatabaseManager manager = (DatabaseManager) session.getAttribute("manager");
        
        // Get movie
        Movie movie = (Movie) session.getAttribute("buyMovie");
        Order order = (Order) session.getAttribute("order");
        
        // Check if movie in session
        if(order == null || movie == null){
            response.sendRedirect("/order/history");
        }
        
        List<String> errors = new ArrayList<>();
        session.setAttribute("orderErrors", errors);
        
        // Get amount
        order.setAmount(Integer.parseInt(request.getParameter("amount")));
        
        if(movie.getStock()<=0){
            errors.add("There is no stock left for this movie.");
        }else if(order.getAmount() <= 0){
                errors.add("The amount must be greater than 0");
        }else if(order.getAmount() > movie.getStock()){
            errors.add("There is not enough stock for this amount.");
        } else{
            
            if(order.getAmount() > movie.getStock())
            {
                errors.add("There is not enough stock for this amount.");
            }else{
                try {
                    BigDecimal price = movie.getPrice();
                    BigDecimal totalPrice = BigDecimal.valueOf(order.getAmount()).multiply(price);
                  
                    String action = request.getParameter("action");
                    
                    if("Save".equals(action))
                    {
                        manager.updateOrder(order.getAmount(), totalPrice, order.getID(), "SAVED");                      
                    }else if("Submit".equals(action))
                    {
                        manager.updateOrder(order.getAmount(), totalPrice, order.getID(), "SUBMITTED");
                        movie.setStock(movie.getStock() - order.getAmount());
                        manager.updateMovieStock(movie.getID(), movie.getStock());
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(creatorder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        if  (errors.size()>0)
        {
            RequestDispatcher view = request.getRequestDispatcher("/Order/createorder.jsp");
            view.forward(request, response);
        }else{
            response.sendRedirect("/order/history");
        }
    }
}
