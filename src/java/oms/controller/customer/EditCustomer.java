package oms.controller.customer;

import MODEL.Customer;
import MODEL.DAO.DatabaseConnector;
import MODEL.DAO.DatabaseManager;
import MODEL.controller.Validator;
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

@WebServlet(name = "editCustomer", urlPatterns = {"/customer/edit"})
public class EditCustomer extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        DatabaseConnector connector = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            List<String> errors = new ArrayList<>();
            connector = new DatabaseConnector();
            Connection conn = connector.openConnection();
            DatabaseManager db = new DatabaseManager(conn);
            Customer customer = new Customer();
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String type = request.getParameter("type");
            String address = request.getParameter("address");
            String status = request.getParameter("status");
            customer.setId(id);
            customer.setName(name);
            customer.setEmail(email);
            customer.setType(type);
            customer.setAddress(address);
            customer.setStatus(status);
            Validator v = new Validator();
            if (!v.validateEmail(email)) {
                errors.add("Email is not a valid email.");
            }

            if (!v.validateAddress(address)) {
                errors.add("Address is not valid.");
            }

            if (!v.validateName(name)) {
                errors.add("Name format is not valid");
            }

            if (errors.size() > 0) {
                request.setAttribute("errors", errors);
                request.setAttribute("customer", customer);
                request.getRequestDispatcher("/CustomerManagement/editCustomer.jsp").forward(request, response);
            } else {
                db.updateCustomer(id, name, email, type, address, status);
                response.sendRedirect("list");
            }
        } catch (ClassNotFoundException | SQLException | ServletException | IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(EditCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (connector != null) {
                    connector.closeConnection();
                }
            } catch (SQLException ex) {
                Logger.getLogger(EditCustomer.class.getName()).log(Level.SEVERE, null, ex);
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
