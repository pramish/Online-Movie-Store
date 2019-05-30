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
import java.util.Set;
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

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet updateMovie</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateMovie at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
         
        HttpSession session = request.getSession();
        DatabaseManager manager = (DatabaseManager)session.getAttribute("manager");
        
        String id = request.getParameter("id");
        Movie editMovie = null;
        try {
            editMovie = manager.getMovieByID(id);
        } catch (SQLException ex) {
            Logger.getLogger(updateMovie.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(editMovie == null)
            response.sendRedirect("/movie/list");
        else 
            session.setAttribute("editMovie", editMovie);
            session.setAttribute("editMovieErrors", new ArrayList<>());

        
        RequestDispatcher view = request.getRequestDispatcher("/Movie/updateMovie.jsp");
    view.forward(request, response);
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
        DatabaseManager manager = (DatabaseManager)session.getAttribute("manager"); 
        
        Movie movie = (Movie) session.getAttribute("editMovie");
        if(movie == null)
            response.sendRedirect("/movie/list");
        
         movie.setTitle(request.getParameter("title"));
         movie.setGenre(request.getParameter("genre"));
         movie.setPrice(new BigDecimal(request.getParameter("price")));
        movie.setStock(Integer.parseInt(request.getParameter("stock")));
        
        List<String> errors = new ArrayList<>();
        
         //compareTo use for big decimal 
        if (movie.getPrice().compareTo(new BigDecimal(0)) == -1 ){errors.add("Price cannot be negative.");}
        if (movie.getStock() < 0 ){errors.add("Stock cannot be negative.");}
        
        if (errors.size() > 0 ){
            session.setAttribute("editMovieErrors", errors);
         RequestDispatcher view = request.getRequestDispatcher("/Movie/updateMovie.jsp");
            view.forward(request, response);   
        }
        else{
            try { 
                manager.updateMovie(movie.getID(), movie.getTitle(), movie.getGenre(), movie.getPrice(), movie.getStock());
                session.setAttribute("editMovie", null);
                response.sendRedirect("/movie/list"); 
                
            } catch (SQLException ex) {
                Logger.getLogger(updateMovie.class.getName()).log(Level.SEVERE, null, ex);
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
