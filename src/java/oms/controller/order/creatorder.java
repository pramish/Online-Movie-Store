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
@WebServlet(name = "creatorder", urlPatterns = {"/order/create"})
public class creatorder extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            DatabaseManager manager = (DatabaseManager) session.getAttribute("manager");
         
            session.setAttribute("orderErrors", new ArrayList<>());
            session.removeAttribute("order");
            
            String movieId = request.getParameter("id");
            
            Movie movie = manager.getMovieByID(movieId);
            
            if(movie.getStock() > 0){
                session.setAttribute("buyMovie", movie);
                session.setAttribute("orderErrors", new ArrayList<>());
                
                RequestDispatcher view = request.getRequestDispatcher("/Order/createorder.jsp");
                view.forward(request, response);
            }else{
                response.sendRedirect("/movie/list");   
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(creatorder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //to get session
        HttpSession session = request.getSession();
        DatabaseManager manager = (DatabaseManager) session.getAttribute("manager");
        
        // Get movie
        Movie movie = (Movie) session.getAttribute("buyMovie");
        Order order = new Order();
        
        
        // Check if movie in session
        if(movie == null)response.sendRedirect("/");
        
        List<String> errors = new ArrayList<>();
        
        session.setAttribute("orderErrors", errors);
        
        order.setAmount(Integer.parseInt(request.getParameter("amount")));
        
        if(movie.getStock()<=0){
            errors.add("There is no stock left for this movie.");
        }else{
            try{
            
            }catch (Exception e){
                errors.add("Please enter a valid number.");
            }
            
            // validate
            if(order.getAmount() <= 0){
                errors.add("The amount must be greater than 0");
            }else if(order.getAmount() > movie.getStock()){
                errors.add("There is not enough stock for this amount.");
            }else{
                try {
                    BigDecimal price = movie.getPrice();
                    BigDecimal totalPrice = BigDecimal.valueOf(order.getAmount()).multiply(price);
                    User user = (User) session.getAttribute("user");
                    
                    String ID = "" + (new Random()).nextInt(999999);
                    
                    while (manager.searchOrderById(ID) != null) {
                        ID = "" + (new Random()).nextInt(999999);
                    }
                    
                    SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
                    LocalDate releaseDate = LocalDate.parse(sp.format(new Date()));
                    
                    String action = request.getParameter("action");
                    
                    if("Save".equals(action)){
                        order.setStatus("SAVED");
                    }else if("Submit".equals(action)){
                        order.setStatus("SUBMITTED");
                    }
                    
                    order.setPrice(price);
                    order.setTotalPrice(totalPrice);
                    order.setUserID(user.getID());
                    order.setID(ID);
                    order.setDate(releaseDate);
                    
                    
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
            try {
                manager.addOrder(order.getID(),
                        movie.getID(),
                        order.getUserID(),
                        order.getPrice(),
                        order.getAmount(),
                        order.getTotalPrice(),
                        order.getDate(),
                        order.getStatus()
                );
            } catch (SQLException ex) {
                Logger.getLogger(creatorder.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if("SUBMITTED".equals(order.getStatus())){
                try {
                    movie.setStock(movie.getStock() - order.getAmount());
                    manager.updateMovieStock(movie.getID(), movie.getStock());
                } catch (SQLException ex) {
                    Logger.getLogger(creatorder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                  
            response.sendRedirect("/order/history");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
