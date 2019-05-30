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
            out.println("<title>Servlet creatorder</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet creatorder at " + request.getContextPath() + "</h1>");
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
        DatabaseManager manager = (DatabaseManager) session.getAttribute("manager");
        User user = (User) session.getAttribute("user");
        String userId = null;
        if (user == null || user.getID() == null) {
            response.sendRedirect("/Order/createorder.jsp?user=yes");
            return;
        } else {
            userId = user.getID();
        }
        List<Movie> queryAllMovie;
        try {
            queryAllMovie = manager.queryAllMovie();
            session.setAttribute("movieAlllist", queryAllMovie);
        } catch (SQLException ex) {
            Logger.getLogger(creatorder.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher view = request.getRequestDispatcher("/Order/createorder.jsp");
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
        DatabaseManager manager = (DatabaseManager) session.getAttribute("manager");
        String movieId = request.getParameter("movieId");
        int amount = Integer.parseInt(request.getParameter("amount"));
        try {
            Movie movie = manager.findMovieById(movieId);
            if (amount > movie.getStock()) {
                response.sendRedirect("/Order/createorder.jsp?error=yes&&stock=" + movie.getStock());
                return;
            }
            BigDecimal price = movie.getPrice();
            BigDecimal totalPrice = BigDecimal.valueOf(amount).multiply(price);
            User user = (User) session.getAttribute("user");
            Order order = null;
            String userId = null;
            if (user == null) {
                userId = null;
            } else {
                userId = user.getID();
            }
            boolean orderIDExists = true;
            String ID = null;
            while (orderIDExists) {
                ID = "" + (new Random()).nextInt(999999);
                orderIDExists = manager.searchOrderById(ID) != null;
            }
            SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
            LocalDate releaseDate = LocalDate.parse(sp.format(new Date()));
            order = new Order(ID,
                    movieId, userId,
                    price, amount,
                    totalPrice, releaseDate,
                    "COMPLETED", movie.getTitle(), movie.getStock()
            );
            manager.addOrder(ID,
                    movieId, userId,
                    price, amount,
                    totalPrice, releaseDate,
                    "COMPLETED"
            );
            response.sendRedirect("/order/history");
            session.setAttribute("order", order);
        } catch (SQLException ex) {
            Logger.getLogger(creatorder.class.getName()).log(Level.SEVERE, null, ex);
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
