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

@WebServlet(name = "addCustomer", urlPatterns = {"/customer/add"})
public class AddCustomer extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Customer customer = new Customer();
        List<String> errors = new ArrayList<>();
        
        request.setAttribute("errors", errors);
        request.setAttribute("customer", customer);
        RequestDispatcher view = request.getRequestDispatcher("/CustomerManagement/createCustomer.jsp");
        view.forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        DatabaseConnector connector = null;
        try {

            Customer customer = new Customer();
            List<String> errors = new ArrayList<>();
            
            connector = new DatabaseConnector();
            Connection conn = connector.openConnection();
            DatabaseManager db = new DatabaseManager(conn);
            
            String id = ""+((new Random()).nextInt(900000000)+ + 100000000);
            while(db.isCustomerIdExist(id)) id = ""+((new Random()).nextInt(900000000)+ + 100000000);
            
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String type = request.getParameter("type");
            String address = request.getParameter("address");
            String status = "ACTIVE";
            customer.setId(id);
            customer.setName(name);
            customer.setEmail(email);
            customer.setType(type);
            customer.setAddress(address);
            customer.setStatus(status);

            Validator v = new Validator();

            if (!v.validateEmail(email)) {
                errors.add("Email is not valid.");
            }
            
            if(!v.validateAddress(address)){
                errors.add("Address is not valid.");
            }
            
            if(!v.validateName(name)){
                errors.add("Name format is not valid");
            }
            
            if (errors.size() > 0) {
                request.setAttribute("errors", errors);
                request.setAttribute("customer", customer);
                request.getRequestDispatcher("/CustomerManagement/createCustomer.jsp").forward(request, response);
            } else {
                db.addCustomer(id, name, email, type, address, status);
                response.sendRedirect("list");
            }
            
        } catch (ClassNotFoundException | SQLException | ServletException | IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
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

    public String getServletInfo() {
        return "Short description";
    }
}
