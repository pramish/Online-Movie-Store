/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oms.controller.movie;

import MODEL.DAO.DatabaseManager;
import MODEL.Movie;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
 * @author luckylau
 */
@WebServlet(name = "updateMovie", urlPatterns = {"/movie/update"})
public class updateMovie extends HttpServlet {



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
            
            String id = request.getParameter("id");
            // Clear errors list
            session.setAttribute("movieErrors", new ArrayList<>());
            
            Movie movie = manager.getMovieByID(id);
            session.removeAttribute("failure1");
            if(movie != null){
                session.setAttribute("movie", movie);
                RequestDispatcher view = request.getRequestDispatcher("/Movie/updateMovie.jsp");
                view.forward(request, response);    
            }else{
                response.sendRedirect("/movie/list");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(updateMovie.class.getName()).log(Level.SEVERE, null, ex);
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
        
        Movie movie = (Movie) session.getAttribute("movie");
        
        DatabaseManager manager = (DatabaseManager)session.getAttribute("manager"); 
        List<String> errors = new ArrayList<>();
        session.setAttribute("movieErrors", errors);
        
        if(movie != null){
            
            String title = request.getParameter("title");
            String genre = request.getParameter("genre");
            BigDecimal price = new BigDecimal(0);
            try{
            price = new java.math.BigDecimal(request.getParameter("price"));}
            catch(Exception e){}
            int stock = -1; 
            try{
                stock = Integer.parseInt(request.getParameter("stock"));
            }catch(Exception e){}
            
            
            // Validation
            if(title == null || title.equals(""))
                errors.add("You must enter a title.");
            if(genre == null || genre.equals(""))
                errors.add("You must enter a genre.");
            if(price.compareTo(new BigDecimal(0)) != 1)
                errors.add("Price must be bigger than 0.");
            if(stock < 0)
                errors.add("Stock must 0 or greater.");
            if(errors.size() == 0){
                try {

                    manager.updateMovie(movie.getID(), 
                            title, 
                            genre, 
                            price, 
                            stock
                    );
                    response.sendRedirect("/movie/list");
                } catch (SQLException ex) {
                    Logger.getLogger(updateMovie.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                RequestDispatcher view = request.getRequestDispatcher("/Movie/updateMovie.jsp");
                view.forward(request, response);
            }  
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
