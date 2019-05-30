package oms.controller.customer;

import MODEL.Customer;
import MODEL.DAO.CustomerManage;
import MODEL.DAO.DatabaseConnector;
import MODEL.controller.Validator;
import java.io.IOException;
import java.sql.Connection;
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

@WebServlet(name = "addCustomer", urlPatterns = {"/customer/add"})
public class AddCustomer extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        DatabaseConnector connector = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            String id = request.getParameter("id");
            Customer customer = new Customer();
            List<String> errors = new ArrayList<>();
            if (id == null || id.equals("")) {
                request.setAttribute("errors", errors);
                request.setAttribute("customer", customer);
                RequestDispatcher view = request.getRequestDispatcher("/CustomerManagement/createCustomer.jsp");
                view.forward(request, response);
            } else {
                connector = new DatabaseConnector();
                Connection conn = connector.openConnection();
                CustomerManage db = new CustomerManage(conn);
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
                boolean checkId = db.checkId(id);
                Validator v = new Validator();
                if (checkId) {
                    errors.add("This customer already exists.");
                }
                if (!v.validateEmail(email)) {
                    errors.add("Email is not a valid email.");
                }
                if (errors.size() > 0) {
                    request.setAttribute("errors", errors);
                    request.setAttribute("customer", customer);
                    request.getRequestDispatcher("/CustomerManagement/createCustomer.jsp").forward(request, response);
                } else {
                    db.addCustomer(id, name, email, type, address, status);
                    response.sendRedirect("list");
                }
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
