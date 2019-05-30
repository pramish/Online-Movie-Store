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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        HttpSession session = request.getSession();
        DatabaseManager manager = (DatabaseManager) session.getAttribute("manager");
        
        User user = (User) session.getAttribute("user");
        
        List<Order> orderlist = new ArrayList<>();
   
        String date = request.getParameter("date");
        
        
        try {
                orderlist = manager.getUserOrdersByDate(user.getID(),date);
                
        } catch (SQLException ex) {
            Logger.getLogger(orderhistory.class.getName()).log(Level.SEVERE, null, ex);
            Order order = new Order(ex.getMessage());
            orderlist.add(order);
        }
        
        session.setAttribute("orderlist", orderlist);
        
        RequestDispatcher view = request.getRequestDispatcher("/Order/history.jsp");
        view.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
