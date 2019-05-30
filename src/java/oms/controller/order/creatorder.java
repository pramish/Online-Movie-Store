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


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        
        // Check if movie in session
        if(movie == null)response.sendRedirect("/");
        List<String> errors = new ArrayList<>();
        session.setAttribute("orderErrors", errors);
        
        if(movie.getStock()<=0){
            errors.add("There is no stock left for this movie.");
        }else{
            
            int amount = Integer.parseInt(request.getParameter("amount"));
            
            if(amount > movie.getStock())
            {
                errors.add("There is not enough stock for this amount.");
            }else{
                
                try {
                    BigDecimal price = movie.getPrice();
                    BigDecimal totalPrice = BigDecimal.valueOf(amount).multiply(price);
                    User user = (User) session.getAttribute("user");
                    
                    
                    
                    boolean orderIDExists = true;
                    String ID = "" + (new Random()).nextInt(999999);
                    
                    while (manager.searchOrderById(ID) != null) {
                        ID = "" + (new Random()).nextInt(999999);
                    }
                    
                    String action = request.getParameter("action");
                    
                    
                    if("Save".equals(action))
                    {
                        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
                        LocalDate releaseDate = LocalDate.parse(sp.format(new Date()));
                        
                        manager.addOrder(ID,
                                movie.getID(), user.getID(),
                                price, amount,
                                totalPrice, releaseDate,
                                "SAVED"
                        );
                    }else if("Submit".equals(action))
                    {
                        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
                        LocalDate releaseDate = LocalDate.parse(sp.format(new Date()));
                        
                        manager.addOrder(ID,
                                movie.getID(), user.getID(),
                                price, amount,
                                totalPrice, releaseDate,
                                "SUBMITTED"
                        );
                        
                        movie.setStock(movie.getStock() - amount);
                        
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
