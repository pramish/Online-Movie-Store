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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import oms.controller.movie.listMovie;

/**
 *
 * @author Ian
 */
@WebServlet(name = "orderhistory", urlPatterns = {"/order/history"}, initParams = {
    @WebInitParam(name = "Name", value = "Value")})
public class orderhistory extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet orderhistory</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet orderhistory at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // FIXME
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Order> orderlist = new ArrayList<>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        session.setAttribute("orderlist", orderlist);
        if (user != null) {
            try {
                DatabaseManager manager = (DatabaseManager) session.getAttribute("manager");
                orderlist = manager.searchOrder(user.getID());//FIXME
                session.setAttribute("orderlist", orderlist);
            } catch (SQLException ex) {
                Logger.getLogger(listMovie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        RequestDispatcher view = request.getRequestDispatcher("/Order/history.jsp");
        view.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
