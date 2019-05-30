package oms.controller.customer;

import MODEL.Customer;

import MODEL.DAO.DatabaseConnector;
import MODEL.DAO.DatabaseManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "viewCustomer", urlPatterns = {"/customer/view"})
public class ViewCustomer extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        DatabaseConnector connector = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            String id = request.getParameter("id");
            connector = new DatabaseConnector();
            Connection conn = connector.openConnection();
            DatabaseManager db = new DatabaseManager(conn);
            Customer customer = db.getCustomer(id);
            List<String> errors = new ArrayList<>();
            request.setAttribute("errors", errors);
            request.setAttribute("customer", customer);
            request.getRequestDispatcher("/CustomerManagement/editCustomer.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException | ServletException | IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(ViewCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (connector != null) {
                    connector.closeConnection();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ViewCustomer.class.getName()).log(Level.SEVERE, null, ex);
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
