package oms.controller.order;

import MODEL.DAO.DatabaseManager;
import MODEL.Movie;
import MODEL.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "cancelorder", urlPatterns = {"/order/cancel"})
public class cancelorder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orderId = request.getParameter("id");
        HttpSession session = request.getSession();
        DatabaseManager manager = (DatabaseManager) session.getAttribute("manager");
        try {
            Order order = manager.searchOrderById(orderId);
            if(order != null)
            {
                manager.deleteOrder(orderId);
                if("SUBMITTED".equals(order.getStatus()))
                {
                    Movie movie = manager.getMovieByID(order.getMovieID());
                    if(movie != null)
                    {
                        manager.updateMovieStock(movie.getID(), movie.getStock()+order.getAmount());
                    }
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(cancelorder.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher view = request.getRequestDispatcher("/order/history");
        view.forward(request, response);
    }
}
