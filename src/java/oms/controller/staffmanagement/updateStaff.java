/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oms.controller.staffmanagement;

import MODEL.DAO.DatabaseManager;
import MODEL.Staff;
import MODEL.controller.Validator;
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pramishluitel
 */
@WebServlet(name = "updateStaff", urlPatterns = {"/updateStaff"})
public class updateStaff extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet updateStaff</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateStaff at " + request.getContextPath() + "</h1>");
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
        String staffID = request.getParameter("staffID");
        Staff editStaff = null;
        try {
            editStaff = manager.getStaffByID(staffID);
        } catch (SQLException ex) {
            Logger.getLogger(updateStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (editStaff == null) {
            response.sendRedirect("/readStaff");
        }
        session.setAttribute("editStaff", editStaff);
        session.setAttribute("editStaffErrors", new ArrayList<>());
        RequestDispatcher view = request.getRequestDispatcher("/StaffAccessManagement/updateStaff.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        DatabaseManager manager = (DatabaseManager) session.getAttribute("manager");
        Staff editStaff = (Staff) session.getAttribute("editStaff");
        if (editStaff == null) {
            response.sendRedirect("/readStaff");
        }
        editStaff.setEmail(request.getParameter("email"));
        editStaff.setName(request.getParameter("name"));
        editStaff.setPosition(request.getParameter("position"));
        editStaff.setAddress(request.getParameter("address"));
        List<String> errors = new ArrayList<>();
        Validator valid = new Validator();
        if (!valid.validateEmail(editStaff.getEmail())) {
            errors.add("Email address is not valid");
        }
        if (!valid.validateName(editStaff.getName())) {
            errors.add("Name is not valid");
        }
        if (!valid.validateAddress(editStaff.getAddress())) {
            errors.add("Address is not valid");
        }

        try {
            Staff staffEmailCheck = manager.getStaff(editStaff.getID());
            if (!staffEmailCheck.getEmail().equals(editStaff.getEmail())) {
                if (manager.checkStaffEmail(editStaff.getEmail())) {
                    errors.add("This email address is already registered to another staff");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(updateStaff.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (errors.size() > 0) {
            session.setAttribute("editStaffErrors", errors);
            RequestDispatcher view = request.getRequestDispatcher("/StaffAccessManagement/updateStaff.jsp");
            view.forward(request, response);
        } else {
            try {
                manager.updateStaff(editStaff.getID(), editStaff.getEmail(), editStaff.getName(), editStaff.getPosition(), editStaff.getAddress());
                session.setAttribute("editStaff", null);
                response.sendRedirect("/readStaff");
            } catch (SQLException ex) {
                Logger.getLogger(updateStaff.class.getName()).log(Level.SEVERE, null, ex);
                errors.add(ex.getMessage());
                session.setAttribute("editStaffErrors", errors);
                RequestDispatcher view = request.getRequestDispatcher("/StaffAccessManagement/updateStaff.jsp");
                view.forward(request, response);
            }
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
