package oms.controller.customer;

import MODEL.Customer;

import MODEL.DAO.DatabaseConnector;
import MODEL.DAO.DatabaseManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "listCustomer", urlPatterns = {"/customer/list"})
public class ListCustomer extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DatabaseConnector connector = null;;
        try {
            connector = new DatabaseConnector();
            Connection conn = connector.openConnection();
            DatabaseManager db = new DatabaseManager(conn);
            List<Customer> list = db.listCustomer();
            request.setAttribute("customers", list);
            request.getRequestDispatcher("/CustomerManagement/listCustomer.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("/CustomerManagement/listCustomer.jsp").forward(request, response);
            Logger.getLogger(ListCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (connector != null) {
                    connector.closeConnection();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    public String getServletInfo() {
        return "Short description";
    }
}
