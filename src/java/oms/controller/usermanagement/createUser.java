/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oms.controller.usermanagement;

import MODEL.DAO.DatabaseManager;
import MODEL.controller.Validator;
import java.io.IOException;
import java.io.PrintWriter;
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

/**
 *
 * @author pramishluitel
 */
@WebServlet(name = "createUser", urlPatterns = {"/createUser"})
public class createUser extends HttpServlet {

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
            out.println("<title>Servlet createUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet createUser at " + request.getContextPath() + "</h1>");
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
        session.setAttribute("createUserErrors", new ArrayList<>());
        RequestDispatcher view = request.getRequestDispatcher("/UserManagement/createUser.jsp");
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
        HttpSession session = request.getSession();

        DatabaseManager manager = (DatabaseManager) session.getAttribute("manager");
        String name = (String) request.getParameter("fullName");
        String password = (String) request.getParameter("password");
        String email = (String) request.getParameter("email");
        String phoneNumber = (String) request.getParameter("phoneNumber");
        int key = (new Random()).nextInt(999999);
        String ID = "" + key;
        List<String> errors = new ArrayList<>();
        Validator valid = new Validator();
        try {

            if (manager.checkEmail(email)) {
                errors.add("User is already registered.");
            }
            if (!valid.validateEmail(email)) {
                errors.add("Email address is not valid.");
            }
            if (!valid.validateName(name)) {
                errors.add("Name format is not valid.");
            }
            if (!valid.validatePassword(password)) {
                errors.add("Password format is not valid.");
            }
            if (!valid.validatePhoneNumber(phoneNumber)) {
                errors.add("Phone Number is not valid.");
            }

            if (errors.size() > 0) {
                session.setAttribute("createUserErrors", errors);
                RequestDispatcher view = request.getRequestDispatcher("/UserManagement/createUser.jsp");
                view.forward(request, response);
            } else {

                try {

                    if (!manager.checkEmail(email)) {
                        try {
                            manager.addUser(ID, email, name, password, phoneNumber);
                        } catch (SQLException ex) {
                            Logger.getLogger(createUser.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        response.sendRedirect("/users?search=" + name);
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(createUser.class.getName()).log(Level.SEVERE, null, ex);
                    errors.add(ex.getMessage());
                    session.setAttribute("createUserErrors", errors);
                    RequestDispatcher view = request.getRequestDispatcher("/UserManagement/createUser.jsp");
                    view.forward(request, response);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(createUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
